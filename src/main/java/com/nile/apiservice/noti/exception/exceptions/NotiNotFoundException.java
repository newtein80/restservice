package com.nile.apiservice.noti.exception.exceptions;

public class NotiNotFoundException extends RuntimeException{
    public NotiNotFoundException() {
        super("존재하지 않는 알림입니다.");
    }
}