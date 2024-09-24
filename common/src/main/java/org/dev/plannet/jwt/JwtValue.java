package org.dev.plannet.jwt;

import lombok.Data;

@Data
public class JwtValue {
    private String sub;
    private String auth;
    private String id;
    private String email;
    private Long exp;
}
