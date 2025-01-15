package com.dzapata.foro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationLinksDTO {
    private String previous;
    private String next;
}
