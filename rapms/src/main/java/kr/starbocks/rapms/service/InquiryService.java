package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.starbocks.api.domain.SbInquiryDO;

public interface InquiryService {

	HashMap<String, Object> inquirylistByPropId(SbInquiryDO inqSearch);

	SbInquiryDO getaInq(long id);

	int inquirywrite(SbInquiryDO inquiryOD);
	
	int inquiryreply(SbInquiryDO inquiryOD);
}