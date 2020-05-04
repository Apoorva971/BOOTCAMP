package com.ecommerceApp.ecommerceApp.Util;


import com.ecommerceApp.ecommerceApp.dtos.PagingAndSortingDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingAndSortingUtil {

    public Pageable getPageable(PagingAndSortingDto pagingAndSortingDto) {
        Pageable pageable;
        if (pagingAndSortingDto == null) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        } else {
            if (pagingAndSortingDto.getOrder() == "descending")
                pageable = PageRequest.of(pagingAndSortingDto.getMax(), pagingAndSortingDto.getOffset(),
                        Sort.by(pagingAndSortingDto.getSortField()).descending());
            else
                pageable = PageRequest.of(pagingAndSortingDto.getMax(), pagingAndSortingDto.getOffset(),
                        Sort.by(pagingAndSortingDto.getSortField()).ascending());
        }
        return pageable;
    }
}
