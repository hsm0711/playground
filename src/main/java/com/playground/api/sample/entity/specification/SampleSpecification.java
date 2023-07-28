package com.playground.api.sample.entity.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.playground.api.sample.entity.SampleUserEntity;

@Component
public class SampleSpecification {
  public Specification<SampleUserEntity> searchCondition(SampleUserEntity sampleEntity) {
    Specification<SampleUserEntity> spec = (root, query, criteriaBuilder) -> null;

    if (sampleEntity != null) {
      if (StringUtils.isNotBlank(sampleEntity.getUserId())) {
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("userId"), sampleEntity.getUserId() + "%"));
      }

      if (StringUtils.isNotBlank(sampleEntity.getName())) {
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), sampleEntity.getName() + "%"));
      }

      if (StringUtils.isNotBlank(sampleEntity.getEmail())) {
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), sampleEntity.getEmail() + "%"));
      }
    }

    return spec;
  }
}
