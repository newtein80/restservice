package com.nile.apiservice.member.service;

import com.nile.apiservice.member.entity.Member;

public interface MemberService {
    void signUpUser(Member member);

    Member loginUser(String id, String password);
}
