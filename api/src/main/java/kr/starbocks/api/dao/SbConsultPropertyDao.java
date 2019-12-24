package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbConsultPropertyDO;

public interface SbConsultPropertyDao  extends SbDataAccessObject{

	int add(SbConsultPropertyDO sbConsultProperty);

	int delete(long id);

	SbConsultPropertyDO getData(long id);

	List<SbConsultPropertyDO> getList(int start, int end);

	long getNextSeq();

	int update(SbConsultPropertyDO data);

	List<SbConsultPropertyDO> conproIdList(SbConsultPropertyDO conproSearch);

	long getzipcode(SbConsultPropertyDO conproSearch);

	List<SbConsultPropertyDO> listByLegalDistrictCd(SbConsultPropertyDO conproSearch);
	
	List<SbConsultPropertyDO> conproUserList(SbConsultPropertyDO conproSearch);
}