package com.nile.apiservice.item.controller;

import java.util.List;

import com.nile.apiservice.item.model.dto.ItemDto;
import com.nile.apiservice.item.service.ItemSerivce;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/item")
public class ItemController {

    private final ItemSerivce itemSerivce;

    @Operation(summary = "아이템 현황", description = "<big>아이템 현황을 조회</big>")
    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(Pageable pageable) {
        // return this.sampleService.getSamples();
        List<ItemDto> itemDtos = this.itemSerivce.getAllItems(pageable);

        return new ResponseEntity<List<ItemDto>>(itemDtos, new HttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "아이템 현황2", description = "<big>아이템 현황을 조회</big>")
    @GetMapping("/paged")
    public Page<ItemDto> getPagedAllItems(
        @PageableDefault(page = 0, size = 20)
        @SortDefault.SortDefaults({
            @SortDefault(sort = "title", direction = Direction.DESC),
            @SortDefault(sort = "createDt", direction = Direction.DESC)
        })
        Pageable pageable
    ) {
        return this.itemSerivce.getPagedAllItems(pageable);
    }
}
