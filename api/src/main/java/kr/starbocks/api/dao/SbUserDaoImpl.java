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
import org.springframework.stereotype.Repository;

import kr.starbocks.api.domain.SbMemberDO;
import kr.starbocks.api.domain.SbUserDO;



/**
 * @author Playdata
 *
 */
@Repository
public class SbUserDaoImpl implements SbUserDao {
	
	@Autowired
	SqlMapFactory sqlMapFactory;
	
	public int add(SbMemberDO sbMember) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); // true 는 auto commit
			rslt = session.insert("sbuser.add", sbMember);
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
			session = sqlMapFactory.openSession(false);
			rslt = session.delete("sbuser.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public SbUserDO getData(long id) {
		SbUserDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbuser.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	public List<SbUserDO> getList(int start, int end) {
		List<SbUserDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbuser.list", map);
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
			rslt = session.selectOne("sbuser.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public int update(SbUserDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); //false를 true로 바꿈
			rslt = session.update("sbuser.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}
	public int checkId(String email) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbuser.checkId", email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			session.close();
		}
		return rslt;
	}
	public long getCount() {
		long rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbuser.getCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
		
	public SbUserDO geteData(String email) {
		SbUserDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbuser.edata", email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

}