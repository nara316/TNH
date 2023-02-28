package go.travel.dnh.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalForm {
    private int withdrawal_mno;
    private String withdrawal_id;
    private String withdrawal_pwd;
}
