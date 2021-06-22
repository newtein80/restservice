package com.nile.apiservice.factory.controller;

import java.util.List;

import com.nile.apiservice.factory.model.entity.Sms;
import com.nile.apiservice.factory.model.entity.sms.Failed;
import com.nile.apiservice.factory.model.entity.sms.Inbox;
import com.nile.apiservice.factory.model.entity.sms.Other;
import com.nile.apiservice.factory.model.entity.sms.Sent;
import com.nile.apiservice.factory.service.SmsSerivce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/sms")
public class SmsController {
    private final SmsSerivce smsSerivce;

    @PostMapping("/addinbox")
    public Inbox addInbox(
        @RequestBody Inbox inbox
    ) {
        return this.smsSerivce.addInbox(inbox);
    }

    @PostMapping("/addsent")
    public Sent addSent(
        @RequestBody Sent sent
    ) {
        return this.smsSerivce.addSent(sent);
    }

    @PostMapping("/addfailed")
    public Failed addFailed(
        @RequestBody Failed failed
    ) {
        return this.smsSerivce.addFailed(failed);
    }

    @GetMapping("/inboxes")
    public List<Inbox> fetchInboxes() {
        return this.smsSerivce.fetchInboxes();
    }

    @GetMapping("/allsms")
    public List<Sms> fetchAllSms() {
        return this.smsSerivce.fetchAllSms();
    }

    @GetMapping("/others")
    public List<Other> fetchOthers() {
        return this.smsSerivce.fetchOthers();
    }
}
