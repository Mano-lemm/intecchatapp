package views;

import models.ChatRoom;
import models.Message;
import models.User;
import org.jetbrains.annotations.NotNull;

public class ChatRoomView {
    private ChatRoom chatRoom;

    public ChatRoomView(@NotNull ChatRoom chatRoom){
        this.chatRoom = chatRoom;
    }

    public void viewConnectedUsers(){
        System.out.println("All currently connected users:");
        for (User user : chatRoom.getUsers()) {
            System.out.println("\t" + user);
        }
    }

    public void viewLastMessage(){
        System.out.println(chatRoom.getMessages().get(chatRoom.getMessages().size() - 1));
    }

    public void viewAllMessages(){
        for (Message message : chatRoom.getMessages()) {
            System.out.println(message);
        }
    }
}
