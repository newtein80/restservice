package com.nile.apiservice.sample.exception.exceptions;

public class SampleNotFoundException extends RuntimeException{
    public SampleNotFoundException() {
        super("존재하지 않는 게시글입니다.");
    }
}