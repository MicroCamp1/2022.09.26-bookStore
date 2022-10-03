package pl.comarch.camp.micro.book.store.controllers.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.Order;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.model.User;
import pl.comarch.camp.micro.book.store.model.dto.OrderDTO;

@RestController
@RequestMapping(value = "/order")
public class RestOrderController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrderDTO getOrder(@PathVariable int id) {
        Order order = new Order();
        order.setId(id);

        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        order.setUser(user);

        Position position1 = new Position();
        position1.setQuantity(5);
        position1.setBook(Book.builder()
                .id(3).title("Tytul3")
                .author("Author3").isbn("13-123-4756")
                .price(54.33).build());

        Position position2 = new Position();
        position2.setQuantity(2);
        position2.setBook(Book.builder()
                .id(2).title("Tytul2")
                .author("Author2").isbn("13-123-4345")
                .price(44.33).build());

        order.getPositions().add(position1);
        order.getPositions().add(position2);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUser("http://localhost:8080/user/" + user.getLogin());
        orderDTO.getPositions().add("http://localhost:8080/position/1");
        orderDTO.getPositions().add("http://localhost:8080/position/2");

        return orderDTO;
    }
}
