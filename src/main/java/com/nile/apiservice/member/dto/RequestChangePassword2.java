package com.nile.apiservice.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChangePassword2 {
    String username;
    String password;
}