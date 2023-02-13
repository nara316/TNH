package go.travel.dnh.domain.air;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

    private int pageNum;
    private int amount;
    private int pageSize;
    private String fromDate;
    private String toDate;
    private String from;
    private String to;
    private Pagination pagination;


    public SearchDTO(){
        this.pageNum = 1;
        this.amount = 2;
        this.pageSize = 5;
    }

}
