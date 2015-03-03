package ch.heigvd.res.lab01.quotes;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * This class provides a method to invoke a web service and to receive a quote.
 * The implementation uses the jersey framework, but you do not need to worry
 * about that. You do not need to modify this file.
 * 
 * You will use this class to fetch quotes, which you will then save on the
 * file system.
 * 
 * @author Olivier Liechti
 */
public class QuoteClient {

  static String WEB_SERVICE_ENDPOINT = "http://www.iheartquotes.com/api/v1/random?min_lines=5&format=json";

  /**
   * Use this method to invoke the iheartquotes.com web service and receive
   * an instance of a Quote.
   * 
   * @return an instance of Quote, with values provided by the web service
   */
  public Quote fetchQuote() {
    Client client = ClientBuilder.newBuilder()
      .register(JacksonFeature.class)
      .register(SimpleObjectMapperProvider.class)
      .build();
    WebTarget target = client.target(WEB_SERVICE_ENDPOINT);
    Invocation.Builder invocationBuilder = target.request();
    Response response = invocationBuilder.get();
    Quote quote = response.readEntity(Quote.class);
    return quote;
  }

}
