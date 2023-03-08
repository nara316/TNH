package go.travel.dnh.service;


import go.travel.dnh.domain.air.*;
import go.travel.dnh.repository.AirProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class AirProductServiceImpl implements AirProductService{

    private final AirProductRepository airProductRepository;

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

}
