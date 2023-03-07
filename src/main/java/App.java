import exceptions.UserIsAlreadyInChatroom;
import models.ChatRoom;
import models.Message;
import models.User;
import services.ChatService;
import views.ChatRoomView;

import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        User mano = new User("Mano", "onam");
        User kadir = new User("kadir", "ridak");
        User avi = new User("avi", "iva");
        User halil = new User("halil", "lilah");

        ChatRoom chatRoom = new ChatRoom();
        ChatRoomView crv = new ChatRoomView(chatRoom);
        ChatService cs = new ChatService();

        chatRoom.connect(mano);
        chatRoom.connect(kadir);
        chatRoom.connect(avi);
        chatRoom.connect(halil);
        try {
            chatRoom.connect(mano);
        } catch (UserIsAlreadyInChatroom chatroom){
            System.err.println(chatroom);
        }

        crv.viewConnectedUsers();
        chatRoom.sendMessage(mano, "hallo");
        chatRoom.sendMessage(avi, "hallo");
        chatRoom.sendMessage(kadir, "hallo");
        chatRoom.sendMessage(halil, "hallo");
        chatRoom.sendMessage(mano, "Waarom zeggen we allemaal hallo?");

        crv.viewAllMessages();

        for (User user : chatRoom.getUsers()) {
            cs.createUser(user);
        }

        for (Message message : chatRoom.getMessages()) {
            cs.createMessage(message);
        }

        List<User> allusers = cs.readAllUsers();

        for (User u : allusers) {
            System.out.printf("user %s with id %d found.\n", u.getName(), u.getId());
        }
    }
}
