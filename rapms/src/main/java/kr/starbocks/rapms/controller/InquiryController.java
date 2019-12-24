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
import kr.starbocks.rapms.service.InquiryService;
import kr.starbocks.util.StarbocksUtil;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inqService;
	
	
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

		HashMap<String, Object> hm = inqService.inquirylistByPropId(inqSearch);

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

		SbInquiryDO inquirydo = inqService.getaInq(inqid);

		model.addAttribute("inquirydo", inquirydo);

		return "inquiry/detail";

	}

	@RequestMapping(value = "/inquiry/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String inquirywriteprocess() throws Throwable {

		return "inquiry/write";

	}

	@RequestMapping(value = "/inquiry/writePro", method = { RequestMethod.GET, RequestMethod.POST })
	public String inquirywriteProprocess(@ModelAttribute SbInquiryDO sbinquiryDo, Model model) throws Throwable {

		int result = inqService.inquirywrite(sbinquiryDo);

		model.addAttribute("result", result);
		System.out.println("Result : " + result);
		return "common/index";

	}

	@RequestMapping(value = "/inquiry/reply/{inqstrid}/{inqstrThrId}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String inquiryreplyprocess(@PathVariable String inqstrid, @PathVariable String inqstrThrId, Model model) throws Throwable {
		long inqid = StarbocksUtil.getId(inqstrid);
		long inqthrid = StarbocksUtil.getId(inqstrThrId);
		SbInquiryDO sbinquiryDo = new SbInquiryDO();
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

		int result = inqService.inquiryreply(sbinquiryDo);

		model.addAttribute("result", result);
		System.out.println("Result : " + result);
		System.out.println(sbinquiryDo);
		return "common/index";
	}
	
	
	
	

	
}
