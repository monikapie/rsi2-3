package rsirest2.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
class MessageService {
    private List<Message> messages = new ArrayList<>();

    @PostConstruct
    void populate() {
        messages.add(new Message(1L, "pierwsza wiadomosc", "abc"));
        messages.add(new Message(2L, "druga wiadomosc", "abcdef"));
        messages.add(new Message(3L, "trzecia wiadomosc", "fedesca"));
    }

    List<Message> findAll() {
        return messages;
    }

    Message findById(Long id) {
        return messages.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
    }

    void add(Message message) {
        message.setId(messages.stream().mapToLong(Message::getId).max().orElseThrow(UnsupportedOperationException::new) + 1);
        messages.add(message);
    }

    void deleteById(Long id) {
        messages = messages.stream().filter(message -> !id.equals(message.getId())).collect(Collectors.toList());
    }

    void updateMessage(Message message, Long id) {
        message.setId(id);
        messages.set(Math.toIntExact(id), message);
    }
}
