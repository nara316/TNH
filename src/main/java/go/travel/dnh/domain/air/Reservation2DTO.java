package go.travel.dnh.domain.air;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Reservation2DTO {
        private Integer rno;
        private Integer ano;
        private Integer price;
        private Integer ea;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime res_date;
}
