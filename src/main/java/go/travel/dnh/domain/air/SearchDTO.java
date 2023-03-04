package go.travel.dnh.domain.air;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

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
    private Integer airline;
    private Pagination pagination;

    private String sortValue;

    public SearchDTO(){
        this.pageNum = 1;
        this.amount = 5;
        this.pageSize = 5;
    }

}
