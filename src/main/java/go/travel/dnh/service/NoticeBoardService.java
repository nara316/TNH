package go.travel.dnh.service;

import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.mapper.NoticeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticeBoardService {

    private final NoticeBoardMapper noticeBoardMapper;
    public void write(NoticeDTO noticeDTO, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        noticeDTO.setN_filename(fileName);
        noticeDTO.setN_filepath("/files/"+fileName);
        noticeBoardMapper.save(noticeDTO);
    }
    public List<NoticeDTO> boardList() {
        return noticeBoardMapper.findAll();
    }

    public NoticeDTO boardView(Integer nno) {
        return noticeBoardMapper.select(nno);
    }

    public void boardDelete(Integer nno) {
        noticeBoardMapper.delete(nno);
    }

    public void modifyBoard(Integer nno) {
        noticeBoardMapper.update(nno);
    }
}
