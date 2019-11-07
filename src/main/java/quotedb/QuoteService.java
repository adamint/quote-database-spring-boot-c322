package quotedb;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteService {
  private final List<Quote> quotes = new ArrayList<>();

  public List<Quote> getAllQuotesSorted() {
    return quotes.stream().sorted(Comparator.comparing(Quote::getSessionId)).collect(Collectors.toList());
  }

  public List<Quote> getQuotesForSession(HttpSession session) {
    return quotes.stream().filter(quote -> quote.getSessionId().equals(session.getId())).collect(Collectors.toList());
  }

  public void removeQuotesForSession(HttpSession session) {
    quotes.removeIf(quote -> quote.getSessionId().equals(session.getId()));
  }

  public void removeQuote(String id, HttpSession session) {
    Quote quote = quotes.stream().filter(q -> q.getId().equals(id)).findFirst().orElse(null);
    if (quote == null) throw new IllegalArgumentException("No quote with id " + id + " found");
    if (!quote.getSessionId().equals(session.getId()))
      throw new IllegalArgumentException("You don't have permission to delete this quote!");
    quotes.remove(quote);
  }

  public void addQuote(Quote quote) {
    quotes.add(quote);
  }
}
