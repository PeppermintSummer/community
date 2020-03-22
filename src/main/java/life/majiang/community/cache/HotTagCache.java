package life.majiang.community.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class HotTagCache {
    private  Map<String,Integer> tags=new HashMap<>(); //final不希望修改

//    public static synchronized Map<String,Integer> getTags(){
//        return tags;
//    }
}
