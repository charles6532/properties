package kr.starbocks.rapms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.starbocks.api.domain.SbAgentDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.rapms.service.CommonService;

@Controller
public class CommonController {

	@Autowired
	CommonService commonService;

	// sign-in 2hw //
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(@ModelAttribute SbAgentDO agentDo, Model model) {
		int result = 0;

		SbAgentDO sbAgentDo = commonService.getData(agentDo.getEmail());

		if (sbAgentDo != null && agentDo.getPasswd().equals(sbAgentDo.getPasswd())) {
			result = 1;
			System.out.println("commonService signin sbAgentDo: " + sbAgentDo);
			model.addAttribute("sbAgentDo", sbAgentDo);

		} else {
			result = 0;
			model.addAttribute("sbAgentDo", null);
		}
		model.addAttribute("loginresult", result);
		return "common/index";
	}

	// 로그아웃 페이지 //
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logoutProcess(HttpSession session) throws Exception {
		commonService.signout(session);
		return "redirect:/main";
	}

	// 회원정보 수정 비밀번호 확인 //
	@RequestMapping(value = "/modifypro", method = RequestMethod.POST)
	public String modifypro(Model model, @ModelAttribute SbAgentDO agentDo) {
		int result = 0;

		SbAgentDO sbAgentDO = commonService.getAgent(agentDo);

		if (sbAgentDO != null && sbAgentDO.getPasswd().equals(agentDo.getPasswd())) {
			result = 1;
			model.addAttribute("agentdo", sbAgentDO);
			model.addAttribute("modifyresult", result);
			return "common/modify";
		} else {
			result = 0;
			model.addAttribute("modifyresult", result);
			return "common/index";
		}
	}

	// //
	@RequestMapping(value = "/common/confirmMod", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyconfirmMod(@ModelAttribute SbAgentDO agentDo, Model model) {
		int result = commonService.modifyviewpro(agentDo);

		model.addAttribute("result", result);
		return "common/index";
	}

	// ajax
	@RequestMapping(value = "/common/askfor/{col}/{val}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<SbLocationInfoDO> statecityprocess(@PathVariable String col, @PathVariable String val)
			throws Throwable {

		List<SbLocationInfoDO> sbzip = null;
		switch (col) {
		case "state":
			sbzip = commonService.getCity(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
			break;
		case "city":
			sbzip = commonService.getLegalDistrict(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
			break;
		case "legalDistrictNm":
			sbzip = commonService.getBldgNm(val);
			System.out.println("col : " + col);
			System.out.println("val : " + val);
		}

		return sbzip;
	}
}
