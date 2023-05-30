package com.member.api.sample.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import com.member.api.sample.entity.PagingEntity;
import com.member.api.sample.repository.PagingListRepository;
import com.member.api.sample.repository.PagingPageRepository;
import com.member.api.sample.repository.PagingSliceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagingService {
  private final PagingListRepository pagingListRepository;
  private final PagingPageRepository pagingPageRepository;
  private final PagingSliceRepository pagingSliceRepository;

  public List<PagingEntity> getPagingList(Pageable pageable) {
    return pagingListRepository.findAll(pageable);
  }

  public Page<PagingEntity> getPagingPage(Pageable pageable) {
    return pagingPageRepository.findAll(pageable);
  }

  public Slice<PagingEntity> getPagingSlice(Pageable pageable) {
    return pagingSliceRepository.findAll(pageable);
  }

}
