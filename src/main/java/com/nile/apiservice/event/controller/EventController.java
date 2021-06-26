package com.nile.apiservice.event.controller;

import java.time.LocalDateTime;

import com.nile.apiservice.event.events.CustomEventPublisher;
import com.nile.apiservice.event.events.GenericEventPublisher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EventController {
    private final CustomEventPublisher customEventPublisher;
    private final GenericEventPublisher<String> genericEventPublisher;
    // private final DomainService domainService;
    // private final UserRepository userRepository;

    @GetMapping("/event")
    public String event(@RequestParam String message) {
        customEventPublisher.publish(message);
        return "finished";
    }

    @GetMapping("/event/generic")
    public String event(@RequestParam String message, @RequestParam boolean success) {
        genericEventPublisher.publish(message, success);
        return "finished";
    }

    // @GetMapping("/event/domain")
    // public String domainEvent(@RequestParam String userId) {
    //     User user = User.builder().userNo(1).userId("happydaddy").age(30).created(LocalDateTime.now()).build();
    //     userRepository.save(user);
    //     domainService.addAge(userId);
    //     return "finished";
    // }
}
