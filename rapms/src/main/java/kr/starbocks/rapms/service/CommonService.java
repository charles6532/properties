package kr.starbocks.rapms.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.starbocks.api.domain.SbAgentDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.rapms.handler.Notification;

public interface CommonService {

	SbAgentDO check(String email, String passwd);

	SbAgentDO getData(String email);

	SbAgentDO getAgent(SbAgentDO agentOD);

	void signout(HttpSession session);

	int modifyviewpro(SbAgentDO sbAgentDo);

	List<SbLocationInfoDO> getCity(String state);

	List<SbLocationInfoDO> getBldgNm(String city);

	List<SbLocationInfoDO> getLegalDistrict(String city);
	
	int sendAddPropNotification(Notification noti);

}