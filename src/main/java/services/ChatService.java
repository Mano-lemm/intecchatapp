package services;

import exceptions.IdIsNull;
import exceptions.MessageIsNull;
import exceptions.UserIsNull;
import models.Message;
import models.User;
import repository.MessageRepository;
import repository.UserRepository;

import java.util.List;
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

    private void validateId(Long id){
        if(Objects.isNull(id)){
            throw new IdIsNull();
        }
    }

    public void createUser(User user){
        validateUser(user);
        ur.createUser(user);
    }

    public Optional<User> readUser(Long id){
        validateId(id);
        return ur.readUser(id);
    }

    public Optional<List<User>> readUsers(List<Long> ids){
        for (Long id : ids) {
            validateId(id);
        }
        return Optional.of(ur.readUsers(ids));
    }

    public List<User> readAllUsers(){
        return ur.readAllUsers();
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
