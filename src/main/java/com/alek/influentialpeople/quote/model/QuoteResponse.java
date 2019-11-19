package com.alek.influentialpeople.quote.model;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class QuoteResponse {

    private long id;
    private String content;
    private String heroName;
}
