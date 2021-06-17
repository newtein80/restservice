package com.nile.apiservice.item.model.dto;

import java.util.Date;

import com.nile.apiservice.item.model.entity.Item;
import com.nile.apiservice.item.model.enums.ItemType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(access = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Schema(name = "아이템 상세 정보 DTO", description = "Item's Data Transfer Object")
public class ItemDto {

    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private Long itemId;
    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private String title;
    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private String content;
    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private Long views;
    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private Date createDt;
    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private ItemType itemType;
    @Schema(name="sampleTitle", description = "제목", required = true, example = "ex) 제목", nullable = false)
    private Boolean topItem;

    public static ItemDto of(Item item) {
        return ItemDto.builder()
        .itemId(item.getItemId())
        .title(item.getTitle())
        .content(item.getContent())
        .views(item.getViews())
        .itemType(item.getItemType())
        .topItem(item.isTopItem())
        .build();
    }
}
