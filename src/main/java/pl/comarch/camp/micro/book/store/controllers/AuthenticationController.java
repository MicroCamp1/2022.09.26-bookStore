package pl.comarch.camp.micro.book.store.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.model.User;
import pl.comarch.camp.micro.book.store.services.IAuthenticationService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IAuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.authenticationService.authenticate(user.getLogin(),
                user.getPassword());
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/login";
    }
}
