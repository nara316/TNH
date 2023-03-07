package go.travel.dnh.domain.notice;

import go.travel.dnh.domain.air.Pagination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeSearchDTO {
    private int page; //현재 페이지 번호
    private int recordSize; // 페이지 당 출력할 게시글 수
    private int pageSize; // 페이지 사이즈
    private String Keyword; // 검색 키워드
    private String searchType; // 검색 유형
    private NoticePagination noticePagination;

    public NoticeSearchDTO() {
        this.page = 1;
        this.recordSize = 5;
        this.pageSize = 10;
    }



}
