package go.travel.dnh.domain.reservation;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AirReservationDTO {
    private Long rno;
    private Integer mno;
    private Integer out_ano;
    private Integer in_ano;
    private Integer arp_price;
    private Integer arp_count;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arp_date;
    private String arp_state;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arp_up_date;


}
