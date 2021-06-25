package com.nile.apiservice.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChangePassword1 {
    private String username;
    private String email;

}