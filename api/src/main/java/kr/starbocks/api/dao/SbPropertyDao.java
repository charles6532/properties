package kr.starbocks.api.dao;

import java.util.ArrayList;
import java.util.List;

import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.value.PropertyDetailVO;

public interface SbPropertyDao  extends SbDataAccessObject{

	int add(SbPropertyDO sbPropertyDo);

	int delete(long id);

	SbPropertyDO getData(long id);

	List<SbPropertyDO> getList(int start, int end);

	long getNextSeq();

	int update(SbPropertyDO data);

	long getCount();

	int addprop(SbPropertyDO sbPropertyDo);

	// 매물등록 - 시군 불러오기
	List<String> getSigungu(String city);

	// 매물등록
	int addproppro(SbPropertyDO sbPropertyDo);

	// 매물 상세보기
	SbPropertyDO detail(long id);

	int updateAddress(SbPropertyDO sbPropertyDo);

	// 매물등록 - 동 불러오기
	List<String> getDong(String gu);

	// 매물등록 - 아파트 단지 불러오기
	List<String> getApt(String apt);

	// 내매물 상세보기
	List<SbPropertyDO> myprop(long id);

	// 계약완료 업데이트
	int complete(long propId);

	// 매물 검색
	List<SbPropertyDO> searchProp(SbPropertyDO sbPropertyDo);

	List<SbLocationInfoDO> getBldgNm(long legalDistrictCd);

	List<SbPropertyDO> selectMyProperties(SbConsultPropertyDO search);
	
	ArrayList<SbPropertyDO> longArrayProperties(long[] search);
	
	PropertyDetailVO getPropLocVO(long propId);
	
	List<SbPropertyDO> throwPropIds(long[] propIds);
}