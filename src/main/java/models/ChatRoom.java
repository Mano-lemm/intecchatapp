package models;

import exceptions.UserIsAlreadyInChatroom;
import exceptions.UserNotInChatRoom;
import org.jetbrains.annotations.NotNull;
import repository.MessageRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatRoom {
    private final List<User> users = new ArrayList<>();
    private final List<Message> messages = new ArrayList<>();

    public void connect(@NotNull User user){
        if(users.contains(user)){
            throw new UserIsAlreadyInChatroom();
        }
        users.add(user);
    }

    public Message sendMessage(@NotNull User user, @NotNull String body){
        if(!users.contains(user)){
            throw new UserNotInChatRoom();
        }
        Message message = new Message(body, user, users);
        messages.add(message);
        return message;
    }

    public List<User> getUsers(){
        return Collections.unmodifiableList(users);
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
