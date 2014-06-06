package ch.fhnw.kvan.chat.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import ch.fhnw.kvan.chat.ChatRoom;
import ch.fhnw.kvan.chat.IChatRoom;
import ch.fhnw.kvan.chat.gui.ClientGUI;

public class Client implements ch.fhnw.kvan.chat.IChatDriver,
		ch.fhnw.kvan.chat.IChatRoom {

	// name of the client
	private String screenName;
	// GUI stuff
	private ClientGUI clientGUI;
	// for writing to and reading from the server
	// private Out out;
	private BufferedReader in;

	// separate thread listening for incoming messages

	private ChatRoom chat;
	private String server;

	private static Logger logger;

	private URL url;
	private HttpURLConnection connection;

	public Client(String screenName) {
		// Log4J initialisation
		logger = Logger.getLogger(Client.class);
		// keep the user name
		this.screenName = screenName;
	}

	private void initGUIAndListen() {
		// create client GUI
		clientGUI = new ClientGUI(this, screenName);
		// start listening thread listening to and handling messages from Server
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// Set up a simple configuration that logs on the console
		BasicConfigurator.configure();
		// create client
		final Client client = new Client(args[0]);
		// connect to server

		try {
			String result = args[1].substring(args[1].indexOf(":") + 1,
					args[1].indexOf("/"));
			client.connect(args[1], Integer.parseInt(result));
		} catch (IOException e) {
			logger.error("Client IOException: could not connect host="
					+ args[1] + " port=" + args[2]);
		}
		// initiate clientGUI and start listening to messages from server
		client.initGUIAndListen();
		// announce itself to the server
		client.addParticipant(args[0]);
	}

	@Override
	public boolean addParticipant(String name) throws IOException {
		String result[] = sendGet("action=addParticipant&name=" + name);
		System.out.println("result: " + result);
		if (result != null) {
			addClientParticipant(result[0]);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeParticipant(String name) throws IOException {
		String result[] = sendGet("action=removeParticipant&name=" + name);
		System.out.println(result);
		if (result != null) {
			removeClientParticipant(result[0]);
			return true;
		}
		return false;
	}

	@Override
	public boolean addTopic(String topic) throws IOException {
		String result[] = sendGet("action=addTopic&topic=" + topic);
		System.out.println(result);
		if (result != null) {
			addClientTopic(result[0]);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeTopic(String topic) throws IOException {
		String result[] = sendGet("action=removeTopic&topic=" + topic);
		if (result != null) {
			removeClientTopic(result[0]);
			return true;
		}
		return false;
	}

	@Override
	public boolean addMessage(String topic, String message) throws IOException {
		// action=postMessage&message=< Nachricht>&topic=<Thema>
		String[] result = sendGet("action=postMessage&message=" + message
				+ "&topic=" + topic);
		if (result != null) {
			addClientMessage(result[0]);
			return true;
		}
		return false;
	}

	@Override
	public String getMessages(String topic) throws IOException {
		String result[] = sendGet("action=getMessages&topic=" + topic);
		if (result != null) {
			updateClientMessages(result[0]);
			return result[0];
		} else
			return null;
	}

	@Override
	public String refresh(String topic) throws IOException {
		String result[] = sendGet("action=refresh&topic=" + topic);

		String[] array = result[0].split("=");
		if (array.length > 1) {
			updateClientMessages(result[0]);
		}
		array = result[1].split("=");
		if (array.length > 1) {
			updateClientParticipants(result[1]);
		}
		array = result[2].split("=");
		if (array.length > 1) {
			updateClientTopics(result[2]);
		}

		return null;
	}

	@Override
	public void connect(String host, int port) throws IOException {
		chat = ChatRoom.getInstance();
		this.server = host;
		in = null;
	}

	@Override
	public void disconnect() throws IOException {
		chat = null;
		in.close();
	}

	@Override
	public IChatRoom getChatRoom() {
		return chat;
	}

	public String[] sendGet(String params) throws IOException {

		// System.out.println(params);
		// String sUrl = "http://" + server + "?" + params;
		// String sUrlReplaced = sUrl.replace(" ", "%20");
		// System.out.println(sUrlReplaced);
		// URL url = new URL(sUrlReplaced);
		// HttpURLConnection connection = (HttpURLConnection)
		// url.openConnection();
		// is = connection.getInputStream();
		// System.out.println("got is " + is);
		//
		// int size = connection.getContentLength();
		// byte[] byteArray = new byte[size];
		// int read = 0;
		//
		// while (read < size) {
		// int n = is.read(byteArray, read, size - read);
		// if (n == -1)
		// break; // EOF
		// read += n;
		// }
		//
		// return new ByteArrayInputStream(byteArray);

		System.out.println(params);
		String sUrl = "http://" + server + "?" + params;
		String sUrlReplaced = sUrl.replace(" ", "%20");
		System.out.println(sUrlReplaced);
		url = new URL(sUrlReplaced);
		connection = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String[] strArray = new String[3];
		String str;
		int i = 0;
		while ((str = in.readLine()) != null) {
			System.out.println(str);
			strArray[i] = str;
			i++;
		}
		return strArray;

	}

	private void addClientMessage(String msgString) {
		String topic = "";
		String[] strArray = msgString.split(";");
		if (strArray.length > 1) {
			String[] topicArray = strArray[1].split("=");
			if (topicArray.length > 1) {
				topic = topicArray[1];
			}
		}
		if (topic.equals(clientGUI.getCurrentTopic())) {
			if (strArray.length > 0) {
				String[] messageArray = strArray[0].split("=");
				if (messageArray.length > 1) {
					String msg = messageArray[1];
					clientGUI.addMessage(msg);
				}
			}
		}
	}

	private void updateClientMessages(String messages) {
		String[] array = messages.split("=");
		if (array.length > 1) {
			String[] messagesArray = array[1].split(";;");

			String[] tempArray = new String[messagesArray.length];
			// reverse the order of the messages, such as the last is at the end
			int j = 0;
			for (int i = messagesArray.length - 1; i >= 0; i--) {
				tempArray[j++] = messagesArray[i];

			}

			for (int i = 0; i < tempArray.length; i++) {
				messagesArray[i] = tempArray[i];
			}

			clientGUI.updateMessages(messagesArray);
		} else {
			clientGUI.updateMessages(new String[0]);
		}
	}

	private void updateClientTopics(String topics) {
		String[] array = topics.split("=");
		if (array.length > 1) {
			String[] topicsArray = array[1].split(";");
			if (topicsArray.length > 0) {
				clientGUI.updateTopics(topicsArray);
			}
		}
	}

	private void addClientTopic(String topicString) {
		String[] array = topicString.split("=");
		if (array.length > 1) {
			clientGUI.addTopic(array[1]);
		}
	}

	private void removeClientTopic(String topicString) {
		String[] array = topicString.split("=");
		if (array.length > 1) {
			clientGUI.removeTopic(array[1]);
		}
	}

	private void updateClientParticipants(String participants) {
		String[] array = participants.split("=");
		if (array.length > 1) {
			String[] participantsArray = array[1].split(";");
			if (participantsArray.length > 0) {
				clientGUI.updateParticipants(participantsArray);
			}
		}
	}

	private void addClientParticipant(String participantString) {
		String[] array = participantString.split("=");
		if (array.length > 1) {
			System.out.println("1");
			// if (!array[1].equals(screenName)) {
			System.out.println("2 add to gui");
			clientGUI.addParticipant(array[1]);
			// }
		}
	}

	private void removeClientParticipant(String participantString) {
		String[] array = participantString.split("=");
		if (array.length > 1) {
			clientGUI.removeParticipant(array[1]);
		}
	}

}
