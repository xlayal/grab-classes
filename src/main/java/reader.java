import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class reader {

    private static int count = 1;

    public static synchronized String readFileByLines() {

        File file = new File("c://Users//public//data"+String.valueOf(count)+".txt");
        count++;
        StringBuilder res = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
               res.append(tempString+"\n");
            }
            reader.close();
            return res.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    return "null";
                }
            }
        }
        return "null";
    }
}
