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

import kr.starbocks.api.domain.SbInquiryDO;



/**
 * @author Playdata
 *
 */
@Component
public class SbInquiryDaoImpl implements SbInquiryDao    {
	
	@Autowired
	SqlMapFactory sqlMapFactory;

	
	public int add(SbInquiryDO sbInquiry) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.insert("sbinquiry.add", sbInquiry);
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
			rslt = session.delete("sbinquiry.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public SbInquiryDO getData(long id) {
		SbInquiryDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbinquiry.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}
	public List<SbInquiryDO> getList(int start, int end) {
		List<SbInquiryDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start",(start));
			map.put("end",(end));
			rslt = session.selectList("sbinquiry.list", map);
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
			rslt = session.selectOne("sbinquiry.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public int update(SbInquiryDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.update("sbinquiry.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	public int inquiryGetCount() { //문의내역 inquiry/list 관련
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbinquiry.inquirygetcount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	
	public int sfinqadd(SbInquiryDO sbInquiry) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbinquiry.sfadd", sbInquiry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public int inquiryreply(SbInquiryDO sbInquiry) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbinquiry.sfreply", sbInquiry);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public long oneinqgetCount() {
		long rslt = 0;
		SqlSession session = null;
		String typeCd = "IT02";
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbinquiry.oneinqgetCount",typeCd);
			System.out.println("dao:" + rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public long getComplainCount() {
		long rslt = 0;
		SqlSession session = null;
		String typeCd = "IT21";
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbinquiry.getComplainCount",typeCd);
			System.out.println("dao:" + rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<Map<String, Object>> getonetoinqData(long id) {
		List<Map<String, Object>>  data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectList("sbinquiry.onetoinq", id);
			System.out.println("dao : " + data );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}
		
	public List<Map<String, Object>> getComplainData(long id) {
		List<Map<String, Object>>  data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbinquiry.complaindetail", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}
	
	public List<SbInquiryDO> inquirylistByUserId(SbInquiryDO sbInquiry) { //문의내역 inquiry/list 관련
		List<SbInquiryDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); //true를 false로 바꾸었다
			
			rslt = session.selectList("sbinquiry.inquirylistByUserId", sbInquiry);
			System.out.println("dao inquiryIdlist : " + rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbInquiryDO> inquirylistByPropId(SbInquiryDO sbInquiry) { //문의내역 inquiry/list 관련
		List<SbInquiryDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); //true를 false로 바꾸었다
			
			rslt = session.selectList("sbinquiry.inquirylistByPropId", sbInquiry);
			System.out.println("dao inquiryIdlist : " + rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
}