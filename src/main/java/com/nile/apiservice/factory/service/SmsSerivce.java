package com.nile.apiservice.factory.service;

import java.util.List;

import com.nile.apiservice.factory.model.entity.Sms;
import com.nile.apiservice.factory.model.entity.sms.Failed;
import com.nile.apiservice.factory.model.entity.sms.Inbox;
import com.nile.apiservice.factory.model.entity.sms.Other;
import com.nile.apiservice.factory.model.entity.sms.Sent;
import com.nile.apiservice.factory.repository.SmsRepository;
import com.nile.apiservice.factory.repository.sms.FailedRepository;
import com.nile.apiservice.factory.repository.sms.InboxRepository;
import com.nile.apiservice.factory.repository.sms.OtherRepository;
import com.nile.apiservice.factory.repository.sms.SentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSerivce {
    
    @Autowired SmsRepository smsRepository;
    @Autowired InboxRepository inboxRepository;
    @Autowired SentRepository sentRepository;
    @Autowired FailedRepository failedRepository;
    @Autowired OtherRepository otherRepository;

    public SmsSerivce() {
        super();
    }

    public Inbox addInbox(Inbox inbox) {
        return this.inboxRepository.save(inbox);
    }

    public Sent addSent(Sent sent) {
        return this.sentRepository.save(sent);
    }

    public Failed addFailed(Failed failed) {
        return this.failedRepository.save(failed);
    }

    public List<Inbox> fetchInboxes() {
        return this.inboxRepository.findAll();
    }

    public List<Sms> fetchAllSms() {
        return this.smsRepository.findAll();
    }

    public List<Other> fetchOthers() {
        return this.otherRepository.findAll();
    }
}
