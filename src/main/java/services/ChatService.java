package services;

import exceptions.MessageIsNull;
import exceptions.UserIsNull;
import models.Message;
import models.User;
import repository.MessageRepository;
import repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

public class ChatService {
    private final UserRepository ur = new UserRepository();
    private final MessageRepository mr = new MessageRepository();

    private void validateMessage(Message message) {
        if(Objects.isNull(message)){
            throw new MessageIsNull();
        }
    }

    private void validateUser(User user){
        if(Objects.isNull(user)){
            throw new UserIsNull();
        }
    }

    public void createUser(User user){
        validateUser(user);
        ur.createUser(user);
    }

    public Optional<User> readUser(Long id){
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        return ur.readUser(id);
    }

    public void updateUser(User user){
        validateUser(user);
        ur.updateUser(user);
    }

    public void deleteUser(User user){
        validateUser(user);
        ur.deleteUser(user);
    }

    public void createMessage(Message message){
        validateMessage(message);
        mr.createMessage(message);
    }

    public Optional<Message> readMessage(Long id){
        if (Objects.isNull(id)) {
            return Optional.empty();
        }
        return mr.readMessage(id);
    }

    public void updateMessage(Message message){
        validateMessage(message);
        mr.updateMessage(message);
    }

    public void deleteMessage(Message message){
        validateMessage(message);
        mr.deleteMessage(message);
    }
}
