package com.nile.apiservice.item.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.nile.apiservice.item.model.enums.ItemType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "rest", name = "t_item_info")
@TypeDef(name = "itemtype", typeClass = ItemType.class)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name="title", length = 100, nullable = false)
    private String title;

    @Column(name="content", columnDefinition = "TEXT", nullable = true)
    private String content;

    @Column(name="createDt")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createDt = new Date();

    @Column(name="views", nullable = false)
    private Long views;

    @Column(name = "topItem", columnDefinition = "boolean default false")
    private boolean topItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "itemType", nullable = true)
    @Type(type = "itemtype")
    @Builder.Default
    private ItemType itemType = ItemType.BOARD;

    @Transient
    private String no;

    public Item(String title, String content, Boolean topItem, ItemType itemType) {
        this.title = title;
        this.content = content;
        this.topItem = topItem;
        this.itemType = itemType;
        this.views = (long) 0;
    }

    public void increaseViews() {
        this.views++;
    }

    public void updateItem(String title, String content, Boolean topItem, ItemType itemType) {
        this.title = title;
        this.content = content;
        this.topItem = topItem;
        this.itemType = itemType;
    }
}