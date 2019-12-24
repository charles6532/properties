package kr.starbocks.api.dao;

import java.util.List;
import java.util.Map;

import kr.starbocks.api.domain.SbInquiryDO;

public interface SbInquiryDao  extends SbDataAccessObject{

	int add(SbInquiryDO sbInquiry);

	int delete(long id);

	SbInquiryDO getData(long id);

	List<SbInquiryDO> getList(int start, int end);

	long getNextSeq();

	int update(SbInquiryDO data);

	int inquiryGetCount();
		
	int sfinqadd(SbInquiryDO sbInquiry);
	
	int inquiryreply(SbInquiryDO sbInquiry);
	
	long oneinqgetCount();

	long getComplainCount();
	
	List<Map<String, Object>> getonetoinqData(long id);
		
	List<Map<String, Object>> getComplainData(long id);
	
	List<SbInquiryDO> inquirylistByPropId(SbInquiryDO sbInquiry);
	
	List<SbInquiryDO> inquirylistByUserId(SbInquiryDO sbInquiry);
}