package com.nile.apiservice.member.service;

import javax.transaction.Transactional;

import com.nile.apiservice.event.events.CustomEventPublisher;
import com.nile.apiservice.member.repository.MemberRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberEventService {
    private final MemberRepository memberRepository;
    private final CustomEventPublisher customEventPublisher;

    @Transactional
    public void addAge(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(entity -> {
                    // entity.addAge();
                    memberRepository.save(entity);
                    customEventPublisher.publish("member name is " + entity.getName());
                });
    }
}
