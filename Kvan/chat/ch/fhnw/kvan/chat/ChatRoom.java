package ch.fhnw.kvan.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * The ChatRoom forwards all requests from clients to either the Participants or
 * the Chats class, which store/manage the participant names or respectively the
 * current topics and messages concerning those topics.
 * 
 * @see ChatRoom
 * @author � ibneco, Rheinfelden
 * @version
 */
public class ChatRoom implements ch.fhnw.kvan.chat.IChatRoom {
	private final Participants participantInfo = new Participants();
	private final Chats chatInfo = new Chats();

	// To ensure singleton behaviour
	private static ChatRoom chatRoomInstance = null;

	private ChatRoom() {

	}

	public static ChatRoom getInstance() {
		if (chatRoomInstance == null) {
			chatRoomInstance = new ChatRoom();
		}
		return chatRoomInstance;
	}

	@Override
	public boolean addParticipant(String name) throws IOException {
		if (!name.trim().equalsIgnoreCase("")) {
			return participantInfo.addParticipant(name);
		} else {
			return false;
		}
	}

	@Override
	public boolean removeParticipant(String name) throws IOException {
		if (!name.trim().equalsIgnoreCase("")) {
			return participantInfo.removeParticipant(name);
		} else {
			return false;
		}
	}

	public String getParticipants() throws IOException {
		if (!participantInfo.getParticipants().equalsIgnoreCase("")) {
			return participantInfo.getParticipants();
		} else {
			return ("participants=");
		}
	}

	@Override
	public boolean addTopic(String topic) throws IOException {
		if (!topic.trim().equalsIgnoreCase("")) {
			return chatInfo.addTopic(topic);
		} else {
			return false;
		}
	}

	@Override
	public boolean removeTopic(String topic) throws IOException {
		if (!topic.trim().equalsIgnoreCase("")) {
			return chatInfo.removeTopic(topic);
		} else {
			return false;
		}
	}

	public String getTopics() throws IOException {
		if (!chatInfo.getTopics().equalsIgnoreCase("")) {
			return chatInfo.getTopics();
		} else {
			return ("topics=");
		}
	}

	@Override
	public boolean addMessage(String topic, String message) throws IOException {
		if (!topic.trim().equalsIgnoreCase("")
				&& !message.trim().equalsIgnoreCase("")) {
			return chatInfo.addMessage(topic, message);
		} else {
			return false;
		}
	}

	@Override
	public String getMessages(String topic) throws IOException {
		if (!topic.trim().equalsIgnoreCase("")) {
			return chatInfo.getMessages(topic);
		} else {
			return ("messages=");
		}
	}

	@Override
	public String refresh(String topic) throws IOException {
		if (!topic.trim().equalsIgnoreCase("")) {
			return chatInfo.getMessages(topic);
		} else {
			return ("messages=");
		}
	}

	/**
	 * The Participants stores and manages all participants. It adds/removes
	 * participant names as they are joining or leaving the chat room in an
	 * ArrayList. It stores all active participant names in form of a String as
	 * well, which can be sent to a client to refresh its local participant
	 * list.
	 * 
	 * @see Participants
	 * @author � ibneco, Rheinfelden
	 * @version
	 */

	private class Participants implements ch.fhnw.kvan.chat.IParticipants {
		private final List<String> participantList = Collections
				.synchronizedList(new ArrayList<String>());
		private String participantString = "";

		private final Logger logger2;

		public Participants() {
			logger2 = Logger.getLogger(Participants.class);
		}

		@Override
		public synchronized boolean addParticipant(String name) {
			logger2.info("creating new client:" + name);

			// add participant to list if not yet present
			if (!participantList.contains(name)) {
				participantList.add(name);
				Collections.sort(participantList);

				// create a new participantString from participants array
				StringBuffer participants = new StringBuffer();
				participants.append("participants=");
				String[] participantArray = new String[participantList.size()];
				participantList.toArray(participantArray);
				for (int i = 0; i < participantArray.length; i++) {
					participants.append(participantArray[i]);
					participants.append(";");
				}
				participantString = participants.toString();
				return true;
			}
			return false;
		}

