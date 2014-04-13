package ch.fhnw.kvan.chat.socket.client;

import ch.fhnw.kvan.chat.ChatRoom;
import ch.fhnw.kvan.chat.gui.ClientGUI;

public class Client {
	public static void main(String[] args) {
		ChatRoom r = ChatRoom.getInstance();
		ClientGUI cgui = new ClientGUI(r, args[0]);
	}
}
