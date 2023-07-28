package com.playground.api.sample.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.playground.api.sample.entity.SampleUserEntity;
import com.playground.api.sample.entity.specification.SampleSpecification;
import com.playground.api.sample.model.SampleUserResponse;
import com.playground.api.sample.model.SampleUserSearchRequest;
import com.playground.api.sample.repository.SampleUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService {
  private final SampleSpecification sampleSpecification;
  private final SampleUserRepository sampleRepository;
  private final ModelMapper modelMapper;

  public Page<SampleUserResponse> getUserPageList(SampleUserSearchRequest req, Pageable pageable) {
    SampleUserEntity sampleUserEntity = modelMapper.map(req, SampleUserEntity.class);

    Page<SampleUserEntity> sampleRepositoryPage = sampleRepository.findAll(sampleSpecification.searchCondition(sampleUserEntity), pageable);

    List<SampleUserResponse> sampleUserList =
        sampleRepositoryPage.getContent().stream().map(entity -> modelMapper.map(entity, SampleUserResponse.class)).toList();

    return new PageImpl<>(sampleUserList, sampleRepositoryPage.getPageable(), sampleRepositoryPage.getTotalElements());
  }
}
