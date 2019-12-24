package kr.starbocks.sf.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbFavoritePropertiesDO;
import kr.starbocks.api.domain.SbInquiryDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbMemberDO;
import kr.starbocks.api.domain.SbNotificationDO;
import kr.starbocks.api.domain.SbPhotoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.domain.SbUserDO;
import kr.starbocks.api.value.PropertyDetailVO;
import kr.starbocks.sf.controller.Notification;
import kr.starbocks.util.codes.PhotoType;

public interface SfService {

	SbMemberDO getData(SbUserDO userOD) throws Exception;

	int signup(SbMemberDO memberOD) throws Exception;

	void logout(HttpSession session);

	HashMap<String, Object> getInqlist(SbInquiryDO inqSearch);

	int inquiryGetCount();

	SbInquiryDO getaInq(long id);

	int askforpro(SbConsultPropertyDO conprodo);

	int modifyviewpro(SbMemberDO memberOD);

	int inquirywrite(SbInquiryDO inquiryOD);

	int inquiryreply(SbInquiryDO inquiryOD);

	List<SbLocationInfoDO> getcity(String state);

	List<SbLocationInfoDO> getLegalDistrict(String city);

	SbMemberDO getMember(SbMemberDO memberOD) throws Exception;

	HashMap<String, Object> getconlist(SbConsultPropertyDO conproSearch);

	List<SbLocationInfoDO> getLocationCity(String state);

	List<SbLocationInfoDO> getLocationDanji(String city);

	List<SbPropertyDO> propertysearch(SbLocationInfoDO locationDOParam);

	Map<String, Object> getPropertyWithLocation(final long propId);

	int emailCheck(String emailaddress);

	HashMap<String, String> emailAuthprocess(String emailaddress);

	int verifyAuthenticationWith(String authKeyStr);

	HashMap<String, Object> searchProp(SbLocationInfoDO locSearch);

	int removeHmVO();

	SbConsultPropertyDO getaCon(long id);

	int addtoFavorite(long[] prouser);

	HashMap<String, Object> favoriteproperties(SbFavoritePropertiesDO favSearch);

	int deletefromFavorite(long[] prouser);

	int addDisclosure(SbAskDisclosureDO aDo);

	HashMap<String, Object> getUserConlist(SbConsultPropertyDO conproSearch);

	List<SbLocationInfoDO> getCommonBldgNm(String dong);

	List<SbLocationInfoDO> getPropBldgNm(String dong);

	HashMap<String, Object> askDisclosureList(SbAskDisclosureDO aDo);

	HashMap<String, Object> askforList(SbConsultPropertyDO aDo);
	
	HashMap<String, String> addtoPropsId(String propsid, String propId);

	HashMap<String, String> deleteFromPropsId(String propsid, String propId);
	
	HashMap<String, Object> getAskDislistAddedBy(SbAskDisclosureDO askSearch);

	int askDisGetCount();

	SbAskDisclosureDO getaAskDis(long id);

	int askdisclosurereply(SbAskDisclosureDO askSearch);
	
	List<SbPropertyDO> addCheckBox(List<SbPropertyDO> originalprintlist, long[] props);
	
	int sendAskForNotification(Notification noti);
	
	PropertyDetailVO getPropLocVO(long propId);
	
	List<SbPhotoDO> getPhotos(long propId, PhotoType type);
		
	HashMap<String, Object> notiList( SbNotificationDO sbNotificationDo );
	
	long[] addressReturnsUserIds(SbLocationInfoDO addr);
	
	List<SbPropertyDO> throwPropIds(long[] propidlongs);
	
	List<SbPropertyDO> recommList(long useridlong) throws IOException;
	
	List<SbPropertyDO> predictionList(List<SbPropertyDO> originalprintlist, long useridlong) throws IOException;
}