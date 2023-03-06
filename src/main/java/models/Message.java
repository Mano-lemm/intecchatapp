package models;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    @ManyToOne
    private User sender;
    @ManyToMany
    private List<User> receivers;

    protected Message(){
    }

    public Message(@NotNull String body, @NotNull User sender, @NotNull List<User> receivers){
        this.body = body;
        this.sender = sender;
        this.receivers = receivers;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getReceivers() {
        return Collections.unmodifiableList(receivers);
    }

    @Override
    public String toString() {
        return sender.getName() + " : " + body;
    }
}
