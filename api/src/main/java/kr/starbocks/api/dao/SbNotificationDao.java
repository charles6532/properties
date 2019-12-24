package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbNotificationDO;

public interface SbNotificationDao {

	int add(SbNotificationDO sbNotification);

	int delete(long id);

	SbNotificationDO getData(long id);

	List<SbNotificationDO> getList(int start, int end);

	long getNextSeq();

	int update(SbNotificationDO data);

	int sendNotification(SbNotificationDO data);
	
	//전체 리스트 보기
	public List<SbNotificationDO> notiList( SbNotificationDO sbNotificationDo );
}