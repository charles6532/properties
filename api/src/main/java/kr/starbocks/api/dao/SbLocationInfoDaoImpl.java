package kr.starbocks.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.starbocks.api.domain.SbDataObject;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbPropertyDO;

@Component
public class SbLocationInfoDaoImpl implements SbLocationInfoDao {
	@Autowired
	SqlMapFactory sqlMapFactory;
	public int add(SbLocationInfoDO locationDO) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.insert("sblocationinfo.add", locationDO);
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
			rslt = session.delete("sblocationinfo.delete", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public SbLocationInfoDO getData(long id) {
		SbLocationInfoDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sblocationinfo.data", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	public List<SbLocationInfoDO> getList(int start, int end) {
		List<SbLocationInfoDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", Long.valueOf(start));
			map.put("end", Long.valueOf(end));
			rslt = session.selectList("sblocationinfo.list", map);
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
			rslt = session.selectOne("sblocationinfo.next");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;

	}

	public int update(SbLocationInfoDO data) {
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(true);
			rslt = session.update("sblocationinfo.update", data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public SbLocationInfoDO getData( SbPropertyDO propDo ) {
		SbLocationInfoDO rslt = null;
		SqlSession session = null;
		
		
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sblocationinfo.getdata", propDo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public String selectLocId( SbLocationInfoDO locationDO ) {
		String rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sblocationinfo.selectLocId", locationDO );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public String selectLocIdP( SbPropertyDO propertyDO ) {
		String rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sblocationinfo.selectLocId", propertyDO );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
		
	public List<SbPropertyDO> getProperties(SbLocationInfoDO locationDO) {
		List<SbPropertyDO> data = null;
		SqlSession session = null;
		System.out.println("dao locationDO : " + locationDO);
		if(locationDO.getBldgNm() != null && locationDO.getBldgNm().equals("단지이름")) {
			locationDO.setBldgNm(null);
		}
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectList("sblocationinfo.getProperties", locationDO );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("dao data : " + data);
		return data;
	}
	
	public List<SbPropertyDO> getMyProperties(SbLocationInfoDO locationDO) {
		List<SbPropertyDO> data = null;
		SqlSession session = null;
		System.out.println("dao getMyProperties locationDO : " + locationDO);
		if(locationDO.getBldgNm() != null && locationDO.getBldgNm().equals("단지이름")) {
			locationDO.setBldgNm(null);
		}
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectList("sblocationinfo.getmyproperties", locationDO );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println("dao data : " + data);
		return data;
	}
	
	public List<SbLocationInfoDO> getlocationcity(String state){
		List<SbLocationInfoDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectList("sblocationinfo.getlocationcitylist", state);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbLocationInfoDO> getlocationdanji(String city){
		List<SbLocationInfoDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectList("sblocationinfo.getlocationdanjilist", city); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public SbLocationInfoDO getaProp(long id) {
		SbLocationInfoDO data = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			data = session.selectOne("sblocationinfo.getaProp", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;
	}
	
	public List<SbLocationInfoDO> getCity(String state){
		List<SbLocationInfoDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectList("sblocationinfo.getcitylist", state);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public List<SbLocationInfoDO> getBldgNm(long legalDistrictCd){
		List<SbLocationInfoDO> rslt = null;
		SqlSession session = null;
		System.out.println("locationDao getBldgNm legalDistrictCd : " + legalDistrictCd);
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectList("sblocationinfo.getBldgNm", legalDistrictCd);
			System.out.println("locationdao rslt : " + rslt);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public int isEmptyDistrict(String city){
		int rslt = 0;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sblocationinfo.isemptydistrict", city);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public List<SbLocationInfoDO> getLegalDistrict(String city){
		List<SbLocationInfoDO> rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectList("sblocationinfo.getLegalDistrict", city);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	public String getAddress(long zipcode){
		String rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sblocationinfo.getAddress", zipcode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public long selectLegalDistrictCd( String[] dong ) {
		long rslt = 0;
		SqlSession session = null;
		SbLocationInfoDO locdo = new SbLocationInfoDO();
		locdo.setState(dong[0]);
		locdo.setCity(dong[1]);
		locdo.setLegalDistrictNm(dong[2]);
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sblocationinfo.selectLegalDistrictCd", locdo );
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
	public SbDataObject getStateCityLegalDistrictNm(String addr) {
		SbDataObject rslt = null;
		SqlSession session = null;
		try {
			session = sqlMapFactory.openSession(false);
			rslt = session.selectOne("sblocationinfo.getStateCityLegalDistrictNm", addr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rslt;
	}
	
}
