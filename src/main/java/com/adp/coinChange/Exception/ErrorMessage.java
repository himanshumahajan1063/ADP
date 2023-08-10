package com.adp.coinChange.Exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorMessage {

    private String code;
    private String message;
}
