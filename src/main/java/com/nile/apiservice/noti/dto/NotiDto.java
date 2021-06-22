package com.nile.apiservice.noti.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "알림 정보 DTO", description = "알림 정보 - 요청/응답")
public class NotiDto {
    @NotBlank
    @NotNull
    @Schema(name="알림KEY", description = "알림PK", required = true, example = "ex) 1", nullable = false)
    private Long id;

    @NotBlank
    @NotNull
    @Schema(name="보낸 사용자ID", description = "알림을 보낸 사용자 ID", required = true, example = "ex) admin", nullable = false)
    private String sender_user_id;

    @NotBlank
    @NotNull
    @Schema(name="보낸 사용자명", description = "알림을 보낸 사용자명", required = true, example = "ex) 관리자", nullable = false)
    private String sender_user_nm;

    // todo: 추후 enum으로 변경해야 함
    @Schema(name="알림 타입", description = "알림 타입 (interval)", required = false, example = "ex) interval", nullable = true, defaultValue = "interval")
    private String noti_type;

    @NotBlank
    @NotNull
    @Schema(name="알림 제목", description = "알림 제목", required = true, example = "ex) 알림 제목입니다.", nullable = false, minLength = 1, maxLength = 50)
    private String noti_title;

    @NotBlank
    @NotNull
    @Schema(name="알림 내용", description = "알림 내용", required = true, example = "ex) 알림 내용입니다.", nullable = false, minLength = 1, maxLength = 50)
    private String noti_body;

    @Schema(name="알림 수신 사용자 ID", description = "알림을 받는 사용자 ID", required = false, example = "ex) admin", nullable = true, defaultValue = "all")
    private String recipient_user_id; // todo: 사용자 id 배열로 변경해야 함

    @Schema(name="알림 수신 사용자명", description = "알림을 받는 사용자명", required = false, example = "ex) 관리자", nullable = true, defaultValue = "전체")
    private String recipient_user_nm;

    @Schema(name="알림 생성일", description = "알림 생성일", required = false, example = "ex) 2021", nullable = true)
    private Date create_dt;
}
