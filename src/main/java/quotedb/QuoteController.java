package quotedb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/quotes")
public class QuoteController {
  private final QuoteService quoteService;

  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping("/")
  public String quoteHome(@RequestParam(value = "error", required = false) String error, HttpSession session, Model model) {
    List<Quote> myQuotes = quoteService.getQuotesForSession(session);
    List<Quote> allQuotes = quoteService.getAllQuotesSorted();

    model.addAttribute("myQuotes", myQuotes);
    model.addAttribute("allQuotes", allQuotes);
    model.addAttribute("quoteTotal", allQuotes.size());
    model.addAttribute("error", error);

    return "index";
  }

  @PostMapping("/add")
  public String quoteAdd(@RequestParam("author") String author,
                         @RequestParam("quote") String text,
                         HttpSession session) {
    Quote quote = new Quote(author, text, session.getId());
    quoteService.addQuote(quote);

    return "redirect:/quotes/";
  }

  @PostMapping("/delete")
  public String quoteDelete(@RequestParam("delete") String deleteId, HttpSession session) {
    try {
      quoteService.removeQuote(deleteId, session);
      return "redirect:/quotes/";

    } catch (Exception exception) {
      return "redirect:/quotes/?error=" + exception.getMessage();
    }
  }

  @PostMapping("/deleteAll")
  public String deleteAllInSession(HttpSession session) {
    quoteService.removeQuotesForSession(session);
    return "redirect:/quotes/";
  }
}
