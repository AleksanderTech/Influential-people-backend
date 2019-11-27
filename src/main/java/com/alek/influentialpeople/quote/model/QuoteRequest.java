package com.alek.influentialpeople.quote.model;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class QuoteRequest {

    private String content;
    private String heroName;
}
