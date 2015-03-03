package ch.heigvd.schoolpulse;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 *
 * @author Olivier Liechti
 */
public class TestResultListener extends RunListener {

  private static final Logger LOG = Logger.getLogger(TestResultListener.class.getName());
  private WebTarget target;
  
  
  public TestResultListener() {
    Client client = ClientBuilder.newClient().register(JacksonFeature.class);
    target = client.target("http://iflux.herokuapp.com/").path("events");

  }

  @Override
  public void testStarted(Description description) throws Exception {
  }

  @Override
  public void testRunStarted(Description description) throws Exception {
    Event[] payload = new Event[1];
    Event e1 = new Event();
    e1.setSource("RES");
    e1.setTimestamp(new Date());
    e1.setType("io.iflux.schoolpulse.test");
    e1.set("who", "olivier");
    e1.set("pulseId", System.getProperty("schoolPulseUserId"));
    payload[0] = e1;
    try {
    Response response = target.request().post(Entity.json(payload));
    } catch (Exception e) {
      LOG.info("Are you connected to the network? It is better to run the tests when you have an Internet connection.");
    }
  }

  @Override
  public void testRunFinished(Result result) throws Exception {
    if (true) return;
    ObjectMapper mapper = new ObjectMapper();
    BufferedWriter writer = new BufferedWriter(new FileWriter("test-results.json"));
    mapper.writeValue(writer, result);
    writer.close();        
  }

}
