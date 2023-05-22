package com.member.api.member.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.member.api.member.entity.MemberEntity;
import com.member.api.member.model.LoginRequest;
import com.member.api.member.model.LoginResponse;
import com.member.api.member.model.Member;
import com.member.api.member.model.MyInfoResponse;
import com.member.api.member.model.SignRequest;
import com.member.api.member.model.SignResponse;
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

	@Transactional(readOnly = false)
	public SignResponse createMember(SignRequest req) {

		log.debug(">>> req : {}", req);

		if (ObjectUtils.isEmpty(req.getUserId())) {
			throw new CustomException(MessageUtils.INVALID_USER_ID);
		} else if (ObjectUtils.isEmpty(req.getName())) {
			throw new CustomException(MessageUtils.INVALID_NAME);
		} else if (ObjectUtils.isEmpty(req.getPassword())) {
			throw new CustomException(MessageUtils.INVALID_PASSWORD);
		} else if (ObjectUtils.isEmpty(req.getRegNo())) {
			throw new CustomException(MessageUtils.INVALID_REG_NO);
		}

		SignResponse rst = new SignResponse();

		String encRegNo = CryptoUtil.encrypt(req.getRegNo());

		// TODO 암호화 알고리즘 변경사항 반영 - 암호화 할 때 마다 암호화 값 변경
		// 회원가입이 되어있는 것 체크
		MemberEntity rstMember = memberRepository.findMemberByNmReg(req.getName(), encRegNo);

		if (!ObjectUtils.isEmpty(rstMember)) {
			throw new CustomException(MessageUtils.DUPLICATE_USER);
		}

		MemberEntity saveMember = memberRepository.save(MemberEntity.builder().userId(req.getUserId())
				.password(CryptoUtil.encodePassword(req.getPassword())).name(req.getName()).regNo(encRegNo).build());

		rst.setName(saveMember.getName());
		rst.setUserId(saveMember.getUserId());

		return rst;

	}

	@Transactional(readOnly = true)
	public LoginResponse login(LoginRequest req) {

		if (ObjectUtils.isEmpty(req.getUserId())) {
			throw new CustomException(MessageUtils.INVALID_USER_ID);
		} else if (ObjectUtils.isEmpty(req.getPassword())) {
			throw new CustomException(MessageUtils.INVALID_PASSWORD);
		}

		MemberEntity rstMember = memberRepository.findById(req.getUserId()).orElseThrow(() -> new CustomException(MessageUtils.INVALID_USER));

		if (!CryptoUtil.comparePassword(req.getPassword(), rstMember.getPassword())) {
			throw new CustomException(MessageUtils.INVALID_PASSWORD);
		}

		log.debug(">>> rstMember : {}", rstMember);

		// 토큰 발급 및 로그인 처리
		return LoginResponse.builder().token(JwtTokenUtil.createToken(rstMember.getUserId(), rstMember.getName()))
				.build();
	}

	@Cacheable(cacheManager = CacheType.ONE_MINUTES, cacheNames = "members", key = "#token", unless = "#result == null")
	@Transactional(readOnly = true)
	public MyInfoResponse myInfo(String token) {

		if (!ObjectUtils.isEmpty(token)) {
			Member member = JwtTokenUtil.autholriztionCheckUser(token); // 넘겨받은 토큰 값으로 토큰에 있는 값 꺼내기

			log.debug("szs/me : {}", member);

			MemberEntity memberEntity = memberRepository.findById(member.getUserId())
					.orElseThrow(() -> new CustomException(MessageUtils.INVALID_USER)); // 토큰 claims에 담겨 있는 userId로 회원
																						// 정보 조회

			MyInfoResponse result = new MyInfoResponse();
			result.setName(memberEntity.getName());
			result.setPassword(memberEntity.getPassword());
			result.setRegNo(CryptoUtil.decrypt(memberEntity.getRegNo()));
			result.setUserId(memberEntity.getUserId());

			return result;
		} else {
			throw new CustomException(MessageUtils.INVALID_TOKEN);
		}
	}

}
