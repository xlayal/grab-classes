import java.util.HashMap;
import java.util.Map;

public class data {
    public static Map<String, String> getData(String data){
        Map<String, String> map = new HashMap<String,String>(16);
        for (String temp : data.split("\n")){
            String[] str = temp.split("::");
            map.put(str[0],str[1]);
        }
        return map;
    }
}
