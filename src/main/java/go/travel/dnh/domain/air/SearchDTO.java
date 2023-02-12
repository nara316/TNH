package go.travel.dnh.domain.air;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

    private int pageNum;
    private int amount;
    private int pageSize;
    private String keyword;
    private String searchType;
    private Pagination pagination;

    public SearchDTO(){
        this.pageNum = 1;
        this.amount = 5;
        this.pageSize = 10;
    }

}
