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
    private String n_title;
    private String n_content;
    private String n_filename;
    private String n_filepath;
}
