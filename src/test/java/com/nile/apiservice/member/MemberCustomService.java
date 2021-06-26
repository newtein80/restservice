package com.nile.apiservice.member;

import static org.mockito.Mockito.times;

import com.nile.apiservice.event.events.CustomEvent;
import com.nile.apiservice.event.events.CustomEventListener;
import com.nile.apiservice.member.entity.Member;
import com.nile.apiservice.member.repository.MemberRepository;
import com.nile.apiservice.member.service.MemberEventService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MemberCustomService {
    
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberEventService memberEventService;

    @MockBean
    private CustomEventListener customEventListener;

    @Test
    void memberCustomSerivceEventTest() {
        Member member = Member.builder().username("username").password("password").name("name").email("email").address("address").build();
        memberRepository.save(member);

        memberEventService.addAge("asdfet");

        // Mockito.verify(customEventListener, times(1)).onApplicationEvent(any(CustomEvent.class));
    }
}
