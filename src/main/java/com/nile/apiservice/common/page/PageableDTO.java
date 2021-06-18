package com.nile.apiservice.common.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageableDTO {
    private Integer page;
    private Integer size;
    private Integer totalItems;
    private List<SortPair<String, SortOption>> sorts;
}
