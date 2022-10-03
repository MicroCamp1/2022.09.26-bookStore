package pl.comarch.camp.micro.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.model.User;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class RestUserController {

    @Autowired
    IUserDAO userDAO;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String login) {
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if(userBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userBox.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        this.userDAO.persistUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
