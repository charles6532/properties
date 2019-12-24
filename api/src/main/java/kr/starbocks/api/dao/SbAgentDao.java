package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbAgentDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbMemberDO;

public interface SbAgentDao extends SbDataAccessObject{

	int add(SbAgentDO sbAgent);

	int delete(long id);

	SbAgentDO getData(long id);

	List<SbAgentDO> getList(int start, int end);

	long getNextSeq();

	int update(SbAgentDO data);

	long getCount();

	long getagCount(long id);


	int checkId(long id);

	SbAgentDO geteData(String email);
	
	long getzipcode(SbAgentDO sbAgent);

	long[] addressReturnsUserIds(long locCd);
}