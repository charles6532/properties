package kr.starbocks.rapms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.starbocks.api.domain.SbAgentDO;
import kr.starbocks.rapms.service.SbRapmsService;

@Controller
public class SbrapmsController {

	@Autowired
	private SbRapmsService sbrampsService;

//	private static final Logger logger = LoggerFactory.getLogger(SbrapmsController.class);

	// @Autowired
	// private SbrapmsServiceImpl service;
	// int a = 0;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String index() {
		return "common/index";
	}

	// added by 2hw
	// Agent 회원가입
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(SbAgentDO sbAgentDo) { // 회원정보를 받는다 (SbAgentDo ado)
		// 서비스에 받은 회원정보를 넘겨준다 (서비스 클래스 객체 생성해서, signup에 해당하는 서비스 객체의 메소드를 호출하는것)
		// int rs = 0;
		// rs = service.signup( ado );
		return "common/agentsignup";
	}

	// 회원가입 pro
	@RequestMapping(value = "/signupPro", method = { RequestMethod.GET, RequestMethod.POST })
	public String signupPro(@ModelAttribute SbAgentDO sbAgentDo, Model model)
			throws Exception {

//		sbrampsService.signuppro(request, model, sbAgentDo);

		return "pro/signupPro";
	}

	// 알림내역
	@RequestMapping(value = "/noti", method = RequestMethod.GET)
	public String noti() {
		return "notification/listtable";
	}

	// 공개요청내역
	@RequestMapping(value = "/noti/pub", method = RequestMethod.GET)
	public String pub() {
		return "notification/publicrequirelist";
	}

	// Favorite
	@RequestMapping(value = "/fav", method = RequestMethod.GET)
	public String fav() {
		return "favorite/seemyfav";
	}

	// Contact
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		return "contact/contact";
	}

}
