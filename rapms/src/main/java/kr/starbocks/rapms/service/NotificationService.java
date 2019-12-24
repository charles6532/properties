package kr.starbocks.rapms.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import kr.starbocks.api.domain.SbNotificationDO;

@Service
public interface NotificationService {
	// 알림 전체 리스트 보기
	public HashMap<String, Object> notiList( SbNotificationDO sbNotificationDo );
}