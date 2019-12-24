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

import kr.starbocks.api.domain.SbFavoritePropertiesDO;
import kr.starbocks.api.domain.SbPropertyDO;

/**
 * @author Playdata
 *
 */
@Component
public class SbFavoritePropertiesDaoImpl implements SbFavoritePropertiesDao {
	@Autowired
	SqlMapFactory sqlMapFactory;

	public int add(SbFavoritePropertiesDO sbFavoriteProperties) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbfavoriteproperties.add", sbFavoriteProperties);
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
			rslt = session.delete("sbfavoriteproperties.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	public SbFavoritePropertiesDO getData(long id) {
		SbFavoritePropertiesDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sbfavoriteproperties.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return data;
	}

	public List<SbFavoritePropertiesDO> getList(int start, int end) {
		List<SbFavoritePropertiesDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sbfavoriteproperties.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}

	public long getNextSeq() {
		long rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sbfavoriteproperties.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}

	public int update(SbFavoritePropertiesDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.update("sbfavoriteproperties.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public int addtoFavorite(long[] prouser) {
		int rslt = 0;
		SqlSession session = null;
		
		System.out.println("FavPropDao propId : " +prouser[0]);
		System.out.println("FavPropDao userId : " +prouser[1]);
		
		SbFavoritePropertiesDO favpro = new SbFavoritePropertiesDO();
		favpro.setPropId(prouser[0]);
		favpro.setUserId(prouser[1]);
		
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbfavoriteproperties.addtoFavorite", favpro);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public List<SbPropertyDO> favpropsearch(SbFavoritePropertiesDO sbFavoriteProperties){
		List<SbPropertyDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			
			rslt = session.selectList("sbfavoriteproperties.favprop", sbFavoriteProperties);
			System.out.println("favdao favpropsearch rslt : " + rslt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return rslt;
	}
	public int deletefromFavorite(long[] prouser) {
		int rslt = 0;
		SqlSession session = null;
		
		System.out.println("FavPropDao propId : " +prouser[0]);
		System.out.println("FavPropDao userId : " +prouser[1]);
		
		SbFavoritePropertiesDO favpro = new SbFavoritePropertiesDO();
		favpro.setPropId(prouser[0]);
		favpro.setUserId(prouser[1]);
		
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sbfavoriteproperties.deletefromFavorite", favpro);
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
			result = session.selectList("sbfavoriteproperties.addressReturnsUserIds", locCd);
						
			array = new long[result.size()];
			for(int i = 0; i < result.size(); i++) array[i] = result.get(i);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		return array;
	}
	
	
}