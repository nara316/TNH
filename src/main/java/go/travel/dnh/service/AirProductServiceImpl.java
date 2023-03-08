package go.travel.dnh.service;

import go.travel.dnh.domain.User.LoginUser;
import go.travel.dnh.domain.air.*;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import go.travel.dnh.repository.AirProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AirProductServiceImpl implements AirProductService{

    private final AirProductRepository airProductRepository;
    private final MemberLoginService memberLoginService;

    private int ar_res_cnt;

    @Override
    public List<AirProductDTO> getListAdmin() {
        return airProductRepository.adminAirProductList();
    }

    @Override
    public int write(AirProductDTO dto) {
        return airProductRepository.write(dto);
    }

    @Override
    public int modify(AirProductDTO dto) {
        return airProductRepository.modify(dto);
    }

    @Override
    public int remove(Integer ano) {
        return airProductRepository.delete(ano);
    }

    @Override
    public AirProductDTO read(Integer ano) {
        return airProductRepository.read(ano);
    }

    @Override
    public PagingResponse<AirProductDTO> getList(SearchDTO sch) {
        return airProductRepository.airProductList(sch);
    }

    @Override
    public PagingResponse<AirProductDTO> getSearchOneWayList(SearchDTO sch) {
        return airProductRepository.airProductSchOneWay(sch);
    }

    @Override
    public PagingResponse<AirProductDTO> getSearchFromList(SearchDTO sch) {
        return airProductRepository.airProductSchFrom(sch);
    }
    @Override
    public PagingResponse<AirProductDTO> getSearchToList(SearchDTO sch) {
        return airProductRepository.airProductSchTo(sch);
    }

    @Override
    public PagingResponse<AirProductDTO> indexSearch(SearchDTO sch) {
        return airProductRepository.indexSearch(sch);
    }

    @Override
    public PagingResponse<AirProductDTO> OneWaySort(SearchDTO sch) {
        return airProductRepository.OneWaySort(sch);
    }

    @Override
    public PagingResponse<AirProductDTO> roundSortOut(SearchDTO sch) {
        return airProductRepository.roundSortOut(sch);
    }

    @Override
    public PagingResponse<AirProductDTO> roundSortIn(SearchDTO sch) {
        return airProductRepository.roundSortIn(sch);
    }

    @Override
    public List<AirportDTO> getListAirport() {
        return airProductRepository.airportList();
    }

    @Override
    public List<AirlineDTO> getListAirline() {
        return airProductRepository.airlineList();
    }


    //예약
    @Override
    public AirProductDTO readRes(Integer ano) {
        return airProductRepository.resRead(ano);
    }

    @Override
    @Transactional
    public void reservation(AirReservationDTO dto, ReservationDetail detail, @AuthenticationPrincipal LoginUser loginUser, Authentication authentication) {

        Integer mno = memberLoginService.findMember(loginUser,authentication).getMno();
        dto.setMno(mno);
        Integer ano = dto.getOut_ano();
        //rno 만들기
        //예약날짜
        LocalDate date = LocalDate.now();
        String dateString = date.toString().replace("-","").substring(2,8);
        //회원번호
        String mnoString = String.format("%03d", mno);
        //예약할 항공권 상품번호(가는편 기준)
        String anoString = String.format("%03d", ano);
        //0~99까지 임의의 수 만들기
        Random r = new Random();
        int ran= r.nextInt(99);
        String ranString = String.format("%02d", ran);
        //위의 세가지 합체
        StringBuffer sb = new StringBuffer();
        sb.append(dateString);
        sb.append(mnoString);
        sb.append(anoString);
        sb.append(ranString);
        String resNumString = String.valueOf(sb);
        Long rno = Long.parseLong(resNumString);
        dto.setRno(rno);
        System.out.println(dto.getRno());
        /*예약 db 인서트*/
        airProductRepository.reservation(dto);
        /*detail 인서트*/
        for (int i = 0; i < dto.getArp_count(); i++) {
            ReservationDetail resDetail = detail.getDetailList().get(i);
            resDetail.setRno(rno);
            airProductRepository.resDetail(detail.getDetailList().get(i));
        }

        /*ar_res_cnt 개수 업데이트*/
        if(dto.getIn_ano()!=null){
            ar_res_cnt = airProductRepository.readCnt(dto.getIn_ano())-dto.getArp_count();
            airProductRepository.updateResCnt(dto.getIn_ano(),ar_res_cnt);
        }
        ar_res_cnt = airProductRepository.readCnt(ano)-dto.getArp_count();
        airProductRepository.updateResCnt(ano,ar_res_cnt);
    }


}
