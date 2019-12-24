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

import kr.starbocks.api.domain.SbStatementDO;

/**
 * @author Playdata
 *
 */

@Component
public class SbStatementDaoImpl implements SbStatementDao {

	@Autowired
	SqlMapFactory sqlMapFactory;
	
	public int add(SbStatementDO sbStatement) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.insert("sbstatement.add", sbStatement);
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
			rslt = session.delete("sbstatement.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public SbStatementDO getData(long id) {
		SbStatementDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbstatement.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	public List<SbStatementDO> getList(int start, int end) {
		List<SbStatementDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbstatement.list", map);
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
			rslt = session.selectOne("sbstatement.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public int update(SbStatementDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.update("sbstatement.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
		}
	
	//혜원추가
	public long getBalance( long userId ) {
		long result = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); //true이면 오토커밋
			result = session.selectOne("sbstatement.balance", userId );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public int rechargeAdd(SbStatementDO sbStatementDo) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbstatement.rechargeAdd", sbStatementDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	public int payingOut(SbStatementDO sbStatementDo) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbstatement.payingOut", sbStatementDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbStatementDO> salesList(SbStatementDO sbStatementDo){
		List<SbStatementDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			
			rslt = session.selectList("sbstatement.salesList", sbStatementDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
}
