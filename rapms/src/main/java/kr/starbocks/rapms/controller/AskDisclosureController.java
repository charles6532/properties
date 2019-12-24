package kr.starbocks.rapms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.rapms.service.AskDisclosureService;
import kr.starbocks.rapms.service.PropertiesService;
import kr.starbocks.rapms.service.SalesStatementService;
import kr.starbocks.util.StarbocksUtil;


@Controller
public class AskDisclosureController {
	
	@Autowired
	AskDisclosureService service;
	
	@Autowired
	SalesStatementService statementService;
	
	@Autowired
	PropertiesService propertiesService;
	
	private Logger completeLog = LoggerFactory.getLogger("_COMPLETE_DISCLOSURE_");
	
	@RequestMapping(value = "/askdisclosure/list/{userstr}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String askdisclosurelist(@PathVariable String userstr, @PathVariable String pagenum,
			@ModelAttribute SbAskDisclosureDO askSearch, Model model) throws Throwable {

		int rowCntPerPage = 10;
		long userId = (int) StarbocksUtil.getId(userstr);
		int currentPage = (int) StarbocksUtil.getId(pagenum);

		System.out.println("askdisclosure list userId : " + userId);

		askSearch.setCurrentPage(currentPage);
		askSearch.setRowCntPerPage(rowCntPerPage);
		askSearch.setUserId(userId);

		HashMap<String, Object> hm = service.getAskDislistByUser(askSearch);

		int totalnum = (int) hm.get("totalnum");
		List<SbAskDisclosureDO> searchlist = (List<SbAskDisclosureDO>) hm.get("searchlist");
		List<SbAskDisclosureDO> printlist = (List<SbAskDisclosureDO>) hm.get("printlist");

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);

