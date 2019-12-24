package kr.starbocks.rapms.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.starbocks.api.domain.SbNotificationDO;
import kr.starbocks.rapms.service.NotificationService;
import kr.starbocks.util.StarbocksUtil;

@Controller
public class NotificationController {
	
	@Autowired
	NotificationService service;

	// 알림내역
	@RequestMapping(value = "/noti/{userIdStr}/{page}", method = { RequestMethod.GET, RequestMethod.POST })
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
		
		return "notification/listtable";
	}
	
}
