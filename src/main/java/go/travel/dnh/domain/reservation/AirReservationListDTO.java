package go.travel.dnh.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirReservationListDTO {

    private Long rno;
    private Integer mno;
    private String mem_name;
    private String ar_from;
    private String ar_to;
    private String al_logo_uri;
    private String al_logo_name;
    private String al_name;
    private String ar_fl_no;
    private String agop_code;
    private String arp_state;
    private Integer arp_count;
    private Integer arp_price;
    private Integer ar_time;
    private Integer in_ano;
    private String ko_name;
    private String en_lname;
    private String en_fname;
    private Date birth;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arp_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ar_from_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ar_to_date;


}
