package com.nile.apiservice.sample.service;

import java.util.List;
import java.util.stream.Collectors;

import com.nile.apiservice.sample.exception.exceptions.SampleNotFoundException;
import com.nile.apiservice.sample.model.dto.SampleDTO;
import com.nile.apiservice.sample.model.entity.Sample;
import com.nile.apiservice.sample.repository.SampleRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;

    @Transactional(readOnly = true)
    public List<SampleDTO> getSamples() {
        return this.sampleRepository.findAll().stream()
        .map(sample -> new SampleDTO(sample.getSampleTitle(), sample.getSampleContent(), sample.getViewcount()))
        .collect(Collectors.toList());
    }

    @Transactional
    public SampleDTO getSample(long sampleId) {
        Sample sample = this.sampleRepository.findById(sampleId).orElseThrow(SampleNotFoundException::new);
        return new SampleDTO(sample.getSampleTitle(), sample.getSampleContent(), sample.getViewcount());
    }

    @Transactional
    public SampleDTO addSample(String sampleTitle, String sampleContent) {
        Sample sample = this.sampleRepository.save(new Sample(sampleTitle, sampleContent));
        return new SampleDTO(sample.getSampleTitle(), sample.getSampleContent(), sample.getViewcount());
    }

    @Transactional
    public SampleDTO updateSample(long sampleId, String sampleTitle, String sampleContent) {
        Sample sample = this.sampleRepository.findById(sampleId).orElseThrow(SampleNotFoundException::new);
        sample.updateSample(sampleTitle, sampleContent);
        return new SampleDTO(sample.getSampleTitle(), sample.getSampleContent(), sample.getViewcount());
    }

    @Transactional
    public void deleteSample(long sampleId) {
        this.sampleRepository.deleteById(sampleId);
    }
}
