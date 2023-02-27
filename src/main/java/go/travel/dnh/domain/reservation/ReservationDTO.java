package go.travel.dnh.domain.reservation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    /*뷰로부터 전달받을 값*/
    private Long rno; //예약번호
    private Integer mno; //회원번호
    private Integer arp_count; //예약수량
    private Integer arp_price; //항공번호의 가격
    private Date arp_date; //예약날짜

}
