package rsirest4.demo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/messages", produces = "application/hal+json")
class MessageResource {
    private MessageService messageService;

    MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/http-request")
    public String getHttpRequest(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    @GetMapping(value = "/messagesFilter/{startedWith}")
    public List<Message> findAllStartedWith(@PathVariable("startedWith") String startedWith) {
        return messageService.findContentStartingWith(startedWith);
    }

    @GetMapping
    public ResponseEntity<Resources<Message>> findAll(HttpServletRequest request) {
        List<Message> messages = messageService.findAll();
        Resources<Message> resources = new Resources<>(messages);
        String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString,"self"));
        return ResponseEntity.ok(resources);
    }

    @PostMapping(value = "/messagesSearch")
    public Message findByAuthorAndContentEnded(@RequestBody SearchParam searchParam) {
        return messageService.findByAuthorAndContentEnded(searchParam.getAuthor(), searchParam.getContentEnded());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Message> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(messageService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Message> add(@RequestBody Message message, UriComponentsBuilder b) {
        Message message1 = messageService.add(message);
        UriComponents uriComponents =
                b.path("/messages/{id}").buildAndExpand(message1.getMessageId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Message message) {
        messageService.updateMessage(message, id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        messageService.deleteById(id);
    }

    @GetMapping(value = "/{messageId}/comments")
    public List<Comment> read(@PathVariable("messageId") int messageId) {
        return messageService.getCommentsForMessage(messageId);
    }
}
