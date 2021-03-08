package ru.geekbrains.server;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@Log4j2
public class ClientHandler {
    private static final Logger eventsLog = LogManager.getLogger("eventsLog");
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        // /auth login1 password1
                        String[] subStrings = str.split(" ");
                        if (subStrings.length >= 3 && subStrings[0].equals("/auth")) {
                            String nickFromDB = SQLHandler.getNickByLoginAndPassword(subStrings[1], subStrings[2]);
                            if (nickFromDB != null) {
                                sendMsg("/authok");
                                server.subscribe(this);
                                nickname = nickFromDB;

                                ThreadContext.put("user", nickname);
                                //покажем историю чата
                                sendMsg(StoryManager.readStory());
                                break;
                            }else {
                                eventsLog.warn(subStrings[1]+" Ошибка авторизации");
                            }
                        }
                    }

                    while (true) {
                        String str = in.readUTF();
                        //System.out.println("Сообщение от клиента: " + str);
                        //System.out.println(ThreadContext.getContext());

                        if (str.length()>6 && str.startsWith("/name")){
                            String newNickName = str.substring(6).trim();
                            if (newNickName.length()>0){
                                String changeResult = SQLHandler.changeNickName(nickname, newNickName);
                                if (changeResult.equals("OK")){
                                    server.broadcastMsg("user#"+nickname+ " установил имя: "+newNickName);
                                    nickname = newNickName;
                                } else
                                    log.warn(changeResult);
                                continue;
                            }
                        }

                        if (str.equals("/end")) {
                            break;
                        }
                        log.info(str);
                        server.broadcastMsg(nickname + ": " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
