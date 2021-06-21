package com.nile.apiservice.item.service;

import java.util.ArrayList;
import java.util.List;

import com.nile.apiservice.item.model.dto.ItemDto;
import com.nile.apiservice.item.repository.ItemRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemSerivce {
    private final ItemRepository itemRepository;

    public List<ItemDto> getAllItems(Pageable pageable) {
        // Pageable paging = PageRequest.of(pageNo-1, pageSize, Sort.by(sortBy));
        Page<ItemDto> pagedresult = this.itemRepository.findAll(pageable).map(
            item -> new ItemDto(item.getItemId(), item.getTitle(), item.getContent(), item.getViews(), item.getCreateDt(), item.getItemType(), item.isTopItem())
        );

        if (pagedresult.hasContent()) {
            return pagedresult.getContent();
        }
        else {
            return new ArrayList<ItemDto>();
        }
        
    }

    // public Page<ItemDto> getPagedAllItems(Pageable pageable) {
    //     return this.itemRepository.findByAllCustom(pageable).map(
    //         item -> new ItemDto(item.getItemId(), item.getTitle(), item.getContent(), item.getViews(), item.getCreateDt(), item.getItemType(), item.isTopItem())
    //     );
    // }

}