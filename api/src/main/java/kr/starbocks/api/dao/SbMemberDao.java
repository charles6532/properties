package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbInquiryDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbMemberDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.domain.SbZipcodeOrDO;

public interface SbMemberDao  extends SbDataAccessObject{

	int add(SbMemberDO sbMember);

	int delete(long id);

	SbMemberDO getData(long id);

	List<SbMemberDO> getList(int start, int end);

	long getNextSeq();

	int update(SbMemberDO data);

	long getCount();

	long getmemCount(long id);

	List<SbInquiryDO> inquiryGetList(int start, int end);

	int sfinfoupdate(SbMemberDO data);
	
	SbMemberDO geteData(String email);
	
}