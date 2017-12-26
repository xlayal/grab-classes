import java.io.File;
import java.io.IOException;
import java.util.Map;

public class run {

    public static void main(String[] args) throws IOException, InterruptedException {
        int fileCount = 1;

        //读取文件，判断有几个文件，有多少就开多少个线程抢课
        File file = new File("c://Users//public//data1.txt");
        if (file.exists()){
            fileCount++;
            int i =2;
            while (true){
                File temp = new File("c://Users//public//data"+String.valueOf(i)+".txt");
                if (temp.exists()) {
                    fileCount++;
                    i++;
                }else{
                    break;
                }
            }
        }


        for (int i =1 ;i<fileCount;i++)
        new Thread(new Runnable() {
            @Override
            public void run() {
                //读取文件内容并转换成map
                Map<String,String> res = data.getData(reader.readFileByLines());
                //构造url
                String url = "http://10.10.240.34/xsxjs.aspx?xkkh="+res.get("urlxkkh")+"&xh="+res.get("xh");
                http net;
                //标记请求次数
                int count=1;
                while(true){
                    net = new http();
                    String result = null;
                    try {
                        result = net.httpPost(res,url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //简易获取请求返回的课程名称
                    String title = result.substring(result.indexOf("课程名称"),result.indexOf("课程名称")+17);
                    //获取请求结果，即提取alert内容
                    result = result.substring(result.indexOf("alert(")+7,result.indexOf("</script>")-3);
                    //打印
                    System.out.println("返回结果："+result+count+title);
                    //抢课成功
                    if (result.indexOf("成功")>0)
                        break;
                    //休息200毫秒接着干活
                    count=count+1;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




    }

}
