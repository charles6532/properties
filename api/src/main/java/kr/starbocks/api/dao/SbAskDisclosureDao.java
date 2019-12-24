package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbPropertyDO;

public interface SbAskDisclosureDao  extends SbDataAccessObject{

	int add(SbAskDisclosureDO sbAskDisclosure);

	int delete(long id);

	SbAskDisclosureDO getData(long id);

	List<SbAskDisclosureDO> getList(int start, int end);

	long getNextSeq();

	int update(SbAskDisclosureDO data);

	int addDisclosure(SbAskDisclosureDO aDo);
	
	List<SbAskDisclosureDO> getAskDislistAddedBy(SbAskDisclosureDO search);
	
	int askDisclosureGetCount();
	
	int sfaskdisreply(SbAskDisclosureDO data);
	
	List<SbAskDisclosureDO> getAskDislistByUser(SbAskDisclosureDO search);
	
	List<SbPropertyDO> getProperties(long[] array);

}