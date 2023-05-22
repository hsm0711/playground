package com.member.api.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.member.api.member.entity.MemberEntity;
import com.member.api.member.model.MemberInfoResponse;
import com.member.api.member.model.SignInRequest;
import com.member.api.member.model.SignInResponse;
import com.member.api.member.model.SignUpRequest;
import com.member.api.member.model.SignUpResponse;
import com.member.api.member.repository.MemberRepository;
import com.member.constants.CacheType;
import com.member.exception.CustomException;
import com.member.utils.CryptoUtil;
import com.member.utils.JwtTokenUtil;
import com.member.utils.MessageUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;

	@Transactional(readOnly = false)
	public SignUpResponse signUp(SignUpRequest req) {
		log.debug(">>> req : {}", req);
		MemberEntity rstMember = memberRepository.findByUserIdOrEmail(req.getUserId(), req.getEmail());

		if (!ObjectUtils.isEmpty(rstMember)) {
			throw new CustomException(MessageUtils.DUPLICATE_USER);
		}

		MemberEntity saveMember = memberRepository.save(
					MemberEntity.builder()
					.userId(req.getUserId())
					.password(CryptoUtil.encodePassword(req.getPassword()))
					.name(req.getName())
					.email(req.getEmail())
					.build()
				);

		return modelMapper.map(saveMember, SignUpResponse.class);
	}

	@Transactional(readOnly = true)
	public SignInResponse signIn(SignInRequest req) {
		MemberEntity rstMember = memberRepository.findById(req.getUserId()).orElseThrow(() -> new CustomException(MessageUtils.INVALID_USER));

		if (!CryptoUtil.comparePassword(req.getPassword(), rstMember.getPassword())) {
			throw new CustomException(MessageUtils.INVALID_PASSWORD);
		}

		log.debug(">>> rstMember : {}", rstMember);

		// 토큰 발급 및 로그인 처리
		return SignInResponse.builder().token(JwtTokenUtil.createToken(rstMember.getUserId(), rstMember.getName()))
				.build();
	}

	@Cacheable(cacheManager = CacheType.ONE_MINUTES, cacheNames = "members", key = "#token", unless = "#result == null")
	@Transactional(readOnly = true)
	public MemberInfoResponse myInfo(String token) {
		if (!ObjectUtils.isEmpty(token)) {
			MemberInfoResponse member = JwtTokenUtil.autholriztionCheckUser(token); // 넘겨받은 토큰 값으로 토큰에 있는 값 꺼내기

			log.debug("szs/me : {}", member);

			MemberEntity memberEntity = memberRepository.findById(member.getUserId())
					.orElseThrow(() -> new CustomException(MessageUtils.INVALID_USER)); // 토큰 claims에 담겨 있는 userId로 회원
																						// 정보 조회

			return modelMapper.map(memberEntity, MemberInfoResponse.class);
		} else {
			throw new CustomException(MessageUtils.INVALID_TOKEN);
		}
	}

}
