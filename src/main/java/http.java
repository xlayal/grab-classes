import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class http {
    public String httpPost(Map<String,String> map,String url) throws IOException {

    
        map.remove("urlxkkh");
        map.remove("xh");

        //获取请求连接
        Connection con = Jsoup.connect(url).timeout(6000);
        //遍历生成参数
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //添加参数
                con.data(entry.getKey(), entry.getValue());
            }
        }
        //插入cookie（头文件形式）
        con.header("Cookie", map.get("cookie"));
        Document doc = con.post();
        return doc.toString();
    }
}
