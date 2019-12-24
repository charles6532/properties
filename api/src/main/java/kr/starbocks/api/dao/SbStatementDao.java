package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbStatementDO;

public interface SbStatementDao  extends SbDataAccessObject{

	int add(SbStatementDO sbStatement);

	int delete(long id);

	SbStatementDO getData(long id);

	List<SbStatementDO> getList(int start, int end);

	long getNextSeq();

	int update(SbStatementDO data);

	long getBalance(long userId);
	
	int rechargeAdd(SbStatementDO sbStatementDo);
	
	int payingOut(SbStatementDO sbStatementDo);
	
	List<SbStatementDO> salesList(SbStatementDO sbStatementDo);
}