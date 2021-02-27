import org.apache.commons.io.input.ReversedLinesFileReader;
import org.junit.Test;

import java.io.*;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Tests {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("(yyyy.MM.dd HH:mm:ss)");

    @Test
    public void tstDate () {

        //method 1
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        //method 2 - via Date
        Date date = new Date();
        //System.out.println(new Timestamp(date.getTime()));

        //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        //System.out.println(timestamp.getTime());

        //format timestamp
        //System.out.println(sdf.format(timestamp));

    }

    @Test
    public void tstFile(){
        File file = new File("src/test/java/tstFile.txt");
        String text = "тест записи!"; // строка для записи
        try(FileOutputStream fos=new FileOutputStream(file, true);
            PrintStream printStream = new PrintStream(fos))
        {
            printStream.println(text);
            System.out.println("Запись в файл произведена");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

//        try(PrintStream printStream = new PrintStream("notes3.txt"))
//        {
//            printStream.print("Hello World!");
//            printStream.println("Welcome to Java!");
//
//            printStream.printf("Name: %s Age: %d \n", "Tom", 34);
//
//            String message = "PrintStream";
//            byte[] message_toBytes = message.getBytes();
//            printStream.write(message_toBytes);
//
//            System.out.println("The file has been written");
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }

        try(PrintWriter pw = new PrintWriter(System.out))
        {
            pw.println("dfsdfsd");
        }

    }

    @Test
    public void readStory() throws IOException {
        File ChatStoryFile = new File("src/test/java/tstFile.txt");

        StringBuilder sb = new StringBuilder();
        String chatRow;
        int readLines = 100;

        ReversedLinesFileReader fileReader = new ReversedLinesFileReader(ChatStoryFile, StandardCharsets.UTF_8);
        for (int i = 0; i < readLines; i++) {
            chatRow = fileReader.readLine();
            if (chatRow == null){
                break;
            } else {
                sb.insert(0, chatRow + System.getProperty("line.separator"));
            }

            //sb.append(fileReader.readLine());
        }
        System.out.println(sb.toString());
    }

    @Test
    public void readStoryArr() throws IOException {
        File ChatStoryFile = new File("src/test/java/tstFile.txt");
        List<String> chatStory = new ArrayList<>();
        String chatRow;
        int readLines = 100;

        ReversedLinesFileReader fileReader = new ReversedLinesFileReader(ChatStoryFile, StandardCharsets.UTF_8);
        for (int i = 0; i < readLines; i++) {
            chatRow = fileReader.readLine();
            if (chatRow == null){
                break;
            } else {
                chatStory.add(chatRow+System.getProperty("line.separator"));
            }
        }
        System.out.println(chatStory.size());
        System.out.println(chatStory.toString());
    }


}
