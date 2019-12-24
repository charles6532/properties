package kr.starbocks.rapms.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kr.starbocks.api.domain.SbAgentDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbPhotoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.value.PropertyDetailVO;
import kr.starbocks.rapms.handler.MessageHandler;
import kr.starbocks.rapms.handler.Notification;
import kr.starbocks.rapms.service.CommonService;
import kr.starbocks.rapms.service.PropertiesService;
import kr.starbocks.util.StarbocksUtil;
import kr.starbocks.util.codes.PhotoType;

@Controller
public class PropertiesController {
	@Autowired
	MessageHandler messageHandler;

	@Autowired
	PropertiesService propertiesService;

	@Autowired
	CommonService commonService;

	@Autowired
	ServletContext sc;
		
	private Logger completeLog = LoggerFactory.getLogger("_COMPLETE_PROPERTIES_");

	// 매물 등록할때 로그
	public String getTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		String sentAt = sdf.format(timestamp);
		return sentAt;
	}
	
	// 매물 목록
	@RequestMapping(value = "/prop/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertylistprocess() throws Throwable {
		return "properties/properties2";
	}

	// 매물 목록 - 검색
	@RequestMapping(value = "/prop/searchPro/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertySearchProprocess(@PathVariable String pagenum, @ModelAttribute SbLocationInfoDO locSearch,
			Model model) throws Throwable {
		int rowCntPerPage = 10;
		int currentPage = (int) StarbocksUtil.getId(pagenum);
		System.out.println("sessioncontroller currentPage : " + currentPage);

		locSearch.setCurrentPage(currentPage);
		locSearch.setRowCntPerPage(rowCntPerPage);

		HashMap<String, Object> hm = propertiesService.searchConditionRapms(locSearch);
		SbLocationInfoDO locationdo = (SbLocationInfoDO) hm.get("locationdo");

		SbLocationInfoDO afterlocationdo = (SbLocationInfoDO) hm.get("afterlocationdo");
		int totalnum = (int) hm.get("totalnum");
		List<SbPropertyDO> searchlist = (List<SbPropertyDO>) hm.get("searchlist");
		List<SbPropertyDO> printlist = (List<SbPropertyDO>) hm.get("printlist");

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);

		return "properties/properties2";
	}

	// 매물 상세보기
	@RequestMapping(value = "/prop/detail/{propidstr}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertydetail(@PathVariable String propidstr, Model model) throws Throwable {
		long propId = StarbocksUtil.getId(propidstr);
		// propId로 sbpropertydo, sblocationdo를 전달한다

		PropertyDetailVO propdetailvo = propertiesService.getPropLocVO(propId);
		model.addAttribute("propdetailvo", propdetailvo);

		List<SbPhotoDO> internalPhotos = propertiesService.getPhotos(propId, PhotoType.INTERNAL);
		System.out.println("controller internalPhotos : " + internalPhotos);
		
		model.addAttribute("internalPhotos", internalPhotos);

		return "properties/property";
	}

	// 매물등록
	@RequestMapping(value = "/prop/addForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String addprop() throws Throwable {
		return "properties/addProp";
	}

	// 매물등록 pro
		@RequestMapping(value = "/prop/addPro", method = { RequestMethod.GET, RequestMethod.POST })
		public String propaddPro(@ModelAttribute SbPropertyDO sbsearch, Model model) throws Exception {

			String realRootPath = sc.getRealPath("/");
			System.out.println("controller propaddPro realRootPath : " + realRootPath);
			System.out.println("controller propaddPro sbPropertyDo : " + sbsearch);

			int result = propertiesService.add(realRootPath, sbsearch);
			System.out.println("controller propaddPro result : " + result);

			if (result > 0) {
				System.out.println("if문 안쪽 result : " + result);
				// 리스트로 forwarding
				Notification noti = new Notification();
				noti.setSender(String.valueOf(sbsearch.getUserId()));
				noti.setAddressee("all");
				noti.setMessage(sbsearch.getTitle() + "(이)가 새로 등록 되었습니다.");
				commonService.sendAddPropNotification(noti);
				JSONObject json = new JSONObject();
				json.put("title",sbsearch.getTitle() + "(이)가 새로 등록 되었습니다.");
				json.put("address",sbsearch.getState()+" " + sbsearch.getCity()+" "+sbsearch.getLegalDistrictNm()+ " " + sbsearch.getBldgNm());
				// 주소를 날리면 이 주소의 legal_district_cd에 관심매물을 갖고 있는 userid array가 출력된다
				SbLocationInfoDO addr = new SbLocationInfoDO();
				addr.setState(sbsearch.getState());
				addr.setCity(sbsearch.getCity());
				addr.setLegalDistrictNm(sbsearch.getLegalDistrictNm());
				addr.setBldgNm(sbsearch.getBldgNm());
				long[] userArray = propertiesService.addressReturnsUserIds(addr);
				System.out.println("controller userArray : "+ Arrays.toString(userArray));
				String[] s=new  String[userArray.length];
				
				for(int i=0;i<userArray.length;i++)
				{
					s[i]=String.valueOf(userArray[i]);
					
				}
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				String stringarray = mapper.writeValueAsString(s);
				json.put("receiver", stringarray);
				
				
				String postJson = mapper.writeValueAsString(json);
				
				
				noti.setMessage(postJson);
//				noti.setMessage(sbsearch.getTitle() + "(이)가 새로 등록 되었습니다.");	// 여기에 받는 사람을 JSON을 직렬화해서 던지고, sf 쪽에서 직렬화된 것을 JSON형태로 받아준다
				messageHandler.sendMessage(noti);
				
				completeLog.info("{},{},{}", sbsearch.getUserId(), sbsearch.getPrice(), this.getTime());
				return "properties/properties2";
			} else {
				// 에러 페이지 출력
				System.out.println("controller result else문 : " + result);
				return "properties/addProp";
			}
		}
	//매물 수정 - 기존항목 불러오기
	@RequestMapping( value="/prop/modify/{propIdStr}", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyProp( @PathVariable String propIdStr, @ModelAttribute SbPropertyDO sbPropertyDo, Model model ) {

		long propId = StarbocksUtil.getId( propIdStr );
		PropertyDetailVO propdetailvo = propertiesService.getPropLocVO( propId );
		propertiesService.getStateCityLegalDistrictNm( propdetailvo.getAddress() );
		String moveatstring = propdetailvo.getMoveAt();
		String[] temp = moveatstring.split(" ")[0].split("-");
		StringBuilder sb = new StringBuilder();
		sb.append(temp[1]).append("/").append(temp[2]).append("/").append(temp[0]);
		propdetailvo.setMoveAt(sb.toString());
		model.addAttribute("sbPropertyDo", propdetailvo );
		
		List<SbPhotoDO> internalPhotos = propertiesService.getPhotos( sbPropertyDo.getPropId(), PhotoType.INTERNAL);
		model.addAttribute("internalPhotos", internalPhotos);
		
		return "properties/modify";
	}

	// 매물수정 처리
	@RequestMapping(value = "/prop/modifyPro/{propIdStr}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute SbPropertyDO sbPropertyDo, @PathVariable String propIdStr) {
		int result = 0;

		long propId = StarbocksUtil.getId(propIdStr);
		System.out.println("매물 수정처리 propId 확인 : " + propId);
		sbPropertyDo.setPropId(propId);
		System.out.println("sbPropertyDo 넘어온 값 확인 : " + sbPropertyDo);

		result = propertiesService.modifyProp(sbPropertyDo);
		System.out.println("매물수정 result값 확인 : " + result);

		return "properties/properties2";
	}

	// 계약 완료처리
    @RequestMapping(value = "/prop/complete/{propIdStr}", method = RequestMethod.POST)
    public String complete(@PathVariable String propIdStr, @ModelAttribute SbAgentDO sbAgentDo, Model model) {
        String email = sbAgentDo.getEmail();
        long propId = StarbocksUtil.getId(propIdStr);
        System.out.println( "컨트롤러  propid 확인 ======> " + propId );
        SbAgentDO agentDo = commonService.getData( email );
        if( agentDo != null && agentDo.getPasswd().equals( sbAgentDo.getPasswd() ) ) {
            int result = propertiesService.complete(propId);
            model.addAttribute(result);
            
        } else {
            int result = 0; 
        	model.addAttribute(result);
        }
        return "properties/properties2";
    }

	// 등록한 매물 확인하기 - 수정중
//	@RequestMapping( value="prop/{userIdStr}/{propIdStr}", method=RequestMethod.POST )
//	public String propConfirm( @PathVariable String propIdStr, @PathVariable String userIdStr, SbPropertyDO sbPropertyDo, Model model ) {
//		
//		long userId = StarbocksUtil.getId( userIdStr );
//		sbPropertyDo = (SbPropertyDO) propertiesService.myprop( userId );
//		
//		long propId = StarbocksUtil.getId(propIdStr);
//		System.out.println( "등록매물 확인 propId 확인함 : " + propId );
//		sbPropertyDo = propertiesService.propDetail(propId);
//		model.addAttribute("sbPropertyDo",sbPropertyDo);
//	
//		SbPropertyDO propdetail = propertiesService.detail(sbPropertyDo, model);
//		model.addAttribute("sbPropertyDo", propdetail);
//		List<SbPhotoDO> internalPhotos = propertiesService.getPhotos(propdetail.getPropId(), PhotoType.INTENAL);
//		model.addAttribute("internalPhotos",internalPhotos);
//		return "properties/confirmAddProp";
//	}

	@RequestMapping(value = "/prop/myprop/{userIdStr}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String myprop(@PathVariable String userIdStr, @PathVariable String pagenum, Model model) {

		long userId = StarbocksUtil.getId(userIdStr);
		System.out.println("controller myprop userid: " + userId);
		SbLocationInfoDO locSearch = new SbLocationInfoDO();

		int rowCntPerPage = 10;
		int currentPage = (int) StarbocksUtil.getId(pagenum);
		System.out.println("controller myprop currentPage : " + currentPage);

		locSearch.setCurrentPage(currentPage);
		locSearch.setRowCntPerPage(rowCntPerPage);
		locSearch.setUserId(userId);
		System.out.println("controller myprop locSearch : " + locSearch);

		HashMap<String, Object> hm = propertiesService.searchMineRapm(locSearch);
		SbLocationInfoDO locationdo = (SbLocationInfoDO) hm.get("locationdo");

		SbLocationInfoDO afterlocationdo = (SbLocationInfoDO) hm.get("afterlocationdo");
		int totalnum = (int) hm.get("totalnum");
		List<SbPropertyDO> searchlist = (List<SbPropertyDO>) hm.get("searchlist");
		List<SbPropertyDO> printlist = (List<SbPropertyDO>) hm.get("printlist");

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);

		return "properties/viewmyprop";

	}

	// 회원 매물요청
	@RequestMapping(value = "/prop/mine/{zipcodestr}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertymineprocess(@PathVariable String zipcodestr, @PathVariable String pagenum,
			@ModelAttribute SbConsultPropertyDO conproSearch, Model model) throws Throwable {
		int rowCntPerPage = 10;
		long zipcodeId = (int) StarbocksUtil.getId(zipcodestr);
		int currentPage = (int) StarbocksUtil.getId(pagenum);
		System.out.println("controller zipcodeId : " + zipcodeId);
		conproSearch.setCurrentPage(currentPage);
		conproSearch.setRowCntPerPage(rowCntPerPage);
		conproSearch.setZipcode(zipcodeId);
		HashMap<String, Object> hm = propertiesService.listByLegalDistrictCd(conproSearch);

		int totalnum = (int) hm.get("totalnum");
		List<SbConsultPropertyDO> conprolist = (List<SbConsultPropertyDO>) hm.get("conprolist");
		List<SbConsultPropertyDO> printlist = (List<SbConsultPropertyDO>) hm.get("printlist");
		System.out.println("controller printlist : "+ printlist);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("conprolist", conprolist);
		model.addAttribute("printlist", printlist);

		return "properties/mine";

	}

	// 매물요청 - 매물요청 상세보기
	@RequestMapping(value = "/property/askforDetail/{consultpid}", method = { RequestMethod.GET, RequestMethod.POST })
	public String askforDetailprocess(@PathVariable String consultpid, @ModelAttribute SbConsultPropertyDO conDo,
			Model model) throws Throwable {
		long conid = StarbocksUtil.getId(consultpid);
		System.out.println("controller askforDetail conid : " + conid);
		SbConsultPropertyDO conpDo = propertiesService.getaCon(conid);

		model.addAttribute("conproperty", conpDo);

		return "properties/askforDetail";

	}

	// 매물요청 - 답변 쓰는 페이지
	@RequestMapping(value = "/prop/reply", method = { RequestMethod.GET, RequestMethod.POST })
	public String askforreplyview(@ModelAttribute SbConsultPropertyDO conDo, Model model) throws Throwable {

		System.out.println("controller askforreplyview conDo : " + conDo);

		model.addAttribute("conDo", conDo);

		return "properties/reply";

	}

	// 매물 답변 처리
	@RequestMapping(value = "/prop/replyPro/{useridstr}", method = { RequestMethod.GET, RequestMethod.POST })
	public String askforReplyPro(@PathVariable String useridstr, @ModelAttribute SbConsultPropertyDO conDo, Model model) throws Throwable {
		long userid = StarbocksUtil.getId(useridstr);
		
		conDo.setUserId(userid);
		
		System.out.println("controller askforReplyPro conDo : " + conDo);

		int result = propertiesService.askforreply(conDo);

		model.addAttribute("result", result);

		return "common/index";
	}

	// ajax - 서울시 서대문구 홍은동 불러오기
	@RequestMapping(value = "/prop/askfor/{col}/{val}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<SbLocationInfoDO> statecityprocess(@PathVariable String col, @PathVariable String val)
			throws Throwable {

		List<SbLocationInfoDO> sbzip = null;
		switch (col) {
		case "state":
			sbzip = commonService.getCity(val);
			break;
		case "city":
			sbzip = commonService.getLegalDistrict(val);
			break;
		case "legalDistrictNm":
			sbzip = propertiesService.getBldgNm(val);
		}

		return sbzip;
	}

	// 매물 고르기
		@RequestMapping(value = "/prop/select/{consultpid}/{propsids}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
		public String selectProps(@PathVariable String consultpid, @PathVariable String propsids, @PathVariable String pagenum, Model model)
				throws Throwable {
			long consultPid = StarbocksUtil.getId(consultpid);
			long[] props = null; 
			// pagination is started
			if( !propsids.equals("0")) {
				props = StarbocksUtil.getIds(propsids.split(" "));
			}
			else model.addAttribute("selectedProps", "0");
			// pagination is ended
			int rowCntPerPage = 10;
			int currentPage = (int) StarbocksUtil.getId(pagenum);
			System.out.println("controller selectProps consultPid : " + consultPid);
			if( props != null ) System.out.println("controller selectProps props[] : " + Arrays.toString(props));
			System.out.println("controller selectProps currentPage : " + currentPage);

			SbConsultPropertyDO conpDo = propertiesService.getaCon(consultPid);

			conpDo.setCurrentPage(currentPage);
			conpDo.setRowCntPerPage(rowCntPerPage);
			System.out.println("controller selectProps conpDo : " + conpDo);

			HashMap<String, Object> hm = propertiesService.selectMyProperties(conpDo);

			int totalnum = (int) hm.get("totalnum");
			List<SbPropertyDO> searchlist = (List<SbPropertyDO>) hm.get("searchlist");
			List<SbPropertyDO> originalprintlist = (List<SbPropertyDO>) hm.get("printlist");
			
			List<SbPropertyDO> printlist = null;
			if( props != null ) printlist = propertiesService.addCheckBox(originalprintlist, props);
			else printlist = originalprintlist;
			System.out.println("controller selectProps printlist : "+ printlist);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("rowCntPerPage", rowCntPerPage);
			model.addAttribute("totCnt", totalnum);
			model.addAttribute("searchlist", searchlist);
			model.addAttribute("printlist", printlist);
			model.addAttribute("consultpid", consultpid);
			return "properties/select";
		}

		// ajax
		@RequestMapping(value = "/prop/addtoPropsId/{consultpid}/{propid}", method = { RequestMethod.GET,
				RequestMethod.POST })
		@ResponseBody
		public String addtoPropsId(@PathVariable String consultpid, @PathVariable String propid) throws Throwable {
			String gotpropid = null;
			long consultPid = StarbocksUtil.getId(consultpid);
			long propId = StarbocksUtil.getId(propid);
			System.out.println("controller addtoPropsId consultPid : " + consultPid);
			System.out.println("controller addtoPropsId propId : " + propId);
			
			HashMap<String, String> result = propertiesService.addtoPropsId(consultpid, Long.toString(propId));
			gotpropid = result.get(consultpid);
			System.out.println("controller addtoPropsId gotpropid : " + gotpropid);
			if (gotpropid != null) {
				String[] arrpropsid = gotpropid.split(" ");
				long[] data = new long[arrpropsid.length];
				for (int i = 0; i < arrpropsid.length; i++) {
					data[i] = Long.parseLong(arrpropsid[i]);
				}
				arrpropsid = StarbocksUtil.getIdStrs(data);
				ArrayList<String> arrayList = new ArrayList<String>();
				for (String temporary : arrpropsid) {
					arrayList.add(temporary);
				}

				StringBuilder sb = new StringBuilder();
				for (String s : arrayList) {
					sb.append(s);
					sb.append(" ");
				}
				gotpropid = sb.toString();
			} else
				gotpropid = "abc";
			return gotpropid;
		}

		// ajax
		@RequestMapping(value = "/prop/deleteFromPropsId/{consultpid}/{propid}", method = { RequestMethod.GET,
				RequestMethod.POST })
		@ResponseBody
		public String deleteFromPropsId(@PathVariable String consultpid, @PathVariable String propid) throws Throwable {
			String gotpropid = null;
			long consultPid = StarbocksUtil.getId(consultpid);
			long propId = StarbocksUtil.getId(propid);
			System.out.println("controller deleteFromPropsId consultPid : " + consultPid);
			System.out.println("controller deleteFromPropsId propId : " + propId);
			
			HashMap<String, String> result = propertiesService.deleteFromPropsId(consultpid, Long.toString(propId));
			gotpropid = result.get(consultpid);
			System.out.println("controller deleteFromPropsId gotpropid : " + gotpropid);
			if (gotpropid != null && !gotpropid.equals("") && !gotpropid.equals(" ")) {
				String[] arrpropsid = gotpropid.split(" ");
				long[] data = new long[arrpropsid.length];
				for (int i = 0; i < arrpropsid.length; i++) {
					data[i] = Long.parseLong(arrpropsid[i]);
				}
				arrpropsid = StarbocksUtil.getIdStrs(data);
				ArrayList<String> arrayList = new ArrayList<String>();
				for (String temporary : arrpropsid) {
					arrayList.add(temporary);
				}

				StringBuilder sb = new StringBuilder();
				for (String s : arrayList) {
					sb.append(s);
					sb.append(" ");
				}
				gotpropid = sb.toString();
			} else
				gotpropid = "0";
			return gotpropid;
		}
}
