package go.travel.dnh.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RequestUser {
    private String mem_id;
    private String mem_pwd;
}
