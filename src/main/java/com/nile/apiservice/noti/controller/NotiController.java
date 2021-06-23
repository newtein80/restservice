package com.nile.apiservice.noti.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.nile.apiservice.noti.dto.NotiDto;
import com.nile.apiservice.noti.service.NotiService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/nileapi/noti")
public class NotiController {

    private final NotiService notiService;

    /**
     * 알림 현황 조회
     * @return List<NotiDto> 알림 리스트
     */
    @Operation(summary = "알림 현황 - jpa : findAll", description = "<big>알림 현황</big>을 조회<br />- JPA default",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
        }
    )
    @GetMapping("/jpa")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NotiDto> getAllNotis() {
        return this.notiService.getAllNotis();
    }

    /**
     * 알림 상세 조회
     * @param id 알림key
     * @return NotiDto 알림 상세 정보
     */
    @Operation(summary = "샘플 상세 - jpa : findById", description = "<strong>샘플 상세 내용</strong>을 조회") // * description 에 html 태그 사용가능
    @GetMapping("/jpa/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotiDto getNoti(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getNoti(id);
    }

    @Operation(summary = "알림 현황 - jpa : findAll", description = "<big>알림 현황</big>을 조회<br />- JPA default<br />startdate ~ enddate --> between<br />startdate ~ --> after Dates after the startdate<br />~ enddate --> before Dates before the enddate<br />both do not exist --> 7 days ago from the current date",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error !!"),
            @ApiResponse(responseCode = "404", description = "Not Found !!")
        }
    )
    @GetMapping("/jpa/searchdate")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NotiDto> getNotiByCreateDt(
        @Parameter(name = "검색시작일", required = false, example = "2021-06-22", description = "-7") @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid Optional<Date> startdate,
        @Parameter(name = "검색종료일", required = false, example = "2021-06-27", description = "0" ) @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") @Valid Optional<Date> enddate
    ) {
        // todo: 잘못된 포맷의 날짜형식을 넘겨받았을 때 처리
        /**
         * 검색시작일 종료일 모두 존재: startdate ~ enddate --> between
         * 검색시작일만 존재          : startdate ~         --> after  Dates after the startdate
         * 검색종료일만 존재          :           ~ enddate --> before Dates before the enddate
         * 검색시작일 종료일 모두 없음:                     --> 7 days ago from the current date
         */

        if (startdate.isPresent() && enddate.isPresent()) {
            return this.notiService.getSearchNotiCreateDtBetween(startdate.get(), enddate.get());
        }
        else {
            if (startdate.isPresent() && !enddate.isPresent()) {
                // 시작일만 존재
                return this.notiService.getSearchNotiCreateDtAfter(startdate.get());

            }
            else if (!startdate.isPresent() && enddate.isPresent()) {
                // 종료일만 존재
                return this.notiService.getSearchNotiCreateDtBefore(enddate.get());
            }
            else {
                return this.notiService.getSearchNotiCreateDtBetween(setDateCalculate(new Date(), "start", -30), setDateCalculate(new Date(), "end", 0));
            }
        }
        
        // return this.notiService.getSearchNotiCreateDtBetween(startdate.orElse(new Date()), enddate.orElse(new Date()));
        
    }

    @Operation(summary = "샘플 상세 - @Query : getById", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/qc/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotiDto getQcNoti(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getQcNotiById(id);
    }

    @Operation(summary = "알림 현황 - @Query : getAll", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qc")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NotiDto> getQcAllNotis() {
        return this.notiService.getQcAllNotis();
    }

    @Operation(summary = "알림 현황 - @Query : getByTitleOrBody", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qc/searchstr")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Object[]> getQcNotiByTitleOrBody(
        @Parameter(name = "검색문자열", required = false, example = "검색어") @RequestParam String searchstr
    ) {
        return this.notiService.getQcNotiByTitleOrBody(searchstr);
    }

    @Operation(summary = "알림 현황 - @Query : getByIds", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qc/ids")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NotiDto> getQcNotiByIds(
        @Parameter(name = "알림KEY 배열", required = true, example = "1,2,3") @RequestBody Set<Long> ids
    ) {
        return this.notiService.getQcNotiByIds(ids);
    }

    @Operation(summary = "알림 현황 - @Query : getByTitleOrBody - navtice query", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qn/searchstr")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Object[]> getQnNotiByTitleOrBody(
        @Parameter(name = "검색문자열", required = false, example = "검색어") @RequestParam String searchstr
    ) {
        return this.notiService.getQnNotiByTitleOrBody(searchstr);
    }

    @Operation(summary = "알림 현황 - @Query : gettotalnoticount (procdure) - navtice query", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qnproc/totalnoticount")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public int getQnProcTotalNotiCount() {
        return this.notiService.getQnProcGetTotalNotiCount();
    }

    @Operation(summary = "알림 현황 - @Query : gettotalnoticount (procdure) - navtice query", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/proc/totalnoticount")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public int getProcTotalNotiCount() {
        return this.notiService.getProcGetTotalNotiCount();
    }

    @Operation(summary = "알림 현황 - @Query : gettotalnoticount (procdure) - navtice query", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/proc/notiid/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public int getQnProcNotiId(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getQnProcGetNotiId(id);
    }

    @Operation(summary = "샘플 상세 - @Query : getById", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/qnproc/noti/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, ?> getQnProcNotiInfo(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getQnProcGetNotiInfo(id);
    }

    @Operation(summary = "샘플 상세 - @Query : getById", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/nproc/noti/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, ?> getNProcNotiInfo(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getNProcGetNotiInfo(id);
    }

    @Operation(summary = "샘플 상세 - @Query : getById", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/qnproc/notitbl/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, ?> getQnProcNotiInfoTbl(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getQnProcGetNotiInfoTbl(id);
    }

    /**
     * ! return Empty !!!
     * @param id
     * @return
     */
    @Operation(summary = "샘플 상세 - @Query : getById", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/nproc/notitbl/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, ?> getNProcNotiInfoTbl(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getNProcGetNotiInfoTbl(id);
    }

    @Operation(summary = "샘플 상세 - @Query : getById", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/em/nproc/notitbl/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getEmspqNProcNotiInfoTbl(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getEmspqNProcGetNotiInfoTbl(id);
    }

    @Operation(summary = "샘플 상세 - @Query : getById", description = "<strong>샘플 상세 내용</strong>을 조회")
    @GetMapping("/em/nproc/noticsr/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getEmspqNProcNotiInfoCursor(
        @Parameter(name = "샘플 KEY", required = true, example = "1")  @PathVariable long id
    ) {
        return this.notiService.getEmspqNProcGetNotiInfoCursor(id);
    }

    @Operation(summary = "알림 현황 - querydsl : getByIds", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qdsl/searchtitle")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NotiDto> getQdslNotiByTitle(
        @Parameter(name = "알림 제목", required = true, example = "검색어") @RequestParam String title
    ) {
        return this.notiService.getQdslNotiByTitle(title);
    }

    @Operation(summary = "알림 현황 - querydsl : getByIds", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qdslcustom/searchtitle")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NotiDto> getQdslNotiByTitleInCustom(
        @Parameter(name = "알림 제목", required = true, example = "검색어") @RequestParam String title
    ) {
        return this.notiService.getQdslNotiByTitleInCustom(title);
    }

    @Operation(summary = "알림 현황 - querydsl : getByIds", description = "<big>알림 현황</big>을 조회<br />- JPA default")
    @GetMapping("/qdslonly/searchtitle")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<NotiDto> getQdslNotiByTitleInOnlyRepository(
        @Parameter(name = "알림 제목", required = true, example = "검색어") @RequestParam String title
    ) {
        return this.notiService.getQdslNotiByTitleInOnlyRepository(title);
    }

    public Date setDateCalculate(Date inputdate, String datetype, int addday) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        if (datetype.equals("end")) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.add(Calendar.DAY_OF_YEAR, addday);
        }
        else {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.add(Calendar.DAY_OF_YEAR, addday);
        }

        today = calendar.getTime();

        return today;
    }
    
}
