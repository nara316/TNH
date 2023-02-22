package go.travel.dnh.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class updateForm {
    private String mem_id;
    private String mem_pwd;
    private String mem_phone;
}
