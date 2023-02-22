package go.travel.dnh.domain.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayDTO {

    private String pno;
    private Integer rno;
    private Integer mno;
    private Integer pay_tot_price;
    private Date pay_date;
    private String pay_state;
    private String pay_method;

}
