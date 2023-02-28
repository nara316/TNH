package go.travel.dnh.domain.notice;

import go.travel.dnh.domain.air.Pagination;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NoticePageResponse<T> {
    private List<T> list = new ArrayList<>();
    private NoticePagination noticePagination;

    public NoticePageResponse(List<T> list, NoticePagination noticePagination) {
        this.list = list;
        this.noticePagination = noticePagination;
    }
}
