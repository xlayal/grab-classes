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
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
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
