package ch.heigvd.res.lab01.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * This class represents a "quote", with tags, the main text, a link and a source.
 * When using the QuoteClient, you retrieve instances of this class.
 * 
 * @author Olivier Liechti
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote implements Serializable {
  @JsonProperty
  private String[] tags;
  @JsonProperty
  private String quote;
  @JsonProperty
  private String link;
  @JsonProperty
  private String source;

  public Quote() {  
  }
  
  public Quote(String[] tags, String quote, String link, String source) {
    this.tags = tags;
    this.quote = quote;
    this.link = link;
    this.source = source;
  }

  public String[] getTags() {
    return tags;
  }

  public String getQuote() {
    return quote;
  }

  public String getLink() {
    return link;
  }

  public String getSource() {
    return source;
  }

}
