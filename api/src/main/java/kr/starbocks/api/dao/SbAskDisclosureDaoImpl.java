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

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbFavoritePropertiesDO;
import kr.starbocks.api.domain.SbInquiryDO;
import kr.starbocks.api.domain.SbPropertyDO;



/**
 * @author Playdata
 *
 */
@Component
public class SbAskDisclosureDaoImpl implements SbAskDisclosureDao{
	@Autowired
	SqlMapFactory sqlMapFactory;

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbAskDisclosureDao#add(kr.starbocks.api.domain.SbAskDisclosureDO)
	 */
	public int add(SbAskDisclosureDO sbAskDisclosure) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbaskdisclosure.add", sbAskDisclosure);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbAskDisclosureDao#delete(long)
	 */
	public int delete(long id) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.delete("sbaskdisclosure.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbAskDisclosureDao#getData(long)
	 */
	public SbAskDisclosureDO getData(long id) {
		SbAskDisclosureDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbaskdisclosure.getdata", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbAskDisclosureDao#getList(int, int)
	 */
	public List<SbAskDisclosureDO> getList(int start, int end) {
		List<SbAskDisclosureDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbaskdisclosure.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbAskDisclosureDao#getNextSeq()
	 */
	public long getNextSeq() {
		long rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbaskdisclosure.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	/* (non-Javadoc)
	 * @see kr.starbocks.api.dao.SbAskDisclosureDao#update(kr.starbocks.api.domain.SbAskDisclosureDO)
	 */
	public int update(SbAskDisclosureDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.update("sbaskdisclosure.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public int addDisclosure(SbAskDisclosureDO search) {
		int rslt = 0;
		SqlSession session = null;
		
		System.out.println("disclosureDao search : " + search );
				
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbaskdisclosure.add", search);
			System.out.println("askdisclosuredao result : " + rslt );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	
	public List<SbAskDisclosureDO> getAskDislistAddedBy(SbAskDisclosureDO search) { //문의내역 inquiry/list 관련
		List<SbAskDisclosureDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); //true를 false로 바꾸었다
			
			rslt = session.selectList("sbaskdisclosure.getAskDislistAddedBy", search);
			System.out.println("dao getAskDislist : " + rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public int askDisclosureGetCount() { //문의내역 inquiry/list 관련
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbaskdisclosure.askDisclosureGetCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	
	public int sfaskdisreply(SbAskDisclosureDO data) {
		int rslt = 0;
		SqlSession session = null;
		System.out.println("dao sfaskdisreply data : "+data);
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbaskdisclosure.sfaskdisreply", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbAskDisclosureDO> getAskDislistByUser(SbAskDisclosureDO search) { //문의내역 inquiry/list 관련
		List<SbAskDisclosureDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); //true를 false로 바꾸었다
			
			rslt = session.selectList("sbaskdisclosure.getAskDislistByUser", search);
			System.out.println("dao getAskDislist : " + rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbPropertyDO> getProperties(long[] propIds){
		List<SbPropertyDO> arrayList = null;
		SqlSession session = null;
			
		try {
			session = sqlMapFactory.openSession(false);
			
			arrayList = session.selectList("sbaskdisclosure.getProperties", propIds);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return arrayList;
	}
}