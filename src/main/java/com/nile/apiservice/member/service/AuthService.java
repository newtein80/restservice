package com.nile.apiservice.member.service;

import com.nile.apiservice.member.dto.RequestSocialData;
import com.nile.apiservice.member.entity.Member;
import com.nile.apiservice.member.entity.enums.UserRole;

import javassist.NotFoundException;

public interface AuthService {

    final String REDIS_CHANGE_PASSWORD_PREFIX="CPW";

    void signUpUser(Member member);

    void signUpSocialUser(RequestSocialData member);

    Member loginSocialUser(String id, String type) throws NotFoundException;

    Member loginUser(String id, String password) throws Exception;

    Member findByUsername(String username) throws NotFoundException;

    void verifyEmail(String key) throws NotFoundException;

    void sendVerificationMail(Member member) throws NotFoundException;

    void modifyUserRole(Member member, UserRole userRole);

    boolean isPasswordUuidValidate(String key);

    void changePassword(Member member, String password) throws NotFoundException;

    void requestChangePassword(Member member) throws NotFoundException;
}