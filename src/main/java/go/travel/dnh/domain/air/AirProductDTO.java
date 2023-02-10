package go.travel.dnh.domain.air;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class AirProductDTO {
    private Integer ano;
    private Integer al_code;
    private String ar_from;
    private String ar_to;
    private String agop_code;
    private Integer ar_fl_no;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ar_from_date;
    private Integer ar_time;
    private LocalDateTime ar_to_date;
    private Integer ar_price;
    private Integer ar_cnt;
    private Integer ar_res_cnt;
    private String ar_sales_state;
    private String in_user;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime in_date;
    private String up_user;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime up_date;

    private AirlineDTO AirlineDTO;
    private AirportDTO AirportDTO;
    private AirGradeOptionDTO AirGradeOptionDTO;

}
