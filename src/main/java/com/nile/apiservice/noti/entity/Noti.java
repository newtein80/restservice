package com.nile.apiservice.noti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nile.apiservice.noti.dto.NotiInfoResultSet;

import org.springframework.format.annotation.DateTimeFormat;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "rest", name="t_notification")
@SqlResultSetMapping(
    name = "defNotiInfoResultset"
    , classes = {
        @ConstructorResult(
            targetClass = NotiInfoResultSet.class,
            columns = {
                @ColumnResult(name = "noti_title"),
                @ColumnResult(name = "noti_body")
            }
        )
    }
)
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "noti.getnotidetailbynamedproc",
        procedureName = "rest.getnotidetailbyqnproc",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_noti_id", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_noti_title", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_noti_body", type = String.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "noti.getnotidetailreturntablesetbynamedproc",
        procedureName = "rest.getnotidetailreturntableset", resultSetMappings = {"defNotiInfoResultset"},
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_noti_id", type = Long.class)
        }
    )
})
public class Noti {
    
    @Id
    @Column(name = "noti_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo: 현재 sse v7.0 의 t_notification 테이블의 id 는 시퀀스로 정의되어 있지 않음 (sequence로 변경 할 경우 관련 sp 모두 변경 필요)
    // @JsonIgnore // * json response 될 때 무시되는 변수
    private Long id;

    @NotNull(message = "The sent user ID is required.")
    @Size(min = 10, max = 32, message = "Sender user id accepts only upto 32 character and minimum 10 characters")
    @Column(name = "sender_user_id")
    private String senderuserid;

    @NotNull(message = "The sent user name is required.")
    @Size(min = 10, max = 64, message = "Sender user name accepts only upto 64 character and minimum 10 characters")
    @Column(name = "sender_user_nm")
    private String senderusernm;

    @Column(name = "noti_type", columnDefinition = "character varying(10) NULL")
    private String notitype;

    @Size(min = 1, max = 50, message = "Notification title accepts only upto 50 character and minimum 1 characters")
    @Column(name = "noti_title")
    private String notititle;

    @Column(name = "noti_body", columnDefinition = "TEXT")
    private String notibody;

    @Size(max = 512, message = "Notification title accepts only upto 50 character and minimum 1 characters")
    @Column(name = "noti_href")
    private String notihref;

    @NotNull(message = "The reception user ID is required.")
    @Size(min = 10, max = 32, message = "Reception user id accepts only upto 32 character and minimum 10 characters")
    @Column(name = "recipient_user_id")
    private String recipientuserid;

    @NotNull(message = "The reception user name is required.")
    @Size(min = 10, max = 64, message = "Reception user name accepts only upto 64 character and minimum 10 characters")
    @Column(name = "recipient_user_nm")
    private String recipientusernm;

    @Column(name ="is_unread", columnDefinition = "character varying(1) not null default 'Y'")
    private String isunread;

    @Column(name ="is_hidden", columnDefinition = "character varying(1) not null default 'N'")
    private String ishidden;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="create_dt", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdt;

    @Column(name ="noti_detail", columnDefinition = "TEXT")
    private String notidetail;

    public void setReadStatus() {
        this.isunread = "N";
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
        this.senderuserid = sender_user_id;
        this.senderusernm = sender_user_nm;
        this.notitype = noti_type;
        this.notititle = noti_title;
        this.notibody = noti_body;
        this.recipientuserid = recipient_user_id;
        this.recipientusernm = recipient_user_nm;
        this.isunread = "Y";
        this.ishidden = "N";
    }

    

}
