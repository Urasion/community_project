package community_project.main.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int pageIndex;
    private int pageAmount;

    public Criteria(int pageNum, int pageAmount) {
        pageIndex = (pageNum - 1) * pageAmount;
        this.pageNum = pageNum;
        this.pageAmount = pageAmount;
    }

    public Criteria() {
        this(1,10);
    }



}