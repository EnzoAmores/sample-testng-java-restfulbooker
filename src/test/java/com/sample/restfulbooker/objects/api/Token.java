package com.sample.restfulbooker.objects.api;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import lombok.Getter;

@Builder
@Getter
@Jacksonized
public class Token {
    private String username;
    private String password;
}