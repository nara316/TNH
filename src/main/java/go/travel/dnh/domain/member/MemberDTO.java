package go.travel.dnh.domain.member;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Integer mno;
    private String mem_id;
    private String mem_pwd;
    private String mem_name;
    private String mem_phone;
    private String mem_gender;
    private Date mem_birth;
    private Date mem_in_date;
    private Date mem_up_date;
    private String role_set;
    private boolean mem_sns;

}
