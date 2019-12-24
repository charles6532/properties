package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.starbocks.api.dao.SbAgentDao;
import kr.starbocks.api.dao.SbLocationInfoDao;
import kr.starbocks.api.dao.SbNotificationDao;
import kr.starbocks.api.dao.SbPropertyDao;
import kr.starbocks.api.dao.SbUserDao;
import kr.starbocks.api.domain.SbAgentDO;
import kr.starbocks.api.domain.SbDataObject;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbNotificationDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.domain.SbUserDO;
import kr.starbocks.api.value.SbHashMapVO;
import kr.starbocks.rapms.handler.Notification;
import kr.starbocks.util.codes.NotificationType;

@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	SbAgentDao sbAgentDao;
	@Autowired
	SbUserDao sbUserDao;
	@Autowired
	SbLocationInfoDao locationDao;
	@Autowired
	SbPropertyDao propertyDao;
	@Autowired
	SbNotificationDao notificationDao;
	@Autowired
	SbHashMapVO hmvo;

	@Override
	public SbAgentDO check(String email, String passwd) {
		SbAgentDO sbAgentDo = null;
		SbUserDO sbUserDo = sbUserDao.geteData(email);
		long userId = sbUserDo.getUserId();
		int result = sbAgentDao.checkId(userId);

		if (result == 1) {
			SbUserDO user = sbUserDao.getData(userId);
			if (!passwd.equals(user.getPasswd().trim())) {
				result = -1;
			}
		}
		return sbAgentDo;
	}

	@Override
	public SbAgentDO getData(String email) {

		SbAgentDO sbAgentDo = sbAgentDao.geteData(email);

		return sbAgentDo;
	}

	@Override
	public void signout(HttpSession session) {
		session.invalidate();
	}

	public SbAgentDO getAgent(SbAgentDO agentOD) {

		SbAgentDO sbagent = sbAgentDao.geteData(agentOD.getEmail());
		long zipcode = sbagent.getZipcodeId();
		sbagent.setAddress(locationDao.getAddress(zipcode));
		return sbagent;
	}

	public int modifyviewpro(SbAgentDO sbAgentDo) {
		long zipcode = sbAgentDao.getzipcode(sbAgentDo);
		sbAgentDo.setZipcodeId(zipcode);
		sbAgentDo.setAddress(locationDao.getAddress(zipcode));
		int result = sbUserDao.update(sbAgentDo);
		System.out.println("RESULT:" + result);
		if (result > 0) {
			result = sbAgentDao.update(sbAgentDo);
		}

		return result;
	}

	public List<SbLocationInfoDO> getCity(String state) {

		List<SbLocationInfoDO> sbs = locationDao.getCity(state);
		return sbs;
	}

	public List<SbLocationInfoDO> getLegalDistrict(String city) {
		System.out.println("getLegalDistrict city: " + city);

		List<SbLocationInfoDO> sbs = null;

		sbs = locationDao.getLegalDistrict(city);
		System.out.println("getLegalDistrict sbs : "+ sbs);
		
		return sbs;

	}

	public List<SbLocationInfoDO> getBldgNm(String dong) {
		System.out.println("getBldgNm dong: " + dong);
		String[] address = dong.split(" ");
		List<SbLocationInfoDO> sbs = null;
		long legalDistrictCd = locationDao.selectLegalDistrictCd(address);
		System.out.println("getBldgNm legalDistrictCd: " + legalDistrictCd);
		sbs = propertyDao.getBldgNm(legalDistrictCd);
		System.out.println("getBldgNm sbs: " + sbs);
		return sbs;

	}

	public int sendAddPropNotification(Notification noti) {
		SbNotificationDO sbnoti = new SbNotificationDO();
		sbnoti.setAddedBy(Long.parseLong(noti.getSender()));
		sbnoti.setContent(noti.getMessage());
		sbnoti.setCd(NotificationType.PROPERTY.getCode());
		long notiid = notificationDao.getNextSeq();
		sbnoti.setId(notiid);
		
		return notificationDao.sendNotification(sbnoti);
	}
		
}
