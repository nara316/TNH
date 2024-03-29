package go.travel.dnh.service;

import go.travel.dnh.domain.notice.NoticeDTO;
import go.travel.dnh.domain.notice.NoticePageResponse;
import go.travel.dnh.domain.notice.NoticePagination;
import go.travel.dnh.domain.notice.NoticeSearchDTO;
import go.travel.dnh.mapper.NoticeBoardMapper;
import go.travel.dnh.repository.MemberLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService {
    private final MemberLoginRepository memberLoginRepository;
    private final NoticeBoardMapper noticeBoardMapper;
    @Override
    public void write(NoticeDTO noticeDTO, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        noticeDTO.setN_filename(fileName);
        noticeDTO.setN_filepath("/files/"+fileName);
        String adminId = SecurityContextHolder.getContext().getAuthentication().getName();
        String adminName = memberLoginRepository.findById(adminId).getMem_name();
        noticeDTO.setN_updatename(adminName);
        noticeDTO.setN_uploadname(adminName);
        noticeBoardMapper.save(noticeDTO);
    }
    @Override
    public NoticePageResponse<NoticeDTO> boardList(NoticeSearchDTO noticeSearchDTO) {
        int count = noticeBoardMapper.count(noticeSearchDTO);
        if(count < 1) {
            return new NoticePageResponse<>(Collections.emptyList(), null);
        }
        NoticePagination noticePagination = new NoticePagination(count, noticeSearchDTO);
        noticeSearchDTO.setNoticePagination(noticePagination);

        List<NoticeDTO> list = noticeBoardMapper.findAll(noticeSearchDTO);
        return new NoticePageResponse<>(list, noticePagination);
    }
    @Override
    public NoticeDTO boardView(Integer nno) {
        return noticeBoardMapper.select(nno);
    }

    @Override
    public void boardDelete(Integer nno) {
        noticeBoardMapper.delete(nno);
    }

    @Override
    public void modifyBoard(NoticeDTO noticeDTO, MultipartFile file)throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        noticeDTO.setN_filename(fileName);
        noticeDTO.setN_filepath("/files/"+fileName);
        String adminId = SecurityContextHolder.getContext().getAuthentication().getName();
        String adminName = memberLoginRepository.findById(adminId).getMem_name();
        noticeDTO.setN_updatename(adminName);
        noticeBoardMapper.update(noticeDTO);
    }

}
