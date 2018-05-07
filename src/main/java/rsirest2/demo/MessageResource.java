package rsirest2.demo;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
class MessageResource {
    private final MessageService messageService;

    MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/messages")
    public List<Message> findAll(HttpServletRequest request) {
        return messageService.findAll();
    }

    @GetMapping(value = "/messages/{id}")
    public Message findById(@PathVariable("id") Long id) {
        return messageService.findById(id);
    }

    @PostMapping(value = "/messages")
    public void add(@RequestBody Message message) {
        messageService.add(message);
    }

    @PutMapping(value = "/messages/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Message message) {
        messageService.updateMessage(message, id);
    }

    @DeleteMapping(value = "/messages/{id}")
    public void delete(@PathVariable("id") Long id) {
        messageService.deleteById(id);
    }
}
