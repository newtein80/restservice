package com.nile.apiservice.common.page;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class SortPair<T, U> {
    private T first;
    private U second;

    public static <T, U> SortPair of(T first, U second) {
        return SortPair.builder().first(first).second(second).build();
    }
}
