package kr.starbocks.rapms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.starbocks.api.dao.SbInquiryDao;
import kr.starbocks.api.domain.SbInquiryDO;

@Service
public class InquiryServiceImpl implements InquiryService {

	@Autowired
	SbInquiryDao inquiryDao;

	public HashMap<String, Object> inquirylistByPropId(SbInquiryDO inqSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbInquiryDO inquirydo = null;
		inquirydo = inqSearch;
		inqSearch.setStartRow(1);
		inqSearch.setEndRow(500);
		System.out.println("service inqSearch : " + inqSearch);
		List<SbInquiryDO> searchlist = inquiryDao.inquirylistByPropId(inqSearch);

		int totalnum = 0;
		if( searchlist != null) {
			for (SbInquiryDO tem : searchlist) {
				totalnum += 1;
			}
		}
		System.out.println("service totalnum : " + totalnum);
		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		inquirydo.setStartRow((inqSearch.getCurrentPage() - 1) * inqSearch.getRowCntPerPage() + 1);
		
		
		int endrow = inqSearch.getCurrentPage() * inqSearch.getRowCntPerPage();
		if(totalnum<endrow)inquirydo.setEndRow(totalnum);
		else inquirydo.setEndRow(endrow);
		System.out.println("service inquirydo : " + inquirydo);
		
		
		List<SbInquiryDO> printlist = inquiryDao.inquirylistByPropId(inquirydo);

		result.put("printlist", printlist);
		return result;
	}

	public SbInquiryDO getaInq(long id) {
		return inquiryDao.getData(id);
	}

	public int inquirywrite(SbInquiryDO inquiryOD) {
		long id = inquiryDao.getNextSeq();
		inquiryOD.setId(id);

		int result = inquiryDao.sfinqadd(inquiryOD);

		return result;
	}

	public int inquiryreply(SbInquiryDO inquiryOD) {
		long id = inquiryDao.getNextSeq();
		inquiryOD.setId(id);

		int result = inquiryDao.inquiryreply(inquiryOD);

		return result;
	}
}
