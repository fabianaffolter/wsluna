package ch.fhnw.kvan.chat.socket.server;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConnectionListener implements Runnable {

	private CopyOnWriteArrayList<ConnectionHandler> conns = new CopyOnWriteArrayList<ConnectionHandler>();
	static Logger logger;
	String msg;

	public ConnectionListener() {
		logger = Logger.getLogger(ConnectionListener.class);
		logger.setLevel(Level.INFO);
	}

	public void addConnection(ConnectionHandler handler) {
		conns.add(handler);
		logger.info("ConnectionHandler " + handler
				+ " was added to the chatRoom.");
	}

	@Override
	public void run() {
		logger.info("Started Listener Service");
		while (true) {
			if (!conns.isEmpty()) {
				for (ConnectionHandler s : conns) {
					// waits for notify of setMessage on each ConnectionHandler
					try {
						msg = s.getMessage();
					} catch (InterruptedException e) {
					}
					logger.info("ConnectionHandler " + s
							+ " has a new message {" + msg + "}");

					if (msg != null && !msg.equals("")) {
						for (ConnectionHandler ch : conns) {
							logger.info("Broadcasting to " + ch);
							ch.println(msg);
						}
					}
				}
			}
		}
	}
}
