package go.travel.dnh.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberjoinForm {

    @NotBlank
    @Email
    private String mem_id;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")
    private String mem_pwd;

    @NotBlank
    @Pattern(regexp = "^[가-힣]{2,5}$")
    private String mem_name;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,11}$")
    private String mem_phone;

    @NotBlank
    private String mem_gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mem_birth;

}
