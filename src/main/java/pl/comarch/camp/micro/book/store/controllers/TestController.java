package pl.comarch.camp.micro.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.comarch.camp.micro.book.store.model.User;

@Controller
public class TestController {

    @RequestMapping(value = "/path/{param1}/{param2}/{param3}",
            method = RequestMethod.GET)
    public String pathTest(
            @PathVariable String param1,
            @PathVariable String param2,
            @PathVariable String param3) {

        System.out.println(param1);
        System.out.println(param2);
        System.out.println(param3);

        return "cos";
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String requestTest(@RequestParam("param1") String a) {
        System.out.println(a);

        return "cos";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /*@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String formProcess(@RequestParam String login,
                              @RequestParam String pass,
                              @RequestParam String pass2) {
        System.out.println(login);
        System.out.println(pass);
        System.out.println(pass2);
        return "cos";
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String model(@ModelAttribute User user, @RequestParam String pass2) {
        System.out.println(pass2);
        System.out.println(user);
        return "cos";
    }
}
