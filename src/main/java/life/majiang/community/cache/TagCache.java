package life.majiang.community.cache;

import life.majiang.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","Java","python","html","css","asp.net","swift","shell","sql","c/cpp"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("Spring","django","flask","structs","Spring Boot"));
        tagDTOS.add(framework);

        TagDTO db=new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","h2","SqlServer","oracle","sqlite","redis","mangodb","nosql"));
        tagDTOS.add(db);

        TagDTO tool=new TagDTO();
        tool.setCategoryName("工具");
        tool.setTags(Arrays.asList("git/github","VSCode","VS2019","Idea","eclipse","maven","svn","sublime"));
        tagDTOS.add(tool);

        TagDTO other=new TagDTO();
        other.setCategoryName("其他");
        other.setTags(Arrays.asList("平常","趣事","小分享","算法","游戏"));
        tagDTOS.add(other);

        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());//2层的数组拿出来
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
