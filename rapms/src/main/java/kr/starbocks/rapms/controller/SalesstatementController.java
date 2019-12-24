package kr.starbocks.rapms.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.starbocks.api.domain.SbInquiryDO;
import kr.starbocks.api.domain.SbStatementDO;
import kr.starbocks.rapms.service.SalesStatementService;
import kr.starbocks.util.StarbocksUtil;

@Controller
public class SalesstatementController {

	@Autowired
	SalesStatementService service;

	// 사용내역
	@RequestMapping(value = "/sales/list/{useridstr}/{page}", method = { RequestMethod.GET, RequestMethod.GET })
	public String saleslist(@PathVariable String useridstr, @PathVariable String page, Model model) throws Exception {
		
		long usrid = StarbocksUtil.getId(useridstr);
		
		int rowCntPerPage = 10;
		int currentPage = (int) StarbocksUtil.getId(page);
		
		
		SbStatementDO sbStatementDo = new SbStatementDO();
		sbStatementDo.setUserId(usrid);
		sbStatementDo.setCurrentPage(currentPage);
		sbStatementDo.setRowCntPerPage(rowCntPerPage);
		HashMap<String, Object> hm = service.salesList(sbStatementDo);
		
		int totalnum = (int) hm.get("totalnum");
		List<SbStatementDO> searchlist = (List<SbStatementDO>) hm.get("searchlist");
		List<SbStatementDO> printlist = (List<SbStatementDO>) hm.get("printlist");
		
		System.out.println("controller saleslist printlist : "+ printlist);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);
		
		
		long balance = service.getBalance(usrid);
		model.addAttribute("balance", balance);

		return "salesstatement/saleslist";
	}
	// 충전하기
	// payment.jsp에 필드값 불러오는 작업
	@RequestMapping(value = "/sales/pay/{useridstr}", method = { RequestMethod.GET, RequestMethod.POST })
	public String pay(@PathVariable String useridstr, @ModelAttribute SbStatementDO sbStatementDo, Model model) {
		long userId = StarbocksUtil.getId(useridstr);
		
		long balance = service.getBalance(userId);
		
		System.out.println("controller pay balance : "+balance);
		model.addAttribute("balance", balance);
		return "salesstatement/payment";
	}
	
	@RequestMapping(value = "/sales/payPro/{useridstr}", method = { RequestMethod.GET, RequestMethod.POST })
	public String payPro(@PathVariable String useridstr, @ModelAttribute SbStatementDO sbStatementDo, Model model) {
		long userId = StarbocksUtil.getId(useridstr);
		
		long balanceFirst = service.getBalance(userId);
		System.out.println("controller payPro balance at first: "+balanceFirst);
		sbStatementDo.setBalance(balanceFirst);
		sbStatementDo.setUserId(userId);
		int result = service.rechargeAdd(sbStatementDo);
		System.out.println("controller payPro result : "+result);
		
		long balanceLast = service.getBalance(userId);
		System.out.println("controller payPro balance at last: "+balanceLast);
		
		
		model.addAttribute("balance", balanceLast);
		return "salesstatement/payment";
	}
}
