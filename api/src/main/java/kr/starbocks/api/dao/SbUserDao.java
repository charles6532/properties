package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbMemberDO;
import kr.starbocks.api.domain.SbUserDO;

public interface SbUserDao  extends SbDataAccessObject{

	int add(SbMemberDO sbMember);

	int delete(long id);

	SbUserDO getData(long id);

	List<SbUserDO> getList(int start, int end);

	long getNextSeq();

	int update(SbUserDO data);

	int checkId(String email);

	long getCount();

	SbUserDO geteData(String email);

}