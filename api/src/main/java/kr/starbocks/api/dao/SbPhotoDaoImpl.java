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

import kr.starbocks.api.domain.SbPhotoDO;

/**
 * @author Playdata
 *
 */
@Component
public class SbPhotoDaoImpl implements SbPhotoDao {
	@Autowired
	SqlMapFactory sqlMapFactory;

	public int add(SbPhotoDO sbPhoto) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); //true로 바꿈
			rslt = session.insert("sbphoto.add", sbPhoto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public int delete(long id) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.delete("sbphoto.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public SbPhotoDO getData(long id) {
		SbPhotoDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			data = session.selectOne("sbphoto.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	public List<SbPhotoDO> getList(long propId, String typeCd, int start, int end) {
		List<SbPhotoDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("propId", Long.valueOf(propId));
			map.put("typeCd", typeCd);
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbphoto.list", map);
			
			System.out.println("photodao rslt : "+ rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public long getNextSeq() {
		long rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbphoto.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public List<Long> getNextSeq(final int cnt) {
		List<Long> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.selectList("sbphoto.nextlist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public int update(SbPhotoDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.update("sbphoto.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
}