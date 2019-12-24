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

/**
 * @author Playdata
 *
 */
@Component
public class SbConsultPropertyDaoImpl implements SbConsultPropertyDao {
	
	@Autowired
	SqlMapFactory sqlMapFactory;

	public int add(SbConsultPropertyDO sbConsultProperty) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); //false를 true로 바꿈
			rslt = session.insert("sbconsultproperty.add", sbConsultProperty);
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
			rslt = session.delete("sbconsultproperty.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	public SbConsultPropertyDO getData(long id) {
		SbConsultPropertyDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbconsultproperty.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}

	public List<SbConsultPropertyDO> getList(int start, int end) {
		List<SbConsultPropertyDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbconsultproperty.list", map);
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
			rslt = session.selectOne("sbconsultproperty.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	public int update(SbConsultPropertyDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.update("sbconsultproperty.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbConsultPropertyDO> conproUserList(SbConsultPropertyDO conproSearch){  //회원 페이지 내 매물보기 관련
		List<SbConsultPropertyDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); //true를 false로 바꾸었다
			
			rslt = session.selectList("sbconsultproperty.conproUserList", conproSearch);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbConsultPropertyDO> conproIdList(SbConsultPropertyDO conproSearch){  //회원 페이지 내 매물보기 관련
		List<SbConsultPropertyDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); //true를 false로 바꾸었다
			
			rslt = session.selectList("sbconsultproperty.conproIdList", conproSearch);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public long getzipcode(SbConsultPropertyDO conproSearch) {
		long rslt = 0;
		SqlSession session = null;
		
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbconsultproperty.getzipcode",conproSearch);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbConsultPropertyDO> listByLegalDistrictCd(SbConsultPropertyDO conproSearch){  //회원 페이지 내 매물보기 관련
		List<SbConsultPropertyDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); 
			
			rslt = session.selectList("sbconsultproperty.listByLegalDistrictCd", conproSearch);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
}