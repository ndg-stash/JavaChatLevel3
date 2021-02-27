package ru.geekbrains.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.commons.io.input.ReversedLinesFileReader;
import java.nio.charset.StandardCharsets;


public class StoryManager {
    private static File ChatStoryFile = new File("server/src/main/resources/chatStory.txt");
    public static void saveStory(String text){
        try(FileOutputStream fos = new FileOutputStream(ChatStoryFile, true);
            PrintStream printStream = new PrintStream(fos))
        {
            printStream.println(text);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String readStory() {
        StringBuilder sb = new StringBuilder();
        int readLines = 100;
        String chatRow;
        try {
            ReversedLinesFileReader fileReader = new ReversedLinesFileReader(ChatStoryFile, StandardCharsets.UTF_8);

            for (int i = 0; i < readLines; i++) {
                chatRow = fileReader.readLine();
                if (chatRow == null){
                    break;
                } else {
                    sb.insert(0, chatRow + System.getProperty("line.separator")); //добавляет строку в начало
                    //sb.append(fileReader.readLine()); //добавляет строку в конец
                }
            }

            fileReader.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return sb.toString();
    }
}
