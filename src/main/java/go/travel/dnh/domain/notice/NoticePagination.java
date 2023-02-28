package go.travel.dnh.domain.notice;

import lombok.Getter;

@Getter
public class NoticePagination {
    private int totalRecordCount; //전체 데이터 수 ex)211
    private int totalPageCount; // 전체 페이지 수 ex)43
    private int startPage; // 첫 페이지
    private int endPage; // 끝 페이지
    private int limitStart; // limit 시작 위치
    private boolean existPrevPage; // 이전 페이지 존재여부
    private boolean existNextPage; // 다음 페이지 존재여부

    public NoticePagination(int totalRecordCount, NoticeSearchDTO noticeSearchDTO) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.calculation(noticeSearchDTO);
        }
    }

    private void calculation(NoticeSearchDTO noticeSearchDTO) {
        totalPageCount = ((totalRecordCount-1) / noticeSearchDTO.getRecordSize()) + 1;
        if (noticeSearchDTO.getPage() > totalPageCount) {
            noticeSearchDTO.setPage(totalPageCount);
        }
        startPage = ((noticeSearchDTO.getPage()-1)/ noticeSearchDTO.getPageSize()) * noticeSearchDTO.getPageSize()+ 1;
        endPage = startPage + noticeSearchDTO.getPageSize() - 1;
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }
        limitStart = (noticeSearchDTO.getPage() - 1) * noticeSearchDTO.getRecordSize();
        existPrevPage = startPage!= 1;
        existNextPage = (endPage * noticeSearchDTO.getRecordSize()) < totalRecordCount;
    }
}
