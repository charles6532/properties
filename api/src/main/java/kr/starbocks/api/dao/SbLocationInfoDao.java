package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbDataObject;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbPropertyDO;

public interface SbLocationInfoDao  extends SbDataAccessObject{

	int add(SbLocationInfoDO locationDO);

	int delete(long id);

	SbLocationInfoDO getData(long id);

	List<SbLocationInfoDO> getList(int start, int end);

	long getNextSeq();

	int update(SbLocationInfoDO data);

	SbLocationInfoDO getData(SbPropertyDO propDo);

	String selectLocId(SbLocationInfoDO locationDO);

	List<SbPropertyDO> getProperties(SbLocationInfoDO locationDO);

	List<SbLocationInfoDO> getlocationcity(String state);

	List<SbLocationInfoDO> getlocationdanji(String city);

	SbLocationInfoDO getaProp(long id);

	List<SbLocationInfoDO> getCity(String state);

	List<SbLocationInfoDO> getLegalDistrict(String city);
	
	List<SbLocationInfoDO> getBldgNm(long legalDistrictCd);

	int isEmptyDistrict(String city);

	String getAddress(long zipcode);
	
	long selectLegalDistrictCd( String[] dong );
	
	String selectLocIdP(SbPropertyDO propertyDO);
	
	List<SbPropertyDO> getMyProperties(SbLocationInfoDO locationDO);
	
	SbDataObject getStateCityLegalDistrictNm(String addr);

}