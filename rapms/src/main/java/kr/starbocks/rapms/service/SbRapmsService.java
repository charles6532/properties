package kr.starbocks.rapms.service;

import org.springframework.ui.Model;

import kr.starbocks.api.domain.SbAgentDO;

public interface SbRapmsService {

	int signup(SbAgentDO sbAgentDo);

	//회원가입
	int signuppro(Model model, SbAgentDO sbAgentDo);

}