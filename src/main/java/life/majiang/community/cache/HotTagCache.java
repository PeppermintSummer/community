package life.majiang.community.cache;

import life.majiang.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class HotTagCache {
    //private  Map<String,Integer> tags=new HashMap<>(); //final不希望修改
    private List<String> hots=new ArrayList<>();

//    public static synchronized Map<String,Integer> getTags(){
//        return tags;
//    }

    //top(n)
    public void updateTags(Map<String,Integer> tags){
        int max=5;
        PriorityQueue<HotTagDTO> priorityQueue=new PriorityQueue<>(max);

        tags.forEach((name,priority)->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size()<max){
                priorityQueue.add(hotTagDTO);
            } else {
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0){
                    priorityQueue.poll(); //取出
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

        List<String> sortedTags = new ArrayList<>();


        HotTagDTO poll = priorityQueue.poll();
        //hots.add(poll.getName());
        while (poll!=null){
            sortedTags.add(0, poll.getName());
            poll = priorityQueue.poll();
        }
        hots = sortedTags;
        //System.out.println(hots);
    }
}
