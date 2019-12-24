package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.starbocks.api.dao.SbNotificationDao;
import kr.starbocks.api.domain.SbNotificationDO;
import kr.starbocks.api.value.SbHashMapVO;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	SbNotificationDao sbNotificationDao;
	
	@Autowired
	SbHashMapVO hmvo;

	@Override
	public HashMap<String, Object> notiList(SbNotificationDO sbNotificationDo) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		SbNotificationDO notido = null;
		if( hmvo.get_NOTIFICATION_LIST_RAPMS() == null ) {
			notido = sbNotificationDo;
			HashMap<String, SbNotificationDO> notimap = new HashMap<String, SbNotificationDO>();
			notimap.put( "notido", notido );
			hmvo.set_NOTIFICATION_LIST_RAPMS( notimap );
		} else {
			if( sbNotificationDo.getAddedBy() == 0 ) {
				notido = hmvo.get_NOTIFICATION_LIST_RAPMS().get( "notido" );
			} else {
				notido = sbNotificationDo;
				HashMap<String, SbNotificationDO> notimap = new HashMap<String, SbNotificationDO>();
				notimap.put( "notido", notido );
				hmvo.set_NOTIFICATION_LIST_RAPMS( notimap );
			}
		}
		
		sbNotificationDo.setStartRow( 1 );
		sbNotificationDo.setEndRow( 50000 );
		List<SbNotificationDO> searchlist = sbNotificationDao.notiList( sbNotificationDo );
		
		int totalnum = 0;
		if( searchlist != null ) {
			for( SbNotificationDO notimap : searchlist ) {
				totalnum += 1;
			}
		}
		
		result.put( "totalnum", totalnum );
		result.put( "searchlist", searchlist );
		notido.setStartRow( ( sbNotificationDo.getCurrentPage() - 1 ) * sbNotificationDo.getRowCntPerPage() + 1 );
		
		int endrow = sbNotificationDo.getCurrentPage() * sbNotificationDo.getRowCntPerPage();
		if( totalnum < endrow ) {
			notido.setEndRow( totalnum );
		} else {
			notido.setEndRow( endrow );
		}
		System.out.println("service notido : "+notido);
		List<SbNotificationDO> printlist = sbNotificationDao.notiList( notido );
		System.out.println("service printlist : "+printlist);
		result.put( "printlist", printlist );
		
		return result;
	}
}
