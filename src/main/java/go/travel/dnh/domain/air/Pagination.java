package go.travel.dnh.domain.air;

import lombok.Getter;

@Getter
public class Pagination {
    private int totalRecordCount;   // 전체 데이터 수
    private int totalPageCount;     // 전체 페이지 수
    private int startPage;          // 첫 페이지 번호
    private int endPage;            // 끝 페이지 번호
    private int limitStart;         // LIMIT 시작 위치
    private boolean existPrevPage;  // 이전 페이지 존재 여부
    private boolean existNextPage;  // 다음 페이지 존재 여부

    public Pagination(int totalRecordCount, SearchDTO sch) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.calculation(sch);
        }
    }

    private void calculation(SearchDTO sch) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / sch.getAmount()) + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (sch.getPageNum() > totalPageCount) {
            sch.setPageNum(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((sch.getPageNum() - 1) / sch.getPageNum()) * sch.getPageNum() + 1;

        // 끝 페이지 번호 계산
        endPage = totalPageCount;

        // LIMIT 시작 위치 계산
        limitStart = (sch.getPageNum() - 1) * sch.getAmount();

        // 이전 페이지 존재 여부 확인
        if(sch.getPageNum() == 1) {
            existPrevPage = false;
        } else {
            existPrevPage = true;
        }

        // 다음 페이지 존재 여부 확인
        if(sch.getPageNum() < endPage ){
            existNextPage = true;
        } else {
            existNextPage = false;
        }
    }

}
