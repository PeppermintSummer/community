package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {  //页码需要展示、封装
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;       //数组当前页
    private List<Integer> pages=new ArrayList<>();//数组表示当前多少页
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage=totalPage;
        this.page=page;
        //分页逻辑
        pages.add(page);//把当前页加进去
        for (int i = 1; i <= 3; i++) {
            if (page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //是否展示上一页
        if (page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }

        if(page==totalPage)
        {
            showNext=false;
        }
        else{
            showNext=true;
        }

        //展示第1页
        if (pages.contains(1)){
            showFirstPage= false;
        }else{
            showFirstPage=true;
        }
        //展示最后一页
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
