package pl.comarch.camp.micro.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.database.impl.MemoryBookDAO;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
public class CommonController {

    @Autowired
    IBookDAO bookDAO;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", bookDAO.getBookList());
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
