package go.travel.dnh.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private String n_uploadname;
    private String n_updatename;
    private Date n_in_date;
    private Date n_up_date;
}
