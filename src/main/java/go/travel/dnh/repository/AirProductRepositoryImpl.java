package go.travel.dnh.repository;

import go.travel.dnh.domain.air.*;
import go.travel.dnh.domain.reservation.AirReservationDTO;
import go.travel.dnh.domain.reservation.ReservationDetail;
import go.travel.dnh.mapper.AdminAirProductMapper;
import go.travel.dnh.mapper.AirProductMapper;
import go.travel.dnh.mapper.AirReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirProductRepositoryImpl implements AirProductRepository{

    private final AdminAirProductMapper airProductAdminMapper;
    private final AirProductMapper airProductMapper;
    private final AirReservationMapper airReservationMapper;

    @Override
    public List<AirProductDTO> adminAirProductList() {
        return airProductAdminMapper.selectAllAirProduct();
    }

    @Override
    public int write(AirProductDTO dto) {
        return airProductAdminMapper.insert(dto);
    }

    @Override
    public int modify(AirProductDTO dto) {
        return airProductAdminMapper.update(dto);
    }

    @Override
    public int delete(Integer ano) {
        return airProductAdminMapper.delete(ano);
    }

    @Override
    public AirProductDTO read(Integer ano) {
        return airProductAdminMapper.select(ano);
    }

    @Override
    public PagingResponse<AirProductDTO> airProductList(SearchDTO sch) {

        int count = airProductMapper.count(sch);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(count, sch);
        sch.setPagination(pagination);

        List<AirProductDTO> list = airProductMapper.selectPro(sch);
        return new PagingResponse<>(list, pagination);

    }

    @Override
    public PagingResponse<AirProductDTO> airProductSchFrom(SearchDTO sch) {
        int count = airProductMapper.searchCntFrom(sch);

        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, sch);
        sch.setPagination(pagination);

        List<AirProductDTO> list = airProductMapper.searchAirFrom(sch);
        return new PagingResponse<>(list, pagination);
    }

    @Override
    public PagingResponse<AirProductDTO> airProductSchTo(SearchDTO sch) {
        int count = airProductMapper.searchCntTo(sch);

        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        Pagination pagination = new Pagination(count, sch);
        sch.setPagination(pagination);

        List<AirProductDTO> list = airProductMapper.searchAirTo(sch);
        return new PagingResponse<>(list, pagination);
    }
    @Override
    public List<AirportDTO> airportList() {
        return airProductMapper.selectAP();
    }



    ////예약
    //선택한 항공권 정보 읽어오기
    @Override
    public AirProductDTO resRead(Integer ano) {
        return airProductMapper.selectRes(ano);
    }
   //예약 저장하기
    @Override
    public int reservation(AirReservationDTO dto) {
        return airReservationMapper.insertReservation(dto);
    }
   //예약 상세정보 저장하기
    @Override
    public int resDetail(ReservationDetail detail) {
        return airReservationMapper.insertResDetail(detail);
    }

}
