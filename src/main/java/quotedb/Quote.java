package quotedb;

import java.util.UUID;

public class Quote {
  private String id;
  private String author;
  private String text;
  private String sessionId;

  public Quote(String author, String text, String sessionId) {
    this.id = UUID.randomUUID().toString();
    this.author = author;
    this.text = text;
    this.sessionId = sessionId;
  }

  public String getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getText() {
    return text;
  }

  public String getSessionId() {
    return sessionId;
  }
}
