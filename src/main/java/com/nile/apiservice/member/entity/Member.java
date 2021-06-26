package com.nile.apiservice.member.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nile.apiservice.event.events.CustomEvent;
import com.nile.apiservice.member.encryt.Salt;
import com.nile.apiservice.member.entity.enums.UserRole;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "rest", name = "members")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Member { //  extends AbstractAggregateRoot<Member> 사용해도 됨 --> 코드량이 줄어듬

    // -- AbstractAggregateRoot
    @Transient
    private final Collection<CustomEvent> customEvents;

    @DomainEvents
    public Collection<CustomEvent> events() {
        return customEvents;
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        customEvents.clear();
    }
    // --

    @Id
    @GeneratedValue
    private int seq;

    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotNull
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="social_id")
    private SocialData social;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_NOT_PERMITTED;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt_id")
    private Salt salt;

    public Member() {
        customEvents = new ArrayList<>();
    }

    public Member(@NotBlank String username, @NotBlank String password, @NotBlank String name, @NotBlank String email, @NotBlank String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.customEvents = new ArrayList<>();
    }

    public void reName() {
        this.name = "reName";
        customEvents.add(new CustomEvent(this, "user name changed : " + this.name));
        // registerEvent(new CustomEvent(this, "user name changed : " + this.name)); // extends AbstractAggregateRoot<Member> 사용
    }

    @Override
    public String toString() {
        return "User{" +
                "seq=" + seq +
                ", id='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
