package rsirest2.demo;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

class Message extends ResourceSupport {
    private Long messageID;
    private String content;
    private String author;
    private List<Comment> comments = new ArrayList<>();

    Message() {}

    Message(Long id, String content, String author) {
        this.messageID = id;
        this.content = content;
        this.author = author;
        add(linkTo(MessageResource.class).withRel("messages"));
        add(linkTo(methodOn(MessageResource.class).read(id.intValue())).withRel("comments"));
        add(linkTo(methodOn(MessageResource.class).findById(id)).withSelfRel());
    }

    Message(Long id, String content, String author, List<Comment> comments) {
        this.messageID = id;
        this.content = content;
        this.author = author;
        this.comments = comments;
        add(linkTo(MessageResource.class).withRel("messages"));
        add(linkTo(methodOn(MessageResource.class).read(id.intValue())).withRel("comments"));
        add(linkTo(methodOn(MessageResource.class).findById(id)).withSelfRel());
    }

    Long getMessageId() {
        return messageID;
    }

    String getContent() {
        return content;
    }

    String getAuthor() {
        return author;
    }

    void setMessageID(Long messageID) {
        this.messageID = messageID;
    }

    List<Comment> getComments() {
        return comments;
    }
}
