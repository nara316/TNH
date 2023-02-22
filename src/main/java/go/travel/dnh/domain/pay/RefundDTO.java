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
public class RefundDTO {

    private String rfno;
    private String pno;
    private Integer mno;
    private String  rf_reason;
    private Date rf_date;
    private String rf_state;

}
