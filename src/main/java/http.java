import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public class http {
    public String httpPost(Map<String,String> map,String url) throws IOException {

        //移除map里的学号和url内容
        map.remove("urlxkkh");
        map.remove("xh");

        //获取请求连接
        Connection con = Jsoup.connect(url).timeout(6000);
        //遍历生成参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //添加参数
            con.data(entry.getKey(), entry.getValue());
        }
        //插入cookie
        con.header("Cookie", map.get("cookie"));
        Document doc = con.post();
        return doc.toString();
    }
}
