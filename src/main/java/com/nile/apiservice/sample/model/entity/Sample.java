package com.nile.apiservice.sample.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(schema = "rest", name = "t_sample_info")
public class Sample {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sampleId;

    @Column(name="title", length = 100, nullable = false)
    private String sampleTitle;

    @Column(name="content", columnDefinition = "TEXT", nullable = true)
    private String sampleContent;

    @Column(name="viewcount", nullable = false)
    private Long viewcount;

    public Sample(String sampleTitle, String sampleContent) {
        this.sampleTitle = sampleTitle;
        this.sampleContent = sampleContent;
        this.viewcount = (long) 0;
    }

    public void increaseViewCount() {
        this.viewcount++;
    }

    public void updateSample(String sampleTitle, String sampleContent) {
        this.sampleTitle = sampleTitle;
        this.sampleContent = sampleContent;
    }
}
