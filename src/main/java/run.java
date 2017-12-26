import java.io.File;
import java.io.IOException;
import java.util.Map;

public class run {

    public static void main(String[] args) throws IOException, InterruptedException {
        int fileCount = 1;

        File file = new File("c://Users//public//data1.txt");
        if (file.exists()){
            fileCount++;
            int i =2;
            while (true){
                File temp = new File("c://Users//public//data"+String.valueOf(i)+".txt");
                //File temp = new File("d://data"+String.valueOf(i)+".txt");
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

                Map<String,String> res = data.getData(reader.readFileByLines());
                String name = res.get("name");
                String url = "http://10.10.240.34/xsxjs.aspx?xkkh="+res.get("urlxkkh")+"&xh=152011088";
                http net = new http();
                int count=1;
                while(true){
                    net = new http();
                    String result = null;
                    try {
                        result = net.httpPost(res,url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    //System.out.println(result);

                    String title = result.substring(result.indexOf("课程名称"),result.indexOf("课程名称")+12);

                    result = result.substring(result.indexOf("alert(")+7,result.indexOf("</script>")-3);



                    System.out.println("返回结果："+result+count+title);

                    if (result.indexOf("成功")>0)
                        break;

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
