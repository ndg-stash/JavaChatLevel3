package ru.geekbrains.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("(yyyy.MM.dd HH:mm:ss)   ");

    public Server() {
        try {
            SQLHandler.connect();
            ServerSocket serverSocket = new ServerSocket(8189);
            clients = new Vector<>();
            while (true) {
                System.out.println("Ждем подключения клиента");
                Socket socket = serverSocket.accept();
                ClientHandler c = new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            SQLHandler.disconnect();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fullMsg = sdf.format(timestamp) + msg;
        for (ClientHandler c : clients) {
            c.sendMsg(fullMsg);
        }
        StoryManager.saveStory(fullMsg);

        Logger log = LogManager
        log.log(info);
    }
}
