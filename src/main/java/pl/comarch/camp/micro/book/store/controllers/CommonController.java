package pl.comarch.camp.micro.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.comarch.camp.micro.book.store.database.repositories.BookRepository;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {

    private final BookRepository bookRepository;

    @Resource
    SessionObject sessionObject;

    public CommonController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "main";
    }

    //@RequestMapping(value = "/costam", method = RequestMethod.GET)
    /*@GetMapping(value = "cos")
    public String cos() {
        return "cos";
    }*/

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "contact";
    }
}
