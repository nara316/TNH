package go.travel.dnh.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberjoinForm {

    private String mem_id;
    private String mem_pwd;
    private String mem_name;
    private String mem_phone;
    private String mem_gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mem_birth;

}
