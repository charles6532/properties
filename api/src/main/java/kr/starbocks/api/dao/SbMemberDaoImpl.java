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

import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbInquiryDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbMemberDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.domain.SbZipcodeOrDO;

/**
 *
 @author Playdata
 *
 */
@Component
public class SbMemberDaoImpl implements SbMemberDao  {
	
	
	@Autowired
	SqlMapFactory sqlMapFactory;

	public int add(SbMemberDO sbMember) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbmember.add", sbMember);

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
			rslt = session.delete("sbmember.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public SbMemberDO getData(long id) {
		SbMemberDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbmember.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	public List<SbMemberDO> getList(int start, int end) {
		List<SbMemberDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbmember.list", map);
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
			rslt = session.selectOne("sbmember.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public int update(SbMemberDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); //false를 true로 바꿈
			rslt = session.update("sbmember.update", data);
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
			rslt = session.selectOne("sbmember.getCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	
	public long getmemCount(long id) {
		long rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbmember.getmemCount",id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	

	public List<SbInquiryDO> inquiryGetList(int start, int end) { //문의내역 inquiry/list 관련
		List<SbInquiryDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start",(start));
			map.put("end",(end));
			rslt = session.selectList("sbmember.inquirygetlist", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public int sfinfoupdate(SbMemberDO data) {
		
		System.out.println("SbMemberDO : " + data);
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.update("sbuser.update", data);
			rslt = session.update("sbmember.update", data);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		return rslt;

	}
	public SbMemberDO geteData(String email) {
		SbMemberDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbmember.data", email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}
	
}