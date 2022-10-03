package pl.comarch.camp.micro.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.comarch.camp.micro.book.store.model.User;

@RestController
public class SimpleRestController {

    @RequestMapping(value = "/endpoint1", method = RequestMethod.GET)
    public void endpoint1() {
        System.out.println("Endpoint działa !!");
    }

    @RequestMapping(value = "/endpoint2", method = RequestMethod.GET)
    public User endpoint2() {
        User user = new User();
        user.setLogin("janusz");
        user.setPassword("janusz123");

        return user;
    }

    @RequestMapping(value = "/endpoint3/{param1}/{param2}",
            method = RequestMethod.GET)
    public void endpoint3(@PathVariable String param1,
                          @PathVariable String param2,
                          @RequestParam String p1,
                          @RequestParam String p2) {
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(p1);
        System.out.println(p2);
    }

    @RequestMapping(value = "/endpoint4", method = RequestMethod.POST)
    public void endpoint4(@RequestBody User user) {
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
    }

    @RequestMapping(value = "/endpoint5", method = RequestMethod.POST)
    public void endpoint5(@RequestBody User user,
                          @RequestHeader("header1") String h1,
                          @RequestHeader String header2,
                          @RequestHeader String abc) {
        System.out.println(user.getPassword());
        System.out.println(user.getLogin());
        System.out.println(h1);
        System.out.println(header2);
        System.out.println(abc);
    }

    @RequestMapping(value = "/endpoint6/{path}", method = RequestMethod.POST)
    public User endpoint6(@RequestBody User user,
                          @RequestHeader String header,
                          @PathVariable String path,
                          @RequestParam int wiek) {
        System.out.println(user.getLogin());
        System.out.println(user.getPassword());
        System.out.println(header);
        System.out.println(path);
        System.out.println(wiek);

        User user2 = new User();
        user2.setLogin("Zbyszek");
        user2.setPassword("tajnehaslo");

        return user2;
    }

    @RequestMapping(value = "/endpoint7", method = RequestMethod.GET)
    public ResponseEntity<User> endpoint7() {
        User user = new User();
        user.setLogin("wiesiek");
        user.setPassword("wieslaw0987");

        ResponseEntity<User> response = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header("h1", "abc")
                .header("h2", "abc")
                .body(user);

        return response;
    }
}
