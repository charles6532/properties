package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbStatementDO;

public interface SalesStatementService {
	long getBalance(long userID);
	
	int rechargeAdd(SbStatementDO sbStatementDo);
	
	int payingOut(SbAskDisclosureDO askDo);
	
	HashMap<String, Object> salesList(SbStatementDO sbStatementDo);
		
}