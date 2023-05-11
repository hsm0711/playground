package com.member.api.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.member.api.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String>{

	@Query(value = "SELECT user_id, password, name, reg_no FROM MEMBER WHERE name = :name and reg_no = :reg_no", nativeQuery = true)
	MemberEntity findMemberByNmReg(@Param(value = "name") String name, @Param(value = "reg_no") String regNo);

	MemberEntity findByName(String name);

	@Query(value = "SELECT user_id, password, name, reg_no FROM MEMBER WHERE user_id = :user_id and password = :password", nativeQuery = true)
	Optional<MemberEntity> findMemberByIdPw(@Param(value = "user_id") String userId, @Param(value = "password") String password);

}
