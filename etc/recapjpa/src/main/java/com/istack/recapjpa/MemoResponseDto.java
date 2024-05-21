package com.istack.recapjpa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoResponseDto {
    private final String userName;
    private final String contents;

    public MemoResponseDto(Memo memo) {
        this.userName = memo.getUsername();
        this.contents = memo.getContents();
    }
}