		return "askdisclosure/list";

	}

	@RequestMapping(value = "/askdisclosure/detail/{askstrId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String askdisclosuredetail(@PathVariable String askstrId, @ModelAttribute SbAskDisclosureDO sbSearch,
			Model model) throws Throwable {
		long askid = StarbocksUtil.getId(askstrId);

		SbAskDisclosureDO askdo = service.getaAskDis(askid);

		model.addAttribute("askdo", askdo);

		return "askdisclosure/detail";

	}

	@RequestMapping(value = "/askdisclosure/replyPro", method = { RequestMethod.GET, RequestMethod.POST })
	public String askdisclosurereplyPro(@ModelAttribute SbAskDisclosureDO askDo, Model model) throws Throwable {
		
		System.out.println("controller askdisclosurereplyPro askDo : "+ askDo);
		int result = service.askdisclosurereply(askDo);
				
		int statementresult = statementService.payingOut(askDo);

		model.addAttribute("result", result);
		model.addAttribute("statementresult", statementresult);
		System.out.println("Result : " + result);
		System.out.println("statementresult : " + statementresult);
		
		long[] askDoProps = StarbocksUtil.getIds(askDo.getPropId().split(" "));
		long priorAddedBy = askDo.getPriorAddedBy();
		List<SbPropertyDO> rslt = service.getProperties(askDoProps);
		
		for( SbPropertyDO tmp : rslt ) {
			completeLog.info("{},{},{},{},{},{},{},{}", priorAddedBy, tmp.getPropId(), tmp.getAddress().substring(2,14),tmp.getAddress().substring(27),
					Integer.parseInt(tmp.getAddress().substring(16,20)),Integer.parseInt(tmp.getAddress().substring(21,26)),tmp.getFloor(),tmp.getAreaSpace());
		}
		return "common/index";
	}
	
	// 매물 고르기
	@RequestMapping(value = "/askdisclosure/select/{propsid}/{propsids}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectAskDis(@PathVariable String propsid, @PathVariable String propsids, @PathVariable String pagenum, Model model) throws Throwable {
		String[] psid = propsid.split(" ");
		long[] propSid = StarbocksUtil.getIds(psid);
		
		long[] props = null; 
		if( !propsids.equals("0")) props = StarbocksUtil.getIds(propsids.split(" "));
		
		
		
		int rowCntPerPage = 10;
		int currentPage = (int) StarbocksUtil.getId(pagenum);
		for (long pid : propSid) {
			System.out.println("propid : " + pid);
		}
		if( props != null ) System.out.println("controller selectProps props[] : " + Arrays.toString(props));
		System.out.println("controller selectProps currentPage : "+currentPage);
		
		SbAskDisclosureDO aDo = new SbAskDisclosureDO();  
		
		
		aDo.setCurrentPage(currentPage);
		aDo.setRowCntPerPage(rowCntPerPage);
		// propSid
		StringBuilder strb = new StringBuilder();
		for(int i=0; i<propSid.length; i++ ) {		//added by sf on Sep 27
			strb.append(propSid[i]);				
			strb.append(" ");						
		}											//added by sf on Sep 27
	    System.out.println("controller strb.toString().trim() : "+strb.toString().trim());
		aDo.setPropsId(strb.toString().trim());
		System.out.println("controller selectProps aDo : "+ aDo);
		
		
		
		HashMap<String, Object> hm = service.selectMyProperties(aDo); // long array로 던지면 List<SbPropertyDO>를 출력하는 함수를 만들 것
				
		int totalnum = (int) hm.get("totalnum");
		List<SbPropertyDO> searchlist = (List<SbPropertyDO>) hm.get("searchlist");
		List<SbPropertyDO> originalprintlist = (List<SbPropertyDO>) hm.get("printlist");
		
		List<SbPropertyDO> printlist = null;
		if( props != null ) printlist = propertiesService.addCheckBox(originalprintlist, props);
		else printlist = originalprintlist;
		System.out.println("controller selectAskDis printlist : "+ printlist);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);
		model.addAttribute("propsid", propsid);
		
		return "askdisclosure/select";
	}
	
	// ajax
	@RequestMapping(value = "/askdisclosure/addtoPropsId/{propsid}/{propid}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String addtoAskDisId(@PathVariable String propsid, @PathVariable String propid) throws Throwable {
		String gotpropid = null;
		
		long propId = StarbocksUtil.getId(propid);
		System.out.println("controller addtoPropsId propsid : "+propsid);
		System.out.println("controller addtoPropsId propId : "+propId);
		
		HashMap<String, String> result = service.addtoPropsId(propsid,Long.toString(propId));
		gotpropid = result.get(propsid);
		System.out.println("controller addtoPropsId gotpropid : "+gotpropid);
		if( gotpropid != null ) {
			String[] arrpropsid = gotpropid.split(" ");
			long[] data = new long[arrpropsid.length];   
			for (int i = 0; i < arrpropsid.length; i++) {   
			    data[i] = Long.parseLong(arrpropsid[i]);   
			}
			arrpropsid = StarbocksUtil.getIdStrs(data);
			ArrayList<String> arrayList = new ArrayList<String>();
			for( String temporary : arrpropsid ) {
				arrayList.add(temporary);
			}
			
			StringBuilder sb = new StringBuilder();
			for (String s : arrayList)
			{
			    sb.append(s);
			    sb.append(" ");
			}
			gotpropid = sb.toString();
		}
		else gotpropid="abc";
		return gotpropid;
	}

	// ajax	
	@RequestMapping(value = "/askdisclosure/deleteFromPropsId/{propsid}/{propid}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String deleteFromAskDisId(@PathVariable String propsid, @PathVariable String propid) throws Throwable {
		String gotpropid = null;
		
		long propId = StarbocksUtil.getId(propid);
		System.out.println("controller deleteFromPropsId propsid : "+propsid);
		System.out.println("controller deleteFromPropsId propId : "+propId);
		
		
		HashMap<String, String> result = service.deleteFromPropsId(propsid,Long.toString(propId));
		gotpropid = result.get(propsid);
		System.out.println("controller deleteFromPropsId gotpropid : "+gotpropid);
		if( gotpropid !=null && !gotpropid.equals("") && !gotpropid.equals(" ") ) {
			String[] arrpropsid = gotpropid.split(" ");
			long[] data = new long[arrpropsid.length];   
			for (int i = 0; i < arrpropsid.length; i++) {   
			    data[i] = Long.parseLong(arrpropsid[i]);   
			}
			arrpropsid = StarbocksUtil.getIdStrs(data);
			ArrayList<String> arrayList = new ArrayList<String>();
			for( String temporary : arrpropsid ) {
				arrayList.add(temporary);
			}
			
			StringBuilder sb = new StringBuilder();
			for (String s : arrayList)
			{
			    sb.append(s);
			    sb.append(" ");
			}
			gotpropid = sb.toString();
		} else gotpropid="0";
		
		
		return gotpropid;
	}
}
