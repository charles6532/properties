package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbOperatorDO;

public interface SbOperatorDao  extends SbDataAccessObject{

	int add(SbOperatorDO sbOperator);

	int delete(long id);

	SbOperatorDO getData(long id);

	List<SbOperatorDO> getList(int start, int end);

	long getNextSeq();

	int update(SbOperatorDO data);

	//07/26 현성 추가 관리자 로그인 확인
	int checkId(long id);

}