package com.example.foodproject.wishlist.service;

import com.example.foodproject.naver.NaverClient;
import com.example.foodproject.naver.dto.SearchImageReq;
import com.example.foodproject.naver.dto.SearchLocalReq;
import com.example.foodproject.wishlist.dto.WishListDto;
import com.example.foodproject.wishlist.entity.WishListEntity;
import com.example.foodproject.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query) {
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);
        if (searchLocalRes.getTotal() > 0) {
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);
            var searchImageRes = naverClient.searchImage(searchImageReq);
            if (searchImageRes.getTotal() > 0) {
                var imageItem = searchImageRes.getItems().stream().findFirst().get();
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());
                return result;
            }
        }
        return new WishListDto();

    }

    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }

    private WishListEntity dtoToEntity(WishListDto wishListDto) {
        var dto = new WishListEntity();
        dto.setIndex(wishListDto.getIndex());
        dto.setTitle(wishListDto.getTitle());
        dto.setCategory(wishListDto.getCategory());
        dto.setAddress(wishListDto.getAddress());
        dto.setRoadAddress(wishListDto.getRoadAddress());
        dto.setHomePageLink(wishListDto.getHomePageLink());
        dto.setImageLink(wishListDto.getImageLink());
        dto.setVisit(wishListDto.isVisit());
        dto.setVisitCount(wishListDto.getVisitCount());
        dto.setLastVisitDate(wishListDto.getLastVisitDate());
        return dto;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity) {
        var entity = new WishListDto();
        entity.setIndex(wishListEntity.getIndex());
        entity.setTitle(wishListEntity.getTitle());
        entity.setCategory(wishListEntity.getCategory());
        entity.setAddress(wishListEntity.getAddress());
        entity.setRoadAddress(wishListEntity.getRoadAddress());
        entity.setHomePageLink(wishListEntity.getHomePageLink());
        entity.setImageLink(wishListEntity.getImageLink());
        entity.setVisit(wishListEntity.isVisit());
        entity.setVisitCount(wishListEntity.getVisitCount());
        entity.setLastVisitDate(wishListEntity.getLastVisitDate());
        return entity;
    }

    public List<WishListDto> findAll() {
        return wishListRepository
                .findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }


    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
