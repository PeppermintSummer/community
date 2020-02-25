package life.majiang.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;   //防止用户暴增越界
    private String bio;
    private String avatar_url;



    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
