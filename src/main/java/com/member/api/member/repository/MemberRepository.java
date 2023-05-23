package com.member.api.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.member.api.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

  MemberEntity findByUserIdOrEmail(String userId, String email);

  MemberEntity findByName(String name);
}
