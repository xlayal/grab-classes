import cn.hutool.core.io.file.FileReader;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class run {

    private static final String DATA_PATH = "c://Users//public//";
    private static final String GRAB_URL = "http://10.10.240.34/xsxjs.aspx";
    private static final Integer SLEEP_TIME = 200;

    private static Map<String, Object> getFileData(int i) {
        String res = new FileReader(DATA_PATH + "data" + i + ".txt").readString();
        Map<String, Object> map = new HashMap<String, Object>();
        for (String column : res.split("\n")) {
            String[] temp = column.split("::");
            map.put(temp[0], temp[1]);
        }
        return map;
    }

    private static class worker extends Thread {

        int fileNum = 0;

        private worker(int fileNum) {
            this.fileNum = fileNum;
        }

        @Override
        public void run() {
            System.out.println(fileNum);
            int dataNum = fileNum;
            //读取文件内容并转换成map
            Map<String, Object> res = getFileData(dataNum);
            res.forEach((k, v) -> {
                System.out.println(k);
            });
            //构造url
            String url = GRAB_URL + "?xkkh=" + res.get("urlxkkh") + "&xh=" + res.get("xh");
            //标记请求次数
            int count = 1;
            //请求的结果
            String result;
            while (true) {
                result = HttpUtil.post(url, res);
                //简易获取请求返回的课程名称
                String title = result.substring(result.indexOf("课程名称"), result.indexOf("课程名称") + 17);
                //获取请求结果，即提取alert内容
                result = result.substring(result.indexOf("alert(") + 7, result.indexOf("</script>") - 3);
                //打印
                System.out.println("工人:" + fileNum + " 返回结果：" + result + count + title);
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
