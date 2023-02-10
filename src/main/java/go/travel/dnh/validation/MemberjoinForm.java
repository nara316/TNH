package go.travel.dnh.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    //@NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$\n")
    private String mem_pwd;

    //@NotBlank
    @Pattern(regexp = "^[가-힣]{2,5}$")
    private String mem_name;

    //@NotBlank
    @Pattern(regexp = "^[0-9]{10,11}$")
    private String mem_phone;
    private String mem_gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")
    private Date mem_birth;

}