		@Override
		public synchronized boolean removeParticipant(String name) {
			logger2.info("removing client:" + name);

			if (participantList.contains(name)) {
				participantList.remove(name);

				// create a new participantString from participants array
				StringBuffer participants = new StringBuffer();
				participants.append("participants=");
				String[] participantArray = new String[participantList.size()];
				participantList.toArray(participantArray);
				for (int i = 0; i < participantArray.length; i++) {
					participants.append(participantArray[i]);
					participants.append(";");
				}
				participantString = participants.toString();
				return true;
			}
			return false;
		}

		@Override
		public synchronized String getParticipants() {
			return participantString;
		}

	}

	/**
	 * The Chats stores and manages all topics and incoming messages regarding
	 * those topics. It adds/removes topics, saves messages, retrieves the last
	 * ten messages on a given topic, and in case that a complete refresh is
	 * requested: it retrieves the last ten messages and the topic list as well.
	 * 
	 * @see Chats
	 * @author � ibneco, Rheinfelden
	 * @version
	 */
	private class Chats implements ch.fhnw.kvan.chat.IChats {
		private final List<String> topicList = Collections
				.synchronizedList(new ArrayList<String>());
		private String topicString = "";
		private final Map<String, List<String>> topicMessagesMap = Collections
				.synchronizedMap(new HashMap<String, List<String>>());

		private final Logger logger3;

		public Chats() {
			logger3 = Logger.getLogger(Chats.class);
		}

		@Override
		public synchronized boolean addTopic(String topic) {
			// add topic to topicList if not yet present
			if (!topicList.contains(topic)) {
				topicList.add(topic);
				Collections.sort(topicList);

				// add new topic to the topicMessagesMap
				topicMessagesMap.put(topic,
						Collections.synchronizedList(new ArrayList<String>()));

				logger3.info("adding new topic:" + topic);

				// create a new topicString from topics array
				StringBuffer topics = new StringBuffer();
				topics.append("topics=");
				String[] topicArray = new String[topicList.size()];
				topicList.toArray(topicArray);
				for (int i = 0; i < topicArray.length; i++) {
					topics.append(topicArray[i]);
					topics.append(";");
				}
				topicString = topics.toString();
				return true;
			}
			return false;
		}

		@Override
		public synchronized boolean removeTopic(String topic) {
			if (topicList.contains(topic)) {
				topicList.remove(topic);
				topicMessagesMap.remove(topic);
				logger3.info("removing topic:" + topic);

				// create a new topicString from topics array
				StringBuffer topics = new StringBuffer();
				topics.append("topics=");
				String[] topicArray = new String[topicList.size()];
				topicList.toArray(topicArray);
				for (int i = 0; i < topicArray.length; i++) {
					topics.append(topicArray[i]);
					topics.append(";");
				}
				topicString = topics.toString();

				return true;
			}
			return false;
		}

		@Override
		public synchronized String getTopics() {
			return topicString;
		}

		@Override
		public synchronized boolean addMessage(String topic, String msg) {
			if (topicList.contains(topic)) {
				if (topicMessagesMap.containsKey(topic)) {
					List<String> messages = topicMessagesMap.get(topic);
					messages.add(msg);
					return true;
				}
			}
			return false;
		}

		// triggered by client
		@Override
		public synchronized String getMessages(String topic) {
			if (topicMessagesMap.containsKey(topic)) {
				List<String> messages = topicMessagesMap.get(topic);
				ListIterator<String> it = messages
						.listIterator(messages.size());
				int i = 0;

				StringBuffer msgs = new StringBuffer();
				msgs.append("messages=");
				while (it.hasPrevious() && i <= 10) {
					i++;
					msgs.append(it.previous());
					msgs.append(";;");
				}
				return msgs.toString();
			}
			return ("messages=");
		}
	}

}
