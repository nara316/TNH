package go.travel.dnh.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Integer nno;
    private String title;
    private String content;
    private String filename;
    private String filepath;
}
