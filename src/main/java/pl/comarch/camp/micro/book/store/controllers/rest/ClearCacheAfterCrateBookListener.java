package pl.comarch.camp.micro.book.store.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClearCacheAfterCrateBookListener {

    @EventListener
    public void on(BookCreatedEvent event) {
        log.info("Book created in {} - clear cache book: {}", event.getSource().getClass().getSimpleName(), event.getCreatedBook());
    }
}
