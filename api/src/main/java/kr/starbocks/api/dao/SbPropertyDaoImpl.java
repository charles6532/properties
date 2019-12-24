/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbFavoritePropertiesDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.value.PropertyDetailVO;

/**
 * @author Playdata
 *
 */
@Component
public class SbPropertyDaoImpl implements SbPropertyDao {
	@Autowired
	SqlMapFactory sqlMapFactory;

	public int add(SbPropertyDO sbPropertyDo) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbproperty.add", sbPropertyDo);
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
			rslt = session.delete("sbproperty.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public SbPropertyDO getData(long id) {
		SbPropertyDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbproperty.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	public List<SbPropertyDO> getList(int start, int end) {
		List<SbPropertyDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbproperty.list", map);
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
			rslt = session.selectOne("sbproperty.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public int update(SbPropertyDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.update("sbproperty.update", data);
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
			rslt = session.selectOne("sbproperty.getCount");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}

	public int addprop(SbPropertyDO sbPropertyDo) {
		int result = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); // true이면 오토커밋
			result = session.insert("sbproperty.add", sbPropertyDo);
			System.out.println("daoimpl");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 매물등록 - 시군 불러오기
	public List<String> getSigungu(String city) {
		List<String> result = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); // true이면 오토커밋
			result = session.selectList("sbproperty.dropdown", city);
			// System.out.println( "아 왜 안돼" );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 매물등록
	public int addproppro(SbPropertyDO sbPropertyDo) {
		int result = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); // true이면 오토커밋
			result = session.insert("sbproperty.add", sbPropertyDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 매물 상세보기
	public SbPropertyDO detail(long id) {
		SbPropertyDO sbPropertyDo = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false); // true이면 오토커밋
			sbPropertyDo = session.selectOne("sbproperty.detail", id);
			// System.out.println( "sbPropertyDo : " + sbPropertyDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sbPropertyDo;
	}

	public int updateAddress(SbPropertyDO sbPropertyDo) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.update("sbproperty.updateAddress", sbPropertyDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	// 매물등록 동 불러오기
	public List<String> getDong(String gu) {
		List<String> result = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); // true이면 오토커밋
			result = session.selectList("sbproperty.dropdownDong", gu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 매물등록 단지명 불러오기
	public List<String> getApt(String apt) {
		List<String> result = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true); // true이면 오토커밋
			result = session.selectList("sbproperty.dropdownApt", apt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 내매물 상세보기
	public List<SbPropertyDO> myprop(long id) {
		List<SbPropertyDO> result = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			result = session.selectList("sbproperty.myprop", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 계약완료 코드 업데이트
	public int complete(long propId) {
		System.out.println("DAO propId 확인 : " + propId);
		int result = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			result = session.update("sbproperty.complete", propId);
			System.out.println("DAO result 확인 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// 매물검색
	public List<SbPropertyDO> searchProp(SbPropertyDO sbPropertyDo) {
		List<SbPropertyDO> list = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			list = session.selectList("sbproperty.searchProp", sbPropertyDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<SbLocationInfoDO> getBldgNm(long legalDistrictCd){
		List<SbLocationInfoDO> rslt = null;
		SqlSession session = null;
		
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectList("sbproperty.getdistrictlist", legalDistrictCd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbPropertyDO> selectMyProperties(SbConsultPropertyDO search){
		List<SbPropertyDO> rslt = null;
		SqlSession session = null;
		System.out.println("dao selectMyProperties search : "+ search);
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectList("sbproperty.selectMyProperties", search);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public ArrayList<SbPropertyDO> longArrayProperties(long[] props){
		ArrayList<SbPropertyDO> arrayList = new ArrayList<SbPropertyDO>();
		SqlSession session = null;
		
		
		for( long propid : props ) {
			
			try {
				session = sqlMapFactory.openSession(false);
				SbPropertyDO rslt = session.selectOne("sbproperty.longArrayProperties", propid);
				if (rslt != null) arrayList.add(rslt);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			
		}
		
		return arrayList;
	}
	
	public PropertyDetailVO getPropLocVO(long propId) {
		PropertyDetailVO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbproperty.getPropLocVO", propId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}
	
	public List<SbPropertyDO> throwPropIds(long[] propIds){
		List<SbPropertyDO> arrayList = null;
		SqlSession session = null;
			
		try {
			session = sqlMapFactory.openSession(false);
			
			arrayList = session.selectList("sbproperty.throwPropIds", propIds);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return arrayList;
	}
		
}