package com.nile.apiservice.noti.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.nile.apiservice.noti.dto.NotiDto;
import com.nile.apiservice.noti.entity.Noti;
import com.nile.apiservice.noti.exception.exceptions.NotiNotFoundException;
import com.nile.apiservice.noti.repository.NotiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotiService {

    /**
     * todo: annotation "@RequiredArgsConstructor + private final..." 과 @Autowired 차이
     */
    
    @Autowired NotiRepository notiRepository;

    public NotiService() {
        super();
    }

    @Transactional(readOnly = true)
    public NotiDto getNoti(long id) {
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

        // ! https://goddaehee.tistory.com/209
        // return this.notiRepository.findById(id).orElseGet(Noti::new);
        Noti noti = this.notiRepository.findById(id).orElseThrow(NotiNotFoundException::new);
        return new NotiDto(noti);
    }

    @Transactional(readOnly = true)
    public List<NotiDto> getAllNotis() {
        // return this.notiRepository.findAll();
        return this.notiRepository.findAll().stream().map(
            // todo: 아래의 구문을 NotiDto의 생성자를 사용하여 변경하는 방법으로 변경
            // noti -> new NotiDto(noti.getId(), noti.getSenderuserid(), noti.getSenderusernm(), noti.getNotitype(), noti.getNotititle(), noti.getNotibody(), noti.getRecipientuserid(), noti.getRecipientusernm(), noti.getCreatedt())
            noti -> new NotiDto(noti)
        )
        .collect(Collectors.toList());
    }

    public List<NotiDto> getSearchNotiCreateDtBetween(Date startdate, Date enddate) {
        return this.notiRepository.findByCreatedtBetween(startdate, enddate).stream().map(
            noti -> new NotiDto(noti)
        )
        .collect(Collectors.toList());
    }

    public List<NotiDto> getSearchNotiCreateDtAfter(Date startdate) {
        return this.notiRepository.findByCreatedtAfter(startdate).stream().map(
            noti -> new NotiDto(noti)
        )
        .collect(Collectors.toList());
    }

    public List<NotiDto> getSearchNotiCreateDtBefore(Date enddate) {
        return this.notiRepository.findByCreatedtBefore(enddate).stream().map(
            noti -> new NotiDto(noti)
        )
        .collect(Collectors.toList());
    }

    public NotiDto getQcNotiById(long id) {
        Noti noti = this.notiRepository.qcGetById(id); // todo: exception throw 필요
        return new NotiDto(noti);
    }

    public List<NotiDto> getQcNotiByIds(Set<Long> ids) {
        return this.notiRepository.qcGetByIds(ids).stream().map(
            noti -> new NotiDto(noti)
        )
        .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotiDto> getQcAllNotis() {
        return this.notiRepository.qcGetAllNoti().stream().map(
            // todo: 아래의 구문을 NotiDto의 생성자를 사용하여 변경하는 방법으로 변경
            noti -> new NotiDto(noti)
        )
        .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Object[]> getQcNotiByTitleOrBody(String searchstr) {
        return this.notiRepository.qcGetNotiByTitleOrBody(searchstr);
    }

    @Transactional(readOnly = true) // todo: @Transactional 사용하는 경우와 옵션들
    public List<Object[]> getQnNotiByTitleOrBody(String searchstr) {
        return this.notiRepository.qnGetNotiByTitleOrBody(searchstr);
    }

    public int getQnProcGetTotalNotiCount() {
        return this.notiRepository.qnprocGetTotalNotiCount();
    }

    public int getProcGetTotalNotiCount() {
        return this.notiRepository.procGetTotalNotiCount();
    }

    public int getQnProcGetNotiId(long id) {
        return this.notiRepository.qnprocGetNotiId(id);
    }

    public Map<String, ?> getQnProcGetNotiInfo(long id) {
        return this.notiRepository.qnprocGetNotiInfo(id);
    }

    public Map<String, ?> getNProcGetNotiInfo(long id) {
        return this.notiRepository.nprocGetNotiInfo(id);
    }

    public Map<String, ?> getQnProcGetNotiInfoTbl(long id) {
        return this.notiRepository.qnprocGetNotiInfoTbl(id);
    }

    public Map<String, ?> getNProcGetNotiInfoTbl(long id) {
        return this.notiRepository.nprocGetNotiInfoTbl(id);
    }

    public Object getEmspqNProcGetNotiInfoTbl(long id) {
        return this.notiRepository.emspqGetNotiInfo(id);
    }

    public Object getEmspqNProcGetNotiInfoCursor(long id) {
        return this.notiRepository.emspqGetNotiInfoCursor(id);
    }

    public Object getEmspqNProcGetNotiInfoCursor2(long id) {
        return this.notiRepository.emspqGetNotiInfoCursor2(id);
    }

    /**
     * ! 미사용(2021.06.23)
     */
    public Noti addNoti(Noti noti) {
        return this.notiRepository.save(noti);
    }
}
