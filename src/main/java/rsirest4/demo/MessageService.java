package rsirest4.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
class MessageService {
    private List<Message> messages = new ArrayList<>();

    @PostConstruct
    void populate() {
        messages.add(new Message(1L, "pierwsza wiadomosc", "abc", Arrays.asList(new Comment("blabla"), new Comment("blabla2"))));
        messages.add(new Message(2L, "druga wiadomosc", "abcdef", Arrays.asList(new Comment("wow"))));
        messages.add(new Message(3L, "trzecia wiadomosca", "adam"));
    }

    List<Message> findAll() {
        return messages;
    }

    Message findById(Long id) {
        return messages.stream().filter(m -> m.getMessageId().equals(id)).findAny().orElse(null);
    }

    Message findByAuthorAndContentEnded(String author, String contentEnded) {
        return messages.stream().filter(m -> m.getAuthor().equals(author) && m.getContent().endsWith(contentEnded)).findAny().orElse(null);
    }

    List<Message> findContentStartingWith(String startedWith) {
        return messages.stream().filter(m -> m.getContent().startsWith(startedWith)).collect(Collectors.toList());
    }

    Message add(Message message) {
        message.setMessageID(messages.stream().mapToLong(Message::getMessageId).max().orElseThrow(UnsupportedOperationException::new) + 1);
        messages.add(message);
        return message;
    }

    void deleteById(Long id) {
        messages = messages.stream().filter(message -> !id.equals(message.getMessageId())).collect(Collectors.toList());
    }

    void updateMessage(Message message, Long id) {
        message.setMessageID(id);
        messages.set(Math.toIntExact(id), message);
    }

    List<Comment> getCommentsForMessage(int id){
        return messages.get(id).getComments();
    }

}
