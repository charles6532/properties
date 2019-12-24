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

import kr.starbocks.api.domain.SbAgentDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbMemberDO;


/**
 * @author Playdata
 *
 */
@Component 
public class SbAgentDaoImpl implements SbAgentDao {
	
	@Autowired
	SqlMapFactory sqlMapFactory;

	public int add(SbAgentDO sbAgent) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.insert("sbagent.add", sbAgent);
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
			rslt = session.delete("sbagent.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	public SbAgentDO getData(long id) {
		SbAgentDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbagent.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}

	public List<SbAgentDO> getList(int start, int end) {
		List<SbAgentDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbagent.list", map);
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
			rslt = session.selectOne("sbagent.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	public int update(SbAgentDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.update("sbagent.update", data);
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
			rslt = session.selectOne("sbagent.getCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	
	public long getagCount(long id) {
		long rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbagent.getagCount",id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	
	public int checkId(long id) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbagent.checkId", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			session.close();
		}
		return rslt;
	}
	
	public SbAgentDO geteData(String email) {
		SbAgentDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbagent.data", email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}
	
	public long getzipcode(SbAgentDO sbAgent) {
		long rslt = 0;
		SqlSession session = null;
		
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbagent.getzipcode",sbAgent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public long[] addressReturnsUserIds(long locCd) {
		long[] array = null;
		List<Long> result = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			result = session.selectList("sbagent.addressReturnsUserIds", locCd);
						
			array = new long[result.size()];
			for(int i = 0; i < result.size(); i++) array[i] = result.get(i);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		return array;
	}

}