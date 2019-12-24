package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbPropertyDO;

public interface AskDisclosureService {

	HashMap<String, Object> selectMyProperties(SbAskDisclosureDO aSearch);

	HashMap<String, String> addtoPropsId(String propsid, String propId);

	HashMap<String, String> deleteFromPropsId(String propsid, String propId);

	HashMap<String, Object> getAskDislistByUser(SbAskDisclosureDO askSearch);

	int askDisGetCount();

	SbAskDisclosureDO getaAskDis(long id);

	int askdisclosurereply(SbAskDisclosureDO askSearch);
	
	List<SbPropertyDO> getProperties(long[] array);
}