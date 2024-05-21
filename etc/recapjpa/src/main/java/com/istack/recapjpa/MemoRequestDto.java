package com.istack.recapjpa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoRequestDto {
    private final String userName;
    private final String contents;
}
