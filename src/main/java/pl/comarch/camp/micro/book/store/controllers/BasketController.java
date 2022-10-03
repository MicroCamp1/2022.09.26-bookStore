package pl.comarch.camp.micro.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.services.IBasketService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/basket")
public class BasketController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBasketService basketService;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String addToBasket(@PathVariable int id) {
        this.basketService.addBookToBasket(id);
        return "redirect:/main";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String basket(Model model) {
        model.addAttribute("basket", this.sessionObject.getBasket());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("basketSum",
                this.basketService.calculateBasketSum());
        return "basket";
    }
}
