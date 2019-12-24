/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.starbocks.api.domain.SbNotificationDO;

/**
 * @author Playdata
 *
 */
@Component
public class SbNotificationDaoImpl implements SbNotificationDao {
	@Autowired
	SqlMapFactory sqlMapFactory;

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbNotificationDao#add(kr.starbocks.api.domain.SbNotificationDO)
	 */
	public int add(SbNotificationDO sbNotification) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.insert("sbnotification.add", sbNotification);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbNotificationDao#delete(long)
	 */
	public int delete(long id) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.delete("sbnotification.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbNotificationDao#getData(long)
	 */
	public SbNotificationDO getData(long id) {
		SbNotificationDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbnotification.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbNotificationDao#getList(int, int)
	 */
	public List<SbNotificationDO> getList(int start, int end) {
		List<SbNotificationDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbnotification.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbNotificationDao#getNextSeq()
	 */
	public long getNextSeq() {
		long rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbnotification.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbNotificationDao#update(kr.starbocks.api.domain.SbNotificationDO)
	 */
	public int update(SbNotificationDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.update("sbnotification.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public int sendNotification(SbNotificationDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbnotification.sendNotification", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	// 전체 리스트 보기 - 혜원추가
	public List<SbNotificationDO> notiList( SbNotificationDO sbNotificationDo ) {
		List<SbNotificationDO> result = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession( false );
			result = session.selectList("sbnotification.allList", sbNotificationDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}