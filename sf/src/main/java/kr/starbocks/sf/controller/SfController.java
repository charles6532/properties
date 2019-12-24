package kr.starbocks.sf.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbFavoritePropertiesDO;
import kr.starbocks.api.domain.SbInquiryDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbMemberDO;
import kr.starbocks.api.domain.SbNotificationDO;
import kr.starbocks.api.domain.SbPhotoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.domain.SbUserDO;
import kr.starbocks.api.value.PropertyDetailVO;
import kr.starbocks.sf.service.SfService;
import kr.starbocks.util.CryptoUtil;
import kr.starbocks.util.StarbocksUtil;
import kr.starbocks.util.codes.PhotoType;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SfController {
	@Autowired
	SfService service;
	
	@Autowired
	MessageHandler messageHandler;

	private static final Logger logger = LoggerFactory.getLogger(SfController.class);
	private Logger consultLogger = LoggerFactory.getLogger("_COMPLETE_CONSULT_PROPERTIES_");
	private Logger useridPropidLogger = LoggerFactory.getLogger("_USERID_PROPID_COUNT_");

	public String getTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		String sentAt = sdf.format(timestamp);
		return sentAt;
	}

	@RequestMapping(value = { "/main", "/home", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "main/index";
	}


	@RequestMapping(value = "/email/check", method = { RequestMethod.GET, RequestMethod.POST })
	public String emailCheckprocess() throws Throwable {

		return "email/check";
	}

	// ajax
	@RequestMapping(value = "/email/auth/{emailaddress:.+}", method = { RequestMethod.POST })
	@ResponseBody
	public String emailAuthprocess(@PathVariable String emailaddress) throws Throwable {
		String auth = null;
		System.out.println("emailAuth emailadd : " + emailaddress);

		HashMap<String, String> hm = service.emailAuthprocess(emailaddress);
		auth = hm.get(emailaddress);
		String authresult = hm.get("authresult");
		String auresult = CryptoUtil.encrypt("asklfnalkwnkla", auth);
		System.out.println("controller authresult : " + authresult);
		return auresult;
	}

	// 실질적인 signupPro method이다
	// signup.jsp id="randomStr"의 값을 받아서 맞으면 signup() method로 넘겨준다
	@RequestMapping(value = "/email/verify", method = { RequestMethod.POST })
	public String verifyAuthenticationWith(@ModelAttribute SbMemberDO member, Model model) throws Exception {
		System.out.println("controller authKeyStr : " + member.getRandomStr());
		System.out.println("controller plainKeyStr : " + CryptoUtil.decrypt("asklfnalkwnkla", member.getAuthStr()));

		int randomresult = service.verifyAuthenticationWith(member.getRandomStr());
		int signupresult = 0;
		if (randomresult == 1) {
			signupresult = service.signup(member);
		} else {
			signupresult = 0;
		}

		if (signupresult == 0)
			service.removeHmVO();
		String signuprslt = (signupresult == 1) ? "회원가입 성공" : "회원가입 실패";
		System.out.println("randomresult : " + randomresult);
		model.addAttribute("controller randomresult", randomresult);
		model.addAttribute("signupresult", signuprslt);
		return "member/signup";
	}

	@RequestMapping(value = "/inquiry/list/{userstr}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String inquirylistprocess(@PathVariable String userstr, @PathVariable String pagenum,
			@ModelAttribute SbInquiryDO inqSearch, Model model) throws Throwable {

		int rowCntPerPage = 10;
		long userId = (int) StarbocksUtil.getId(userstr);
		int currentPage = (int) StarbocksUtil.getId(pagenum);

		System.out.println("inquiry list userId : " + userId);

		inqSearch.setCurrentPage(currentPage);
		inqSearch.setRowCntPerPage(rowCntPerPage);
		inqSearch.setUserId(userId);

		HashMap<String, Object> hm = service.getInqlist(inqSearch);

		int totalnum = (int) hm.get("totalnum");
		List<SbInquiryDO> searchlist = (List<SbInquiryDO>) hm.get("searchlist");
		List<SbInquiryDO> printlist = (List<SbInquiryDO>) hm.get("printlist");

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);

		return "inquiry/list";

	}

	@RequestMapping(value = "/inquiry/detail/{inqstrId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String inquirydetailstrprocess(@PathVariable String inqstrId, @ModelAttribute SbInquiryDO sbinquiryDo,
			Model model) throws Throwable {
		long inqid = StarbocksUtil.getId(inqstrId);

		SbInquiryDO inquirydo = service.getaInq(inqid);

		model.addAttribute("inquirydo", inquirydo);

		return "inquiry/detail";

	}

	@RequestMapping(value = "/inquiry/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String inquirywriteprocess() throws Throwable {

		return "inquiry/write";

	}

	@RequestMapping(value = "/inquiry/writePro", method = { RequestMethod.GET, RequestMethod.POST })
	public String inquirywriteProprocess(@ModelAttribute SbInquiryDO sbinquiryDo, Model model) throws Throwable {

		int result = service.inquirywrite(sbinquiryDo);

		model.addAttribute("result", result);
		System.out.println("Result : " + result);
		return "main/index";

	}

	@RequestMapping(value = "/inquiry/reply/{inqstrid}/{inqstrThrId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String inquiryreplyprocess(@PathVariable String inqstrid, @PathVariable String inqstrThrId,
			@ModelAttribute SbInquiryDO sbinquiryDo, Model model) throws Throwable {
		long inqid = StarbocksUtil.getId(inqstrid);
		long inqthrid = StarbocksUtil.getId(inqstrThrId);

		if (inqid == inqthrid) {
			sbinquiryDo.setpId(inqthrid);
			sbinquiryDo.setThrId(inqthrid);
		} else {
			sbinquiryDo.setpId(inqid);
			sbinquiryDo.setThrId(inqthrid);
		}

		model.addAttribute("inquirydo", sbinquiryDo);

		return "inquiry/reply";

	}

	@RequestMapping(value = "/inquiry/replyPro", method = { RequestMethod.GET, RequestMethod.POST })
	public String inquiryreplyProprocess(@ModelAttribute SbInquiryDO sbinquiryDo, Model model) throws Throwable {

		int result = service.inquiryreply(sbinquiryDo);

		model.addAttribute("result", result);
		System.out.println("Result : " + result);
		System.out.println(sbinquiryDo);
		return "main/index";
	}

	@RequestMapping(value = "/notice/list/{userIdStr}/{page}", method = { RequestMethod.GET, RequestMethod.POST })
	public String noti( @PathVariable String userIdStr, @PathVariable String page, Model model ) throws Exception {
		
		long userId = StarbocksUtil.getId( userIdStr );
		System.out.println("controller noti() userId : "+ userId);
		int rowCntPerPage = 10;
		System.out.println("controller noti() rowCntPerPage : "+ rowCntPerPage);
		int currentPage = ( int ) StarbocksUtil.getId( page );
		System.out.println("controller noti() currentPage : "+ currentPage);
		
		// api - SbNotifacionDO 수정 0923 ( extends PaginationVO )
		SbNotificationDO sbNotificationDo = new SbNotificationDO();
		sbNotificationDo.setUserId( userId );
		sbNotificationDo.setCurrentPage( currentPage );
		sbNotificationDo.setRowCntPerPage( rowCntPerPage );
		HashMap<String, Object> hm = service.notiList( sbNotificationDo );
		
		int totalnum = ( int ) hm.get( "totalnum" );
		List<SbNotificationDO> searchlist = ( List<SbNotificationDO> ) hm.get( "searchlist" );
		List<SbNotificationDO> printlist = ( List<SbNotificationDO> ) hm.get( "printlist" );
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);
	
		return "notice/list";

	}

	@RequestMapping(value = "/property/askfor", method = RequestMethod.GET)
	public String propertyaskforprocess() throws Throwable {
		return "property/askfor";
	}

	// ajax
	@RequestMapping(value = "/property/askfor/{col}/{val}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<SbLocationInfoDO> statecityprocess(@PathVariable String col, @PathVariable String val)
			throws Throwable {

		List<SbLocationInfoDO> sbzip = null;
		switch (col) {
		case "state":
			sbzip = service.getcity(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
			break;
		case "city":
			sbzip = service.getLegalDistrict(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
			break;
		case "legalDistrictNm":
			sbzip = service.getCommonBldgNm(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
		}
		

		return sbzip;
	}

	@RequestMapping(value = "/property/askforPro", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertyaskforProprocess(@ModelAttribute SbConsultPropertyDO conprodo, Model model) throws Throwable {
		int result = service.askforpro(conprodo);
		System.out.println("controller askforPro conprodo : "+ conprodo);
		
		if (result > 0) {
			Notification noti = new Notification();
			noti.setSender(String.valueOf(conprodo.getUserId()));
			noti.setAddressee("all");
			noti.setMessage(conprodo.getConTitle() + "(이)가 새로 등록 되었습니다.");
			service.sendAskForNotification(noti);
			JSONObject json = new JSONObject();
			json.put("title",conprodo.getConTitle() + "(이)가 새로 등록 되었습니다.");
			json.put("address",conprodo.getState()+" " + conprodo.getCity()+" "+conprodo.getLegalDistrictNm());
			
			SbLocationInfoDO addr = new SbLocationInfoDO();
			addr.setState(conprodo.getState());
			addr.setCity(conprodo.getCity());
			addr.setLegalDistrictNm(conprodo.getLegalDistrictNm());
			addr.setBldgNm(null);
			
			// 주소를 날리면 이 주소에 사무실이 있는 rapms userid array가 출력된다
			long[] userArray = service.addressReturnsUserIds(addr);
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
//			noti.setMessage(sbsearch.getTitle() + "(이)가 새로 등록 되었습니다.");	// 여기에 받는 사람을 JSON을 직렬화해서 던지고, sf 쪽에서 직렬화된 것을 JSON형태로 받아준다
			messageHandler.sendMessage(noti);
			
			consultLogger.info("{},{}", conprodo.getCpId(), this.getTime());
			
			return "property/list";
		} else {
			// 에러 페이지 출력
			System.out.println("controller result else문 : " + result);
			
		}
		model.addAttribute("result", result);
		return "property/askfor";

	}

	@RequestMapping(value = "/property/askforDetail/{consultpid}", method = { RequestMethod.GET, RequestMethod.POST })
	public String askforDetailprocess(@PathVariable String consultpid, @ModelAttribute SbConsultPropertyDO conDo,
			Model model) throws Throwable {
		long conid = StarbocksUtil.getId(consultpid);
		System.out.println("controller askforDetail conid : " + conid);
		SbConsultPropertyDO conpDo = service.getaCon(conid);
		
		model.addAttribute("conproperty", conpDo);

		return "property/askforDetail";

	}

	@RequestMapping(value = "/property/detail/{useridstr}/{propertyId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertydetailprocess(@PathVariable String useridstr,@PathVariable String propertyId, Model model) throws Throwable {
		long userid = StarbocksUtil.getId(useridstr);
		long propid = StarbocksUtil.getId(propertyId);

		useridPropidLogger.info("{},{},{}", userid, propid, 1);
		
		PropertyDetailVO propdetailvo = service.getPropLocVO(propid);
		model.addAttribute("propdetailvo", propdetailvo);

		List<SbPhotoDO> internalPhotos = service.getPhotos(propid, PhotoType.INTERNAL);
		System.out.println("controller internalPhotos : " + internalPhotos);
		
		model.addAttribute("internalPhotos", internalPhotos);
		
		return "property/detail";

	}

	@RequestMapping(value = "/property/addtoFavorite/{propertyId}/{usrId}", method = { RequestMethod.POST })
	@ResponseBody
	public int addtoFavoriteprocess(@PathVariable String propertyId, @PathVariable String usrId) throws Throwable {
		int result = 0;
		long[] favoriteProUsr = StarbocksUtil.getIds(propertyId, usrId);
		System.out.println("controller propertyId :" + propertyId);
		System.out.println("controller usrId :" + usrId);
		result = service.addtoFavorite(favoriteProUsr);
		
		useridPropidLogger.info("{},{},{}", favoriteProUsr[1], favoriteProUsr[0], 1);

		return result;

	}

	@RequestMapping(value = "/property/deletefromFavorite/{propertyId}/{usrId}", method = { RequestMethod.POST })
	@ResponseBody
	public int deletefromFavoriteprocess(@PathVariable String propertyId, @PathVariable String usrId) throws Throwable {
		int result = 0;
		long[] favoriteProUsr = StarbocksUtil.getIds(propertyId, usrId);
		System.out.println("controller propertyId :" + propertyId);
		System.out.println("controller usrId :" + usrId);
		result = service.deletefromFavorite(favoriteProUsr);

		return result;
	}

	@RequestMapping(value = "/property/favorite/{usrid}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertyfavoriteprocess(@PathVariable String usrid, @PathVariable String pagenum,
			@ModelAttribute SbFavoritePropertiesDO favSearch, Model model) throws Throwable {
		int rowCntPerPage = 9;
		long userId = (int) StarbocksUtil.getId(usrid);
		int currentPage = (int) StarbocksUtil.getId(pagenum);
		System.out.println("sessioncontroller userId : " + userId);
		System.out.println("sessioncontroller currentPage : " + currentPage);
		favSearch.setCurrentPage(currentPage);
		favSearch.setRowCntPerPage(rowCntPerPage);
		favSearch.setUserId(userId);
		HashMap<String, Object> hm = service.favoriteproperties(favSearch);

		int totalnum = (int) hm.get("totalnum");
		List<SbPropertyDO> propertylist = (List<SbPropertyDO>) hm.get("propertylist");
		List<SbPropertyDO> printlist = (List<SbPropertyDO>) hm.get("printlist");

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("propertylist", propertylist);
		model.addAttribute("printlist", printlist);

		return "property/favorite";

	}
	
	
	@RequestMapping(value = "/property/list/{useridstr}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertylistprocess(@PathVariable String useridstr, Model model) throws Throwable {
		long userId = 0;
		if ( !useridstr.equals("none")) {
			userId = (int) StarbocksUtil.getId(useridstr);
		}else {
			userId = 0;
		}
		
		List<SbPropertyDO> printlist = service.recommList(userId);
        
        System.out.println("printlist : " + printlist);
        
        int rowCntPerPage = 10;
		int currentPage = 1;
		
		List<SbPropertyDO> searchlist = null;
		if(printlist != null)	searchlist = printlist;
		int totalnum = searchlist.size();
		int recomm = 1;
		model.addAttribute("recomm", recomm);
        model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
        model.addAttribute("printlist", printlist);
        
		return "property/list";
	}

	// ajax
	@RequestMapping(value = "/property/list/{col}/{val}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<SbLocationInfoDO> statecitydanjiprocess(@PathVariable String col, @PathVariable String val)
			throws Throwable {

		List<SbLocationInfoDO> slido = null;
		switch (col) {
		case "state":
			slido = service.getLocationCity(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
			break;
		case "city":
			slido = service.getLegalDistrict(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
			break;
		case "legalDistrictNm":
			slido = service.getPropBldgNm(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
		}
		// System.out.println("controller slido : " + slido);
		return slido;
	}

	@RequestMapping(value = "/property/mine/{userstr}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertymineprocess(@PathVariable String userstr, @PathVariable String pagenum,
			@ModelAttribute SbConsultPropertyDO conproSearch, Model model) throws Throwable {
		int rowCntPerPage = 10;
		long userId = (int) StarbocksUtil.getId(userstr);
		int currentPage = (int) StarbocksUtil.getId(pagenum);
		System.out.println("controller userId : " + userId);
		conproSearch.setCurrentPage(currentPage);
		conproSearch.setRowCntPerPage(rowCntPerPage);
		conproSearch.setUserId(userId);
		HashMap<String, Object> hm = service.getUserConlist(conproSearch);

		int totalnum = (int) hm.get("totalnum");
		List<SbConsultPropertyDO> searchlist = (List<SbConsultPropertyDO>) hm.get("searchlist");
		System.out.println("controller searchlist : "+ searchlist);
		List<SbConsultPropertyDO> printlist = (List<SbConsultPropertyDO>) hm.get("printlist");
		System.out.println("controller printlist : "+ printlist);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);

		return "property/mine";

	}

	@RequestMapping(value = "/property/searchPro/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
	public String propertySearchProprocess(@PathVariable String pagenum, @ModelAttribute SbLocationInfoDO locSearch,
			Model model) throws Throwable {
		int rowCntPerPage = 10;
		int currentPage = (int) StarbocksUtil.getId(pagenum);
		System.out.println("sessioncontroller currentPage : " + currentPage);

		locSearch.setCurrentPage(currentPage);
		locSearch.setRowCntPerPage(rowCntPerPage);

		HashMap<String, Object> hm = service.searchProp(locSearch);
		SbLocationInfoDO locationdo = (SbLocationInfoDO) hm.get("locationdo");

		SbLocationInfoDO afterlocationdo = (SbLocationInfoDO) hm.get("afterlocationdo");
		int totalnum = (int) hm.get("totalnum");
		List<SbPropertyDO> searchlist = (List<SbPropertyDO>) hm.get("searchlist");
		List<SbPropertyDO> printlist = (List<SbPropertyDO>) hm.get("printlist");
		
		int recomm = 0;
		model.addAttribute("recomm", recomm);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowCntPerPage", rowCntPerPage);
		model.addAttribute("totCnt", totalnum);
		model.addAttribute("searchlist", searchlist);
		model.addAttribute("printlist", printlist);

		return "property/list";
	}

	@RequestMapping(value = "/member/signup", method = { RequestMethod.GET, RequestMethod.POST })
	public String signupprocess() throws Throwable {

		return "member/signup";

	}

	@RequestMapping(value = "/member/modifyinfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyinfoprocess() {

		return "member/modifyinfo";

	}

	@RequestMapping(value = "/member/modifyinfoPro", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyinfoProprocess(@ModelAttribute SbMemberDO memberDOParam, Model model) throws Throwable {
		int result = 0;

		SbMemberDO sbMemberDO = service.getMember(memberDOParam);

		if (sbMemberDO != null && sbMemberDO.getPasswd().equals(memberDOParam.getPasswd())) {
			result = 1;
			model.addAttribute("memberdo", sbMemberDO);
			model.addAttribute("modifyresult", result);
			return "member/modifyview";
		} else {
			result = 0;
			model.addAttribute("modifyresult", result);
			return "member/modifyinfo";
		}
	}

	@RequestMapping(value = "/member/modifyview", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyviewprocess(@ModelAttribute SbMemberDO member, Model model) throws Throwable {

		return "member/modifyview";
	}

	@RequestMapping(value = "/member/modifyviewPro", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyviewProprocess(@ModelAttribute SbMemberDO member, Model model) throws Throwable {
		int result = service.modifyviewpro(member);

		model.addAttribute("result", result);
		return "main/index";
	}

	@RequestMapping(value = "/main/loginPro", method = RequestMethod.POST)
	public String loginProprocess(@ModelAttribute SbUserDO userDOParam, Model model) throws Exception {
		int result = 0;

		SbMemberDO sbMemberDO = service.getData(userDOParam); // service에 그냥 객체 자체를 던짐에 유의할 것

		System.out.println("sbMemberDO : " + sbMemberDO);
		if (sbMemberDO != null && sbMemberDO.getPasswd().equals(userDOParam.getPasswd())) {
			result = 1;
			model.addAttribute("sbmemberdo", sbMemberDO);
		} else {
			result = 0;
			model.addAttribute("sbmemberdo", null);
		}

		model.addAttribute("loginresult", result);
		return "main/index";
	}

	@RequestMapping(value = "/member/logoutPro", method = { RequestMethod.GET, RequestMethod.POST })
	public String logoutProprocess(HttpSession session) throws Exception {
		service.logout(session);

		return "redirect:/main";
	}

	@RequestMapping(value = "/etc/contact", method = { RequestMethod.GET, RequestMethod.POST })
	public String contactprocess() throws Throwable {

		return "etc/contact";

	}

	@RequestMapping(value = "/askdisclosure/write/{conuseridstr}/{useridstr}", method = RequestMethod.POST)
	public String askdisclosure(@PathVariable String conuseridstr, @PathVariable String useridstr, @ModelAttribute SbAskDisclosureDO search, Model model) throws Throwable {
		int result = 0;
		long conuserid = StarbocksUtil.getId(conuseridstr);
		long userid = StarbocksUtil.getId(useridstr);
		System.out.println("controller askdisclosure write conuserid : "+conuserid);
		System.out.println("controller askdisclosure write userid : "+userid);
		search.setUserId(conuserid);
		search.setAddedBy(userid);
		
		System.out.println("controller askdisclosure/write search : "+search);
		
		long[] consultpropsid = StarbocksUtil.getIds((search.getPropId().split(" ")));
		for ( long consultpropid: consultpropsid ) {
			useridPropidLogger.info("{},{},{}", search.getAddedBy(), consultpropid, 1);
		}
		
		result = service.addDisclosure(search);

		return "property/askforDetail";

	}

		// 매물 고르기
		@RequestMapping(value = "/property/select/{propsid}/{propsids}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
		public String selectProps(@PathVariable String propsid, @PathVariable String propsids, @PathVariable String pagenum, Model model) throws Throwable {
			String[] psid = propsid.split(" ");
			long[] propSid = StarbocksUtil.getIds(psid);
			
			long[] props = null; 
			if( !propsids.equals("0")) props = StarbocksUtil.getIds(propsids.split(" "));
			
			
			int rowCntPerPage = 10;
			int currentPage = (int) StarbocksUtil.getId(pagenum);
			for (long pid : propSid) {
				System.out.println("propid : " + pid);
			}
			System.out.println("controller selectProps currentPage : "+currentPage);
			if( props != null ) System.out.println("controller selectProps props[] : " + Arrays.toString(props));
			
			
			SbConsultPropertyDO aDo = new SbConsultPropertyDO();  
			
			
			aDo.setCurrentPage(currentPage);
			aDo.setRowCntPerPage(rowCntPerPage);
			// propSid
			StringBuilder strb = new StringBuilder();
			for(int i=0; i<propSid.length; i++ ) {		//added by sf on Sep 27
				strb.append(propSid[i]);				
				strb.append(" ");						
			}											//added by sf on Sep 27
			aDo.setPropsId(strb.toString().trim());
			
			System.out.println("controller selectProps aDo : "+ aDo);
			
			
			HashMap<String, Object> hm = service.askforList(aDo); // long array로 던지면 List<SbPropertyDO>를 출력하는 함수를 만들 것
					
			int totalnum = (int) hm.get("totalnum");
			List<SbPropertyDO> searchlist = (List<SbPropertyDO>) hm.get("searchlist");
			List<SbPropertyDO> originalprintlist = (List<SbPropertyDO>) hm.get("printlist");

			List<SbPropertyDO> printlist = null;
			if( props != null ) printlist = service.addCheckBox(originalprintlist, props);
			else printlist = originalprintlist;
			System.out.println("controller selectProps printlist : "+ printlist);
			
						
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("rowCntPerPage", rowCntPerPage);
			model.addAttribute("totCnt", totalnum);
			model.addAttribute("searchlist", searchlist);
			model.addAttribute("printlist", printlist);
			model.addAttribute("propsid", propsid);
						
			return "property/select";
		}
		
		// ajax
		@RequestMapping(value = "/property/addtoPropsId/{propsid}/{propid}", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String addtoPropsId(@PathVariable String propsid, @PathVariable String propid) throws Throwable {
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
		@RequestMapping(value = "/property/deleteFromPropsId/{propsid}/{propid}", method = { RequestMethod.GET, RequestMethod.POST })
		@ResponseBody
		public String deleteFromPropsId(@PathVariable String propsid, @PathVariable String propid) throws Throwable {
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
				
			}
			else gotpropid="0";
			return gotpropid;
		}
		
		@RequestMapping(value = "/askdisclosure/list/{userstr}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
		public String askdisclosurelist(@PathVariable String userstr, @PathVariable String pagenum,
				@ModelAttribute SbAskDisclosureDO askSearch, Model model) throws Throwable {

			int rowCntPerPage = 10;
			long userId = (int) StarbocksUtil.getId(userstr);
			int currentPage = (int) StarbocksUtil.getId(pagenum);

			System.out.println("askdisclosure list userId : " + userId);

			askSearch.setCurrentPage(currentPage);
			askSearch.setRowCntPerPage(rowCntPerPage);
			askSearch.setAddedBy(userId);

			HashMap<String, Object> hm = service.getAskDislistAddedBy(askSearch);

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

			int result = service.askdisclosurereply(askDo);

			model.addAttribute("result", result);
			System.out.println("Result : " + result);
			System.out.println(askDo);
			return "main/index";
		}
		
		// 매물 고르기
		@RequestMapping(value = "/askdisclosure/select/{propsid}/{useridstr}/{pagenum}", method = { RequestMethod.GET, RequestMethod.POST })
		public String selectAskDis(@PathVariable String propsid, @PathVariable String useridstr, @PathVariable String pagenum, Model model) throws Throwable {
			String[] psid = propsid.split(" ");
			long[] propSid = StarbocksUtil.getIds(psid);
			long userId = StarbocksUtil.getId(useridstr);
			System.out.println("askdisclosure/select userId : "+userId);
						
			int rowCntPerPage = 10;
			int currentPage = (int) StarbocksUtil.getId(pagenum);
			for (long pid : propSid) {
				System.out.println("propid : " + pid);
			}
			System.out.println("controller selectProps currentPage : "+currentPage);
			if( propSid != null ) System.out.println("controller selectProps propSid[] : " + Arrays.toString(propSid));
			
			
			SbAskDisclosureDO aDo = new SbAskDisclosureDO();  
			
			
			aDo.setCurrentPage(currentPage);
			aDo.setRowCntPerPage(rowCntPerPage);
			// propSid
			StringBuilder strb = new StringBuilder();
			for(int i=0; i<propSid.length; i++ ) {		//added by sf on Sep 27
				strb.append(propSid[i]);				
				strb.append(" ");						
			}											//added by sf on Sep 27
			aDo.setPropsId(strb.toString().trim());
			
			System.out.println("controller selectProps aDo : "+ aDo);
			
			
			HashMap<String, Object> hm = service.askDisclosureList(aDo); // long array로 던지면 List<SbPropertyDO>를 출력하는 함수를 만들 것
					
			int totalnum = (int) hm.get("totalnum");
			List<SbPropertyDO> searchlist = (List<SbPropertyDO>) hm.get("searchlist");
			List<SbPropertyDO> originalprintlist = (List<SbPropertyDO>) hm.get("printlist");
			List<SbPropertyDO> printlist = service.predictionList(originalprintlist,userId);

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
		
}
