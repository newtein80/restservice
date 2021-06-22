package com.nile.apiservice.noti.service;

import java.util.List;

import com.nile.apiservice.noti.entity.Noti;
import com.nile.apiservice.noti.repository.NotiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotiService {
    
    @Autowired NotiRepository notiRepository;

    public NotiService() {
        super();
    }

    public Noti getNoti(long id) {
        /**
         * .get()의 경우 결과값이 null일 경우 NoSuchElementException 발생
         * orElseThrow()를 통해 값이 없을 경우 예외를 던져주거나 orElse , orElseGet를 통해 값이 없을 경우 값을 지정
         * 
         * Repository에서 Optional을 반환하는 경우 원하는 값이 있으면 원하는 객체로 받고 없으면 Exception처리를 하는 패턴을 사용
         * - .isPresent() 사용
         * - .orElseThrow(IllegalArgumentException::new) 사용
         * 
         * -- 아래와 같이 사용
         * Optional<UserVO> user = userRepository.findById(userId);
         * user.ifPresent(u -> {
         *     vo.setUsername(u.getUsername());
         *     vo.setUserNm(u.getName());
         * });
         */
        return this.notiRepository.findById(id).orElseGet(Noti::new);
    }

    public List<Noti> getAllNotis() {
        return this.notiRepository.findAll();
    }

    public Noti addNoti(Noti noti) {
        return this.notiRepository.save(noti);
    }
}
