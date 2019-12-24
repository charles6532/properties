package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbDataObject;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbPhotoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.value.PropertyDetailVO;
import kr.starbocks.util.codes.PhotoType;

public interface PropertiesService {

	
	HashMap<String, Object> searchConditionRapms(SbLocationInfoDO locSearch);
	
	HashMap<String, Object> searchMineRapm(SbLocationInfoDO locSearch);
	
	HashMap<String, Object> selectMyProperties(SbConsultPropertyDO searchCon);
	
	List<SbPropertyDO> priceSetter(SbLocationInfoDO searchObj);

	// 매물 상세보기
	SbPropertyDO detail(SbPropertyDO sbPropertyDo, Model model);

	// 매물등록 Pro
	int add(String rootPath, SbPropertyDO sbPropertyDo);

	List<SbPhotoDO> getPhotos(long propId, PhotoType type);

	

	// 계약완료 업데이트
	int complete(long userId);

	// 매물 수정
	public int modifyProp(SbPropertyDO sbPropertyDo);

	HashMap<String, Object> getconlist(SbConsultPropertyDO conproSearch);

	SbConsultPropertyDO getaCon(long id);

	HashMap<String, Object> listByLegalDistrictCd(SbConsultPropertyDO conproSearch);

	int askforreply(SbConsultPropertyDO conOD);

	List<SbLocationInfoDO> getLocationCity(String state);

	List<SbLocationInfoDO> getLocationDanji(String city);

	int removeHmVO();

	List<SbLocationInfoDO> getBldgNm(String dong);
	
	HashMap<String, String> addtoPropsId(String consultid, String propId);
	
	HashMap<String, String> deleteFromPropsId(String consultid, String propId);
	
	PropertyDetailVO getPropLocVO(long propId);
	
	List<SbPropertyDO> addCheckBox(List<SbPropertyDO> originalprintlist, long[] props);
	
	long[] addressReturnsUserIds(SbLocationInfoDO addr);
	
	SbDataObject getStateCityLegalDistrictNm(String addr);
}