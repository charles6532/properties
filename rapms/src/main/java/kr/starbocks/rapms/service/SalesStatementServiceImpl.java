package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.starbocks.api.dao.SbPaymentDao;
import kr.starbocks.api.dao.SbStatementDao;
import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbStatementDO;
import kr.starbocks.api.value.SbHashMapVO;

@Service
public class SalesStatementServiceImpl implements SalesStatementService {
	
	@Autowired
	SbPaymentDao sbPaymentDao;

	@Autowired
	SbStatementDao sbStatementDao;
	
	@Autowired
	SbHashMapVO hmvo;
	
	public long getBalance(long userID) {
		long result = 0;
		result = sbStatementDao.getBalance(userID);
		
		return result;
	}
	
	public int rechargeAdd(SbStatementDO sbStatementDo) {
		int result = 0;
		long paymentid = sbPaymentDao.getNextSeq();
		sbStatementDo.setPaymentId(paymentid);
		int payresult = sbPaymentDao.rechargeAdd(sbStatementDo);
		int stateresult = sbStatementDao.rechargeAdd(sbStatementDo);
		if (payresult == 1 && stateresult == 1)result = 1;
		
		return result;
	}
	
	public int payingOut(SbAskDisclosureDO askDo) {
		int result = 0;
		long paymentid = sbPaymentDao.getNextSeq();
		
		SbStatementDO sbStatementDo = new SbStatementDO();
		sbStatementDo.setPaymentId(paymentid);
		long askuserid = askDo.getAddedBy();
		sbStatementDo.setUserId(askuserid);
		sbStatementDo.setAskId(askDo.getAskId());
		sbStatementDo.setAmount((askDo.getPropId().split(" ").length)*500);
		int payresult = sbPaymentDao.payingOut(sbStatementDo);
		int stateresult = sbStatementDao.payingOut(sbStatementDo);
		if (payresult == 1 && stateresult == 1)result = 1;
		
		return result;
	}
	
	public HashMap<String, Object> salesList(SbStatementDO stateSearch){
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbStatementDO statedo = null;
		
		
		if (hmvo.get_SALES_STATEMENT_RAPMS() == null) {

			statedo = stateSearch;
			HashMap<String, SbStatementDO> tmp = new HashMap<String, SbStatementDO>();
			tmp.put("statedo", statedo);

			System.out.println("service if statedo : " + statedo);
			hmvo.set_SALES_STATEMENT_RAPMS(tmp); // save the first condition for looking around with properties

		} else {
			if (stateSearch.getAskId() == 0) {
				
				// is not the first condition but being along with pagination
				statedo = hmvo.get_SALES_STATEMENT_RAPMS().get("statedo");
				System.out.println("service else-if statedo : " + statedo);
				
			} else {
				// is not the first condition but the second condition
				statedo = stateSearch;
				HashMap<String, SbStatementDO> tmp = new HashMap<String, SbStatementDO>();
				tmp.put("statedo", statedo);
				System.out.println("service else-else statedo : " + statedo);
				hmvo.set_SALES_STATEMENT_RAPMS(tmp); // save the first condition in a hashmap for looking around with
												// properties
			}
		}
		
		stateSearch.setStartRow(1);
		stateSearch.setEndRow(50000);
		System.out.println("service stateSearch : " + stateSearch);
		List<SbStatementDO> searchlist = sbStatementDao.salesList(stateSearch);

		int totalnum = 0;
		if( searchlist != null ) {
			for (SbStatementDO tem : searchlist) {
				totalnum += 1;
			}
		}
		System.out.println("service totalnum : " + totalnum);
		System.out.println("service searchlist : " + searchlist);
		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		statedo.setStartRow((stateSearch.getCurrentPage() - 1) * stateSearch.getRowCntPerPage() + 1);
		int endrow = stateSearch.getCurrentPage() * stateSearch.getRowCntPerPage();
		if(totalnum<endrow)statedo.setEndRow(totalnum);
		else statedo.setEndRow(endrow);
		System.out.println("service statedo : " + statedo);
		List<SbStatementDO> printlist = sbStatementDao.salesList(statedo);
		System.out.println("service printlist : " + printlist);
		
		result.put("printlist", printlist);
		
		return result;
	}

}
