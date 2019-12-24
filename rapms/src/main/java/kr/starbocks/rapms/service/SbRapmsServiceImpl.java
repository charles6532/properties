//package kr.starbocks.rapms.service;
//
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class SbrapmsServiceImpl {
//
//}

package kr.starbocks.rapms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import kr.starbocks.api.dao.SbPropertyDao;
import kr.starbocks.api.domain.SbAgentDO;


@Component
public class SbRapmsServiceImpl implements SbRapmsService {
	@Autowired
	SbPropertyDao sbPropertyDao;
	

	@Override
	public int signup(SbAgentDO sbAgentDo) {
		System.out.println( "여기는 SbrapmsService" );
		return signup( sbAgentDo );
	}
	
	//회원가입
	@Override
	public int signuppro(Model model, SbAgentDO sbAgentDo) {
		int result = 0;
		
		return result;
	}


	
}
