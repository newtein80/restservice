package com.nile.apiservice.noti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "rest", name="t_notification")
public class Noti {
    
    @Id
    @Column(name = "noti_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo: 현재 sse v7.0 의 t_notification 테이블의 id 는 시퀀스로 정의되어 있지 않음 (sequence로 변경 할 경우 관련 sp 모두 변경 필요)
    @JsonIgnore // * json response 될 때 무시되는 변수
    private Long id;

    @NotNull(message = "The sent user ID is required.")
    @Size(min = 10, max = 32, message = "Sender user id accepts only upto 32 character and minimum 10 characters")
    private String sender_user_id;

    @NotNull(message = "The sent user name is required.")
    @Size(min = 10, max = 64, message = "Sender user name accepts only upto 64 character and minimum 10 characters")
    private String sender_user_nm;

    @Column(columnDefinition = "character varying(10) NULL")
    private String noti_type;

    @Size(min = 1, max = 50, message = "Notification title accepts only upto 50 character and minimum 1 characters")
    private String noti_title;

    @Column(columnDefinition = "TEXT")
    private String noti_body;

    @Size(max = 512, message = "Notification title accepts only upto 50 character and minimum 1 characters")
    private String noti_href;

    @NotNull(message = "The reception user ID is required.")
    @Size(min = 10, max = 32, message = "Reception user id accepts only upto 32 character and minimum 10 characters")
    private String recipient_user_id;

    @NotNull(message = "The reception user name is required.")
    @Size(min = 10, max = 64, message = "Reception user name accepts only upto 64 character and minimum 10 characters")
    private String recipient_user_nm;

    @Column(name ="is_unread", columnDefinition = "character varying(1) not null default 'Y'")
    private String is_unread;

    @Column(name ="is_hidden", columnDefinition = "character varying(1) not null default 'N'")
    private String is_hidden;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date create_dt;

    @Column(columnDefinition = "TEXT")
    private String noti_detail;

    public void setReadStatus() {
        this.is_unread = "N";
    }

    /**
     * @param sender_user_id
     * @param sender_user_nm
     * @param noti_type
     * @param noti_title
     * @param noti_body
     * @param recipient_user_id
     * @param recipient_user_nm
     */
    public Noti(
            @NotNull(message = "The sent user ID is required.") @Size(min = 10, max = 32, message = "Sender user id accepts only upto 32 character and minimum 10 characters") String sender_user_id,
            @NotNull(message = "The sent user name is required.") @Size(min = 10, max = 64, message = "Sender user name accepts only upto 64 character and minimum 10 characters") String sender_user_nm,
            String noti_type,
            @Size(min = 1, max = 50, message = "Notification title accepts only upto 50 character and minimum 1 characters") String noti_title,
            String noti_body,
            @NotNull(message = "The reception user ID is required.") @Size(min = 10, max = 32, message = "Reception user id accepts only upto 32 character and minimum 10 characters") String recipient_user_id,
            @NotNull(message = "The reception user name is required.") @Size(min = 10, max = 64, message = "Reception user name accepts only upto 64 character and minimum 10 characters") String recipient_user_nm) {
        this.sender_user_id = sender_user_id;
        this.sender_user_nm = sender_user_nm;
        this.noti_type = noti_type;
        this.noti_title = noti_title;
        this.noti_body = noti_body;
        this.recipient_user_id = recipient_user_id;
        this.recipient_user_nm = recipient_user_nm;
        this.is_unread = "Y";
        this.is_hidden = "N";
    }

    

}
