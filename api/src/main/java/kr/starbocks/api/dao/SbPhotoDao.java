package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbPhotoDO;

public interface SbPhotoDao  extends SbDataAccessObject{

	int add(SbPhotoDO sbPhoto);

	int delete(long id);

	SbPhotoDO getData(long id);

	List<SbPhotoDO> getList(long propId, String typeCd, int start, int end) ;

	long getNextSeq();

	List<Long> getNextSeq(int cnt);

	int update(SbPhotoDO data);

}