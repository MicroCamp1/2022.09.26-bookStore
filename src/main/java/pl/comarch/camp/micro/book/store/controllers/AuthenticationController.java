package pl.comarch.camp.micro.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.comarch.camp.micro.book.store.exceptions.ValidationException;
import pl.comarch.camp.micro.book.store.model.User;
import pl.comarch.camp.micro.book.store.services.IAuthenticationService;
import pl.comarch.camp.micro.book.store.session.SessionObject;
import pl.comarch.camp.micro.book.store.validators.UserDataValidator;

import javax.annotation.Resource;

@Controller
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    @Resource
    SessionObject sessionObject;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute User user) {
        try {
            UserDataValidator.validateLogin(user.getLogin());
            UserDataValidator.validatePassword(user.getPassword());
        } catch (ValidationException e) {
            return "redirect:/login";
        }
        this.authenticationService.authenticate(user.getLogin(),
                user.getPassword());
        if (this.sessionObject.isLogged()) {
            return "redirect:/main";
        }

        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/login";
    }
}
