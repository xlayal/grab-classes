import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class run {

    private static final String  DATA_PATH  = "c://Users//public//";
    private static final String  GRAB_URL   = "http://10.10.240.34/xsxjs.aspx";
    private static final Integer SLEEP_TIME = 200;

    private static class worker extends Thread {

        private String readText(int i){
            File file = new File(DATA_PATH + "data"+String.valueOf(i)+".txt");
            StringBuilder res = new StringBuilder();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new java.io.FileReader(file));
                String tempString = null;
                while ((tempString = reader.readLine()) != null) {
                    res.append(tempString+"\n");
                }
                reader.close();
                return res.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "null";
        }

        private HashMap<String, String> getFileData(int i) {
            String res = readText(i);
            HashMap<String, String> map = new HashMap<String,String>(16);
            for (String temp : res.split("\n")){
                String[] str = temp.split("::");
                map.put(str[0],str[1]);
            }
            return map;
        }

        private String doPost(Map<String,String> param,String url,String Cookie) throws IOException {
            Connection con = Jsoup.connect(url).timeout(6000);
            param.forEach(con::data);
            con.header("Cookie", Cookie);
            Document doc = con.post();
            return doc.toString();
        }

        int workerId = 0;

        private worker(int id) {
            this.workerId = id;
        }

        @Override
        public void run() {
            int dataNum = workerId;
            //读取文件内容并转换成map
            HashMap<String, String> param = getFileData(dataNum);
            //构造url
            String url = GRAB_URL + "?xkkh=" + param.get("urlxkkh") + "&xh=" + param.get("xh");
            //标记请求次数
            int count = 1;
            //请求的结果
            String result = "";
            String cookie = param.get("cookie");

            while (true) {
                try {
                    result = doPost(param,url,cookie);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //简易获取请求返回的课程名称
                String title = result.substring(result.indexOf("课程名称"), result.indexOf("课程名称") + 17);
                //获取请求结果，即提取alert内容
                result = result.substring(result.indexOf("alert(") + 7, result.indexOf("</script>") - 3);
                //打印
                System.out.println("工人:" + workerId + " 返回结果：" + result + count + title);
                //抢课成功
                if (result.indexOf("成功") > 0)
                    break;
                //休息200毫秒接着干活
                count = count + 1;
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        int fileCount = 1;
        while (new File(DATA_PATH + "data" + fileCount + ".txt").exists()) {
            new worker(fileCount).run();
            fileCount++;
        }

    }

}
