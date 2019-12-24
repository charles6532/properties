package kr.starbocks.api.dao;

import java.util.List;

import kr.starbocks.api.domain.SbFavoritePropertiesDO;
import kr.starbocks.api.domain.SbPropertyDO;

public interface SbFavoritePropertiesDao  extends SbDataAccessObject{

	int add(SbFavoritePropertiesDO sbFavoriteProperties);

	int delete(long id);

	SbFavoritePropertiesDO getData(long id);

	List<SbFavoritePropertiesDO> getList(int start, int end);

	long getNextSeq();

	int update(SbFavoritePropertiesDO data);
	
	int addtoFavorite(long[] prouser);
	
	List<SbPropertyDO> favpropsearch(SbFavoritePropertiesDO sbFavoriteProperties);
	
	int deletefromFavorite(long[] prouser);
	
	long[] addressReturnsUserIds(long locCd);

}