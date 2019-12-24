package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbPaymentDO;
import kr.starbocks.api.domain.SbStatementDO;

public interface SbPaymentDao  extends SbDataAccessObject{

	int add(SbPaymentDO sbPaymentDo);

	int delete(long id);

	SbPaymentDO getData(long id);

	List<SbPaymentDO> getList(int start, int end);

	long getNextSeq();

	int update(SbPaymentDO data);
	
	SbPaymentDO getBalance( long userId );
	
	int rechargeAdd(SbStatementDO sbStatementDo);
	
	int payingOut(SbStatementDO sbStatementDo);

}