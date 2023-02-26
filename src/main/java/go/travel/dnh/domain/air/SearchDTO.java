package go.travel.dnh.domain.air;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

    private int pageNum;
    private int amount;
    private int pageSize;
    private String oneFromDate;
    private Integer oneFrom;
    private Integer oneTo;
    private String roundFromDate;
    private String roundToDate;
    private Integer roundFrom;
    private Integer roundTo;
    private Integer ea;
    private String airGrade;
    private Pagination pagination;


    public SearchDTO(){
        this.pageNum = 1;
        this.amount = 10;
        this.pageSize = 5;
    }

}
