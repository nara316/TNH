package go.travel.dnh.domain.reservation;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetail {

    private Long rno;
    private String ko_name;
    private String en_lname;
    private String en_fname;
    private String birth;
    private String gender;

    private List<ReservationDetail> detailList;


}


