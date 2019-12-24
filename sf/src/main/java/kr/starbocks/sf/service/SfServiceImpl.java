package kr.starbocks.sf.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.starbocks.api.dao.SbAgentDao;
import kr.starbocks.api.dao.SbAskDisclosureDao;
import kr.starbocks.api.dao.SbConsultPropertyDao;
import kr.starbocks.api.dao.SbFavoritePropertiesDao;
import kr.starbocks.api.dao.SbInquiryDao;
import kr.starbocks.api.dao.SbLocationInfoDao;
import kr.starbocks.api.dao.SbMemberDao;
import kr.starbocks.api.dao.SbNotificationDao;
import kr.starbocks.api.dao.SbPhotoDao;
import kr.starbocks.api.dao.SbPropertyDao;
import kr.starbocks.api.dao.SbUserDao;
import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbFavoritePropertiesDO;
import kr.starbocks.api.domain.SbInquiryDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbMemberDO;
import kr.starbocks.api.domain.SbNotificationDO;
import kr.starbocks.api.domain.SbPhotoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.domain.SbUserDO;
import kr.starbocks.api.value.PropertyDetailVO;
import kr.starbocks.api.value.SbHashMapVO;
import kr.starbocks.sf.controller.Notification;
import kr.starbocks.sf.controller.Prediction;
import kr.starbocks.sf.controller.Recommendation;
import kr.starbocks.util.CryptoUtil;
import kr.starbocks.util.MailUtil;
import kr.starbocks.util.StarbocksUtil;
import kr.starbocks.util.codes.NotificationType;
import kr.starbocks.util.codes.PhotoType;

@Service
public class SfServiceImpl implements SfService {
	@Autowired
	SbUserDao userDao;
	@Autowired
	SbMemberDao memberDao;
	@Autowired
	SbInquiryDao inquiryDao;
	@Autowired
	SbConsultPropertyDao conproDao;
	@Autowired
	SbPropertyDao propertyDao;
	@Autowired
	SbLocationInfoDao locationDao;
	@Autowired
	SbHashMapVO hmvo;
	@Autowired
	SbFavoritePropertiesDao favproDao;
	@Autowired
	SbAskDisclosureDao disclosureDao;
	@Autowired
	SbNotificationDao notificationDao;
	@Autowired
	SbPhotoDao photoDao;
	@Autowired
	SbAgentDao agentDao;
	
	
	public SbMemberDO getData(SbUserDO userOD) throws Exception {

		SbMemberDO sbmember = memberDao.geteData(userOD.getEmail());

		return sbmember;
	}

	public SbMemberDO getMember(SbMemberDO memberOD) throws Exception {

		SbMemberDO sbmember = memberDao.geteData(memberOD.getEmail());

		return sbmember;
	}

	public int signup(SbMemberDO memberOD) throws Exception {
		long userId = userDao.getNextSeq();
		memberOD.setUserId(userId);

		int result = userDao.add(memberOD);
		System.out.println("RESULT:" + result);
		if (result > 0) {
			result = memberDao.add(memberOD);
		}

		return result;
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}

	public HashMap<String, Object> getInqlist(SbInquiryDO inqSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbInquiryDO inquirydo = null;
		inquirydo = inqSearch;
		inqSearch.setStartRow(1);
		inqSearch.setEndRow(500);
		System.out.println("service inqSearch : " + inqSearch);
		List<SbInquiryDO> searchlist = inquiryDao.inquirylistByUserId(inqSearch);

		int totalnum = 0;
		if( searchlist != null ) {
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
		
		List<SbInquiryDO> printlist = inquiryDao.inquirylistByUserId(inquirydo);

		result.put("printlist", printlist);
		return result;
	}

	public int inquiryGetCount() {
		return inquiryDao.inquiryGetCount();
	}

	public SbInquiryDO getaInq(long id) {
		return inquiryDao.getData(id);
	}

	public int askforpro(SbConsultPropertyDO conprodo) {

		long cpId = conproDao.getNextSeq();
		conprodo.setCpId(cpId);
		long zipcode = conproDao.getzipcode(conprodo);
		conprodo.setpCpId(0);
		conprodo.setThrCpId(cpId);
		conprodo.setZipcode(zipcode);
		System.out.println("service askforpro conprodo : " + conprodo);
		int result = conproDao.add(conprodo);
		System.out.println("RESULT: " + result);

		return result;
	}

	public int modifyviewpro(SbMemberDO memberOD) {

		int result = userDao.update(memberOD);
		System.out.println("RESULT:" + result);
		if (result > 0) {
			result = memberDao.update(memberOD);
		}

		return result;
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

	public List<SbLocationInfoDO> getcity(String state) {

		List<SbLocationInfoDO> sbs = locationDao.getCity(state);
		return sbs;
	}

	public List<SbLocationInfoDO> getLegalDistrict(String city) {
		System.out.println("getDistrict city: " + city);

		List<SbLocationInfoDO> sbs = null;

		sbs = locationDao.getLegalDistrict(city);

		return sbs;

	}

	public HashMap<String, Object> getconlist(SbConsultPropertyDO conproSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbConsultPropertyDO conprodo = null;
		conprodo = conproSearch;
		conproSearch.setStartRow(1);
		conproSearch.setEndRow(500);
		List<SbConsultPropertyDO> searchlist = conproDao.conproIdList(conproSearch);
		System.out.println("service getconlist searchlist : " + searchlist);
		int totalnum = 0;

		if( searchlist != null) {
			for (SbConsultPropertyDO tem : searchlist) {
				totalnum += 1;
			}
			result.put("totalnum", totalnum);
			result.put("searchlist", searchlist);
		}

		

		conprodo.setStartRow((conproSearch.getCurrentPage() - 1) * conproSearch.getRowCntPerPage() + 1);
		
		
		int endrow = conproSearch.getCurrentPage() * conproSearch.getRowCntPerPage();
		if(totalnum<endrow)conprodo.setEndRow(totalnum);
		else conprodo.setEndRow(endrow);
		System.out.println("service conprodo : " + conprodo);
		
		List<SbConsultPropertyDO> printlist = conproDao.conproIdList(conprodo);
		if( printlist != null ) {
				for (SbConsultPropertyDO tem : printlist) {
					String address = locationDao.getAddress(tem.getZipcode());
					tem.setAddress(address);
				}
				result.put("printlist", printlist);
		}
		return result;
	}

	public List<SbLocationInfoDO> getLocationCity(String state) {
		List<SbLocationInfoDO> slido = locationDao.getlocationcity(state);
		return slido;
	}

	public List<SbLocationInfoDO> getLocationDanji(String city) {
		List<SbLocationInfoDO> slido = locationDao.getlocationdanji(city);
		return slido;
	}

	public List<SbPropertyDO> propertysearch(SbLocationInfoDO locationDOParam) {

		String[] pricelist = locationDOParam.getPriceRange().split(",");

		locationDOParam.setPriceMin(Long.parseLong(pricelist[0]));
		locationDOParam.setPriceMax(Long.parseLong(pricelist[1]));
//		System.out.println("service locationdo : " + locationDOParam);
		System.out.println("priceMin : " + pricelist[0]);
		System.out.println("priceMax : " + pricelist[1]);
		List<SbPropertyDO> result = locationDao.getProperties(locationDOParam);

		return result;
	}

	public Map<String, Object> getPropertyWithLocation(final long propId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		SbPropertyDO propDo = this.propertyDao.getData(propId);
		System.out.println("propDo : " + propDo);
		if (propDo == null)
			return result;

		SbLocationInfoDO locDo = this.locationDao.getData(propDo);
		System.out.println("locDo : " + locDo);

		if (locDo != null)
			result.put("_LOCATION_", locDo);
		String locId = locationDao.selectLocId(locDo);
		System.out.println("locId : " + locId);
		propDo.setAddress(locId);
		propDo.setState(locDo.getState());
		propDo.setCity(locDo.getCity());
		System.out.println("propDo.getAddress() : " + propDo.getAddress());
		int rslt = propertyDao.updateAddress(propDo);
		System.out.println("rslt : " + rslt);
		result.put("_PROPERTY_", propDo);

		return result;
	}

	public int emailCheck(String emailaddress) {
		int result = 0;
		result = userDao.checkId(emailaddress);
		return result;
	}

	public HashMap<String, String> emailAuthprocess(String emailaddress) {
		HashMap<String, String> result = new HashMap<String, String>();

		MailUtil mailinstance = MailUtil.getInstance();
		String controllerRandomStr = StarbocksUtil.getRandomStr(12);
		// String sentAt = "20190812233249"; //Current timestamp
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		String sentAt = sdf.format(timestamp);
		System.out.println("service emailAuthprocess() sentAt: " + sentAt);
		StringBuilder sb = new StringBuilder();
		sb.append(emailaddress).append(" ").append(sentAt).append(" ").append(controllerRandomStr);
		String mesg = CryptoUtil.encrypt("asklf", sb.toString());
		String[] randomKey = sb.toString().split(" ");

//		int authresult = mailinstance.send(emailaddress, "emailAuth", mesg);
		int authresult = mailinstance.send(emailaddress, "emailAuth", randomKey[2]);	// added by sf on October 12
		HashMap<String, String> tmp = new HashMap<String, String>();
		System.out.println("service emailAuthprocess() randomKey[2]: " + randomKey[2]);
		tmp.put("randomKey", randomKey[2]);
		tmp.put(emailaddress, sb.toString());
		if (hmvo.get_AUTH_INFO_SF() == null) {
			hmvo.set_AUTH_INFO_SF(tmp);
			result.put(emailaddress, sb.toString());
		}
		result.put("authresult", Integer.toString(authresult));
		System.out.println("service emailAuthprocess() sb.toString() : " + sb.toString());

		return result;
	}

	public int verifyAuthenticationWith(String authKeyStr) {
		int randomresult = 0;

		if (authKeyStr.equals(hmvo.get_AUTH_INFO_SF().get("randomKey"))) {
			randomresult = 1;
		} else {
			randomresult = 0;
		}

		return randomresult;
	}

	public HashMap<String, Object> searchProp(SbLocationInfoDO locSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbLocationInfoDO locationdo = null;

		if (hmvo.get_SEARCH_CONDITION_SF() == null) {

			locationdo = locSearch;
			HashMap<String, SbLocationInfoDO> tmp = new HashMap<String, SbLocationInfoDO>();
			tmp.put("locationdo", locationdo);

			System.out.println("service if locationdo : " + locationdo);
			hmvo.set_SEARCH_CONDITION_SF(tmp); // save the first condition for looking around with properties

		} else {
			if (locSearch.getCity() == null) {
				// is not the first condition but being along with pagination
				locationdo = hmvo.get_SEARCH_CONDITION_SF().get("locationdo");
				System.out.println("service else-if locationdo : " + locationdo);
			} else {
				// is not the first condition but the second condition
				locationdo = locSearch;
				HashMap<String, SbLocationInfoDO> tmp = new HashMap<String, SbLocationInfoDO>();
				tmp.put("locationdo", locationdo);
				System.out.println("service else-else locationdo : " + locationdo);
				hmvo.set_SEARCH_CONDITION_SF(tmp); // save the first condition in a hashmap for looking around with
												// properties
			}
		}
		locationdo.setStartRow(1);
		locationdo.setEndRow(500);
		List<SbPropertyDO> searchlist = this.propertysearch(locationdo);

		int totalnum = 0;
		if( searchlist != null ) {
			for (SbPropertyDO tem : searchlist) {
					totalnum += 1;
				}
		}
		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		locationdo.setStartRow((locSearch.getCurrentPage() - 1) * locSearch.getRowCntPerPage() + 1);
		
		
		int endrow = locSearch.getCurrentPage() * locSearch.getRowCntPerPage();
		if(totalnum<endrow)locationdo.setEndRow(totalnum);
		else locationdo.setEndRow(endrow);
		System.out.println("service locationdo : " + locationdo);
		
		List<SbPropertyDO> printlist = this.propertysearch(locationdo);

		result.put("printlist", printlist);
		return result;
	}

	public int removeHmVO() {
		int result = 0;

		hmvo.set_AUTH_INFO_SF(null);
		hmvo.set_SEARCH_CONDITION_SF(null);

		return result;
	}

	public SbConsultPropertyDO getaCon(long id) {
		return conproDao.getData(id);
	}

	public int addtoFavorite(long[] prouser) {
		return favproDao.addtoFavorite(prouser);
	}

	public HashMap<String, Object> favoriteproperties(SbFavoritePropertiesDO favSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbFavoritePropertiesDO favpropdo = null;
		favpropdo = favSearch;
		favSearch.setStartRow(1);
		favSearch.setEndRow(500);
		List<SbPropertyDO> favlist = favproDao.favpropsearch(favSearch);

		int totalnum = 0;
		List<SbPropertyDO> searchlist = new ArrayList<SbPropertyDO>();
		for (SbPropertyDO tem : favlist) {
			totalnum += 1;
			searchlist.add(propertyDao.getData(tem.getUserId()));
		}

		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		favpropdo.setStartRow((favSearch.getCurrentPage() - 1) * favSearch.getRowCntPerPage() + 1);
		
		
		int endrow = favSearch.getCurrentPage() * favSearch.getRowCntPerPage();
		if(totalnum<endrow)favpropdo.setEndRow(totalnum);
		else favpropdo.setEndRow(endrow);
		System.out.println("service favpropdo : " + favpropdo);
		
		List<SbPropertyDO> printlist = favproDao.favpropsearch(favpropdo);

		result.put("printlist", printlist);
		return result;
	}

	public int deletefromFavorite(long[] prouser) {
		return favproDao.deletefromFavorite(prouser);
	}
	
	public int addDisclosure(SbAskDisclosureDO aDo) {
		long askid = disclosureDao.getNextSeq();
		aDo.setAskId(askid);
		return disclosureDao.addDisclosure(aDo);
	}
	
	public HashMap<String, Object> getUserConlist(SbConsultPropertyDO conproSearch){
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbConsultPropertyDO conprodo = null;
		conprodo = conproSearch;
		conproSearch.setStartRow(1);
		conproSearch.setEndRow(500);
		List<SbConsultPropertyDO> searchlist = conproDao.conproUserList(conproSearch);
		System.out.println("service getconlist searchlist : " + searchlist);
		int totalnum = 0;

		if( searchlist != null) {
			for (SbConsultPropertyDO tem : searchlist) {
				totalnum += 1;
			}
			result.put("totalnum", totalnum);
			result.put("searchlist", searchlist);
		}

		

		conprodo.setStartRow((conproSearch.getCurrentPage() - 1) * conproSearch.getRowCntPerPage() + 1);
		
		
		int endrow = conproSearch.getCurrentPage() * conproSearch.getRowCntPerPage();
		if(totalnum<endrow)conprodo.setEndRow(totalnum);
		else conprodo.setEndRow(endrow);
		System.out.println("service conprodo : " + conprodo);
		
		
		List<SbConsultPropertyDO> printlist = conproDao.conproUserList(conprodo);
		if( printlist != null ) {
				for (SbConsultPropertyDO tem : printlist) {
					String address = locationDao.getAddress(tem.getZipcode());
					tem.setAddress(address);
				}
				result.put("printlist", printlist);
		}
		return result;
	}
	
	
	public List<SbLocationInfoDO> getCommonBldgNm(String dong) {
		System.out.println("getBldgNm dong: " + dong);
		String[] address = dong.split(" ");
		List<SbLocationInfoDO> sbs = null;
		System.out.println("service getCommonBldgNm address[] : " + address[0] + " " + address[1]+" "+address[2] );
		long legalDistrictCd = locationDao.selectLegalDistrictCd(address);
		System.out.println("getBldgNm legalDistrictCd: " + legalDistrictCd);
		sbs = locationDao.getBldgNm(legalDistrictCd);
		System.out.println("getBldgNm sbs: " + sbs);
		return sbs;

	}
	
	public List<SbLocationInfoDO> getPropBldgNm(String dong) {
		System.out.println("getBldgNm dong: " + dong);
		String[] address = dong.split(" ");
		List<SbLocationInfoDO> sbs = null;
		System.out.println("service getPropBldgNm address[] : " + address[0] + " " + address[1]+" "+address[2] );
		long legalDistrictCd = locationDao.selectLegalDistrictCd(address);
		System.out.println("getBldgNm legalDistrictCd: " + legalDistrictCd);
		
		sbs = propertyDao.getBldgNm(legalDistrictCd);
		System.out.println("getBldgNm sbs: " + sbs);
		return sbs;

	}
	
	public HashMap<String, Object> askDisclosureList(SbAskDisclosureDO aSearch){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		SbAskDisclosureDO askdo = null;

		askdo = aSearch;
		HashMap<String, SbAskDisclosureDO> tmp = new HashMap<String, SbAskDisclosureDO>();
		tmp.put("askdo", askdo);

		System.out.println("service selectMyProperties if askdo : " + askdo);
		hmvo.set_ASK_DISCLOSURE_SF(tmp); // save the first condition for looking around with properties
		
		String stringProps = askdo.getPropsId();
		String[] arrayProps = stringProps.split(" ");
			
		long[] data = new long[arrayProps.length];   
		for (int i = 0; i < arrayProps.length; i++) {   
		    data[i] = Long.parseLong(arrayProps[i]);   
		}
		ArrayList<SbPropertyDO> originalsearchlist = propertyDao.longArrayProperties(data);
		List<SbPropertyDO> searchlist = originalsearchlist;
		int totalnum = 0;
		if( searchlist != null) {
			for (SbPropertyDO tem : searchlist) {
				totalnum += 1;
			}
		}
		System.out.println("service selectMyProperties totalnum : " + totalnum);
		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);
		int fromIndex = ((aSearch.getCurrentPage() - 1) * aSearch.getRowCntPerPage() + 1) - 1 ;
		int toIndex = (aSearch.getCurrentPage() * aSearch.getRowCntPerPage()) -1;
		List<SbPropertyDO> printlist = null;
		if( totalnum > toIndex ) {
			printlist = originalsearchlist.subList(fromIndex, toIndex);
		} else {
			printlist = originalsearchlist.subList(fromIndex, totalnum);
		}

		result.put("printlist", printlist);
		
		return result;

	}
	public HashMap<String, Object> askforList(SbConsultPropertyDO aSearch){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		SbConsultPropertyDO askdo = null;
		
		if (hmvo.get_ASK_FOR_SF() == null) {
			
			askdo = aSearch;
			HashMap<String, SbConsultPropertyDO> tmp = new HashMap<String, SbConsultPropertyDO>();
			tmp.put("askdo", askdo);
			
			System.out.println("service selectMyProperties if askdo : " + askdo);
			hmvo.set_ASK_FOR_SF(tmp); // save the first condition for looking around with properties
			
		} else {
			
			// is not the first condition but being along with pagination
			askdo = hmvo.get_ASK_FOR_SF().get("askdo");
			System.out.println("service selectMyProperties else askdo : " + askdo);
			
		}
		String stringProps = askdo.getPropsId();
		String[] arrayProps = stringProps.split(" ");
			
		long[] data = new long[arrayProps.length];   
		for (int i = 0; i < arrayProps.length; i++) {   
		    data[i] = Long.parseLong(arrayProps[i]);   
		}
		ArrayList<SbPropertyDO> originalsearchlist = propertyDao.longArrayProperties(data);
		List<SbPropertyDO> searchlist = originalsearchlist;
		int totalnum = 0;
		if( searchlist != null) {
			for (SbPropertyDO tem : searchlist) {
				totalnum += 1;
			}
		}
		System.out.println("service selectMyProperties totalnum : " + totalnum);
		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);
		int fromIndex = ((aSearch.getCurrentPage() - 1) * aSearch.getRowCntPerPage() + 1) - 1 ;
		int toIndex = (aSearch.getCurrentPage() * aSearch.getRowCntPerPage()) -1;
		List<SbPropertyDO> printlist = null;
		if( totalnum > toIndex ) {
			printlist = originalsearchlist.subList(fromIndex, toIndex);
		} else {
			printlist = originalsearchlist.subList(fromIndex, totalnum);
		}
		
		result.put("printlist", printlist);
		
		return result;
		
	}
	
	public HashMap<String, String> addtoPropsId(String propsid, String propId) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		if (hmvo.get_ASK_DISCLOSURE_PROP_IDS_SF() == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(propId).append(" ");
			result.put(propsid, sb.toString());

			System.out.println("service addtoPropsId if propsid, sb.toString() : " + propsid+"     "+ sb.toString());
			hmvo.set_ASK_DISCLOSURE_PROP_IDS_SF(result); 
		} else {
			
			String temp = hmvo.get_ASK_DISCLOSURE_PROP_IDS_SF().get(propsid);
			if (temp == null ) temp="";
			String[] temparray = temp.split(" ");
			int duplicated = 0;
			for ( String tmp : temparray ) {
				if( tmp == propId ) duplicated += 1; break;
			}
			
			StringBuilder sb = new StringBuilder();
			if( duplicated != 1) {
				sb.append(temp).append(propId).append(" ");
			} else {
				sb.append(temp).append(" ");
			}
			System.out.println("service addtoPropsId else temp : " + temp);
			result.put(propsid, sb.toString());
			hmvo.set_ASK_DISCLOSURE_PROP_IDS_SF(result);
		}
//		result.put(Long.toString(propids[0]), null);
//		hmvo.setPropsIds(result);
		return result;
	}
	
	public HashMap<String, String> deleteFromPropsId(String propsid, String propId) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		if (hmvo.get_ASK_DISCLOSURE_PROP_IDS_SF() == null) {
			
		} else {
			if( !propId.equals("0") ) {
				String temp = hmvo.get_ASK_DISCLOSURE_PROP_IDS_SF().get(propsid);
				System.out.println("service deleteFromPropsId else temp : " + temp);
				
				String[] temparray = temp.split(" ");
				ArrayList<String> arrayList = new ArrayList<String>();
				for( String temporary : temparray ) {
					arrayList.add(temporary);
				}
				int index = arrayList.indexOf(propId);
				if( index != -1) {
					arrayList.remove(index);
				}
				
				StringBuilder sb = new StringBuilder();
				
				for (String s : arrayList)
				{
				    sb.append(s);
				    sb.append(" ");
				}
				
	
				System.out.println("service deleteFromPropsId else sb.toString() : " + sb.toString());
				
				result.put(propsid, sb.toString());
				hmvo.set_ASK_DISCLOSURE_PROP_IDS_SF(result);
			} else {
				result.put(propsid, null);
				hmvo.set_ASK_DISCLOSURE_PROP_IDS_SF(result);
			}
		}
//		result.put(Long.toString(propids[0]), null);
//		hmvo.setPropsIds(result);
		return result;
	}
	
	public HashMap<String, Object> getAskDislistAddedBy(SbAskDisclosureDO askSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbAskDisclosureDO askdo = null;
		askdo = askSearch;
		askSearch.setStartRow(1);
		askSearch.setEndRow(500);
		System.out.println("service inqSearch : " + askSearch);
		List<SbAskDisclosureDO> searchlist = disclosureDao.getAskDislistAddedBy(askSearch);

		int totalnum = 0;
		if ( searchlist != null ) {
			for (SbAskDisclosureDO tem : searchlist) {
				totalnum += 1;
			}
		}
		System.out.println("service totalnum : " + totalnum);
		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		askdo.setStartRow((askSearch.getCurrentPage() - 1) * askSearch.getRowCntPerPage() + 1);
		
		
		int endrow = askSearch.getCurrentPage() * askSearch.getRowCntPerPage();
		if(totalnum<endrow)askdo.setEndRow(totalnum);
		else askdo.setEndRow(endrow);
		System.out.println("service askdo : " + askdo);
		
		List<SbAskDisclosureDO> printlist = disclosureDao.getAskDislistAddedBy(askdo);

		result.put("printlist", printlist);
		return result;
	}

	public int askDisGetCount() {
		return disclosureDao.askDisclosureGetCount();
	}

	public SbAskDisclosureDO getaAskDis(long id) {
		return disclosureDao.getData(id);
	}
	

	public int askdisclosurereply(SbAskDisclosureDO askSearch) {
		long id = disclosureDao.getNextSeq();
		askSearch.setAskId(id);

		int result = disclosureDao.sfaskdisreply(askSearch);

		return result;
	}
	
	public List<SbPropertyDO> addCheckBox(List<SbPropertyDO> originalprintlist, long[] props){
		if (originalprintlist != null && props != null) {
			for( int i=0 ; i<originalprintlist.size(); i++ ) {
				originalprintlist.get(i).setCheckBox(false);
				for (int j=0 ; j<props.length; j++) {
					if (originalprintlist.get(i).getPropId() == props[j]) originalprintlist.get(i).setCheckBox(true);
					
				}
				
			}
		}
		
		return originalprintlist;
	}
	
	public int sendAskForNotification(Notification noti) {
		SbNotificationDO sbnoti = new SbNotificationDO();
		sbnoti.setAddedBy(Long.parseLong(noti.getSender()));
		sbnoti.setContent(noti.getMessage());
		sbnoti.setCd(NotificationType.PROPERTY.getCode());
		long notiid = notificationDao.getNextSeq();
		sbnoti.setId(notiid);
		
		return notificationDao.sendNotification(sbnoti);
	}
	
	public PropertyDetailVO getPropLocVO(long propId) {
		return propertyDao.getPropLocVO(propId);
	}
	
	public List<SbPhotoDO> getPhotos(long propId, PhotoType type) {
		return photoDao.getList(propId, type.getCode(), 1, Integer.MAX_VALUE);
	}
	
	public HashMap<String, Object> notiList(SbNotificationDO sbNotificationDo) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		SbNotificationDO notido = null;
		if( hmvo.get_NOTIFICATION_LIST_SF() == null ) {
			notido = sbNotificationDo;
			HashMap<String, SbNotificationDO> notimap = new HashMap<String, SbNotificationDO>();
			notimap.put( "notido", notido );
			hmvo.set_NOTIFICATION_LIST_SF( notimap );
		} else {
			if( sbNotificationDo.getAddedBy() == 0 ) {
				notido = hmvo.get_NOTIFICATION_LIST_SF().get( "notido" );
			} else {
				notido = sbNotificationDo;
				HashMap<String, SbNotificationDO> notimap = new HashMap<String, SbNotificationDO>();
				notimap.put( "notido", notido );
				hmvo.set_NOTIFICATION_LIST_SF( notimap );
			}
		}
		
		sbNotificationDo.setStartRow( 1 );
		sbNotificationDo.setEndRow( 50000 );
		List<SbNotificationDO> searchlist = notificationDao.notiList( sbNotificationDo );
		
		int totalnum = 0;
		if( searchlist != null ) {
			for( SbNotificationDO notimap : searchlist ) {
				totalnum += 1;
			}
		}
		
		result.put( "totalnum", totalnum );
		result.put( "searchlist", searchlist );
		notido.setStartRow( ( sbNotificationDo.getCurrentPage() - 1 ) * sbNotificationDo.getRowCntPerPage() + 1 );
		
		int endrow = sbNotificationDo.getCurrentPage() * sbNotificationDo.getRowCntPerPage();
		if( totalnum < endrow ) {
			notido.setEndRow( totalnum );
		} else {
			notido.setEndRow( endrow );
		}
		System.out.println("service notido : "+notido);
		List<SbNotificationDO> printlist = notificationDao.notiList( notido );
		System.out.println("service printlist : "+printlist);
		result.put( "printlist", printlist );
		
		return result;
	}
	
	public long[] addressReturnsUserIds(SbLocationInfoDO addr){
		long[] result = null;
		
		String locIdstr = locationDao.selectLocId(addr);
		System.out.println("service locIdstr : "+ locIdstr);
		long locCd = Long.parseLong(locIdstr.substring(27));
		System.out.println("service locId : " + locCd);
		result = agentDao.addressReturnsUserIds(locCd);
		System.out.println("service addressReturnsUserIds result : "+ Arrays.toString(result));
		return result;
	}
	
	public List<SbPropertyDO> throwPropIds(long[] propIds){
		return propertyDao.throwPropIds(propIds);
	}
	
	public List<SbPropertyDO> recommList(long useridlong) throws IOException {
		List<Recommendation> recommlist = new ArrayList<Recommendation>();
		List<String> allFiles = new ArrayList<String>();
		List<String> allLines = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		LineIterator it = null;
		try {

//            File theFile = new File("e:/alsresult/alsresultweb.json");
            File theFile = new File("/home/encore/alsresultweb/alsresultweb.json");
            System.out.println("Reading files using Apache IO:");
            it = FileUtils.lineIterator(theFile, "UTF-8");
            
            int i = 0;
		    while (it.hasNext()) {
		    	
		        String line = it.nextLine();
		        // do something with line
		        sb.append(line.replaceAll("\\[", "").replaceAll("\\]", ""));
		        sb.append(",");
		    }
		} catch (IOException e) {
            e.printStackTrace();
        } finally {
        	it.close();
        }
				
		sb.append("]");
		System.out.println("total sb.toString() : "+ sb.toString());
		String jsonOnReady = sb.toString().replaceAll("},]", "}]");
		
		System.out.println("jsonOnReady : "+ jsonOnReady);
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
			recommlist = Arrays.asList(objectMapper.readValue(jsonOnReady, Recommendation[].class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("property/list recommlist : "+ recommlist);
        
        ArrayList<Long> recommPropIds = new ArrayList();
        for (Recommendation tmp : recommlist ) {

//        	if(tmp.getUser() == 1000003)   /* test */
        	if(tmp.getUser() == useridlong)
        		recommPropIds.add(tmp.getProperty());
        }
        
        long[] propidlongs = new long[recommPropIds.size()];
        for( int i = 0; i<propidlongs.length; i++ ) {
        	propidlongs[i] = recommPropIds.get(i);
        	
        	System.out.println("property/list propidlongs["+i+"] : "+ propidlongs[i]);
        }
        List<SbPropertyDO> tmpprops = this.throwPropIds(propidlongs);
        List<SbPropertyDO> rslt = new ArrayList<SbPropertyDO>();
        for (long apropid : propidlongs) {
        	for(SbPropertyDO atmp : tmpprops ) {
        		if(apropid == atmp.getPropId()) {
        			rslt.add(atmp);
        		}
        	}
        }
        return rslt;
	}
	
	public List<SbPropertyDO> predictionList(List<SbPropertyDO> originalprintlist, long useridlong) throws IOException{
		List<Prediction> predictionlist = new ArrayList<Prediction>();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		LineIterator it = null;
		try {
//			File theFile = new File("c:/playdata/logs/starbocks/predictionresultweb.json");
            File theFile = new File("/home/encore/predictionresultweb/predictionresultweb.json");
            System.out.println("Reading files using Apache IO:");
            it = FileUtils.lineIterator(theFile, "UTF-8");
            
            int i = 0;
		    while (it.hasNext()) {
		    	
		        String line = it.nextLine();
		        // do something with line
		        sb.append(line.replaceAll("\\[", "").replaceAll("\\]", ""));
		        sb.append(",");
		    }
		} catch (IOException e) {
            e.printStackTrace();
        } finally {
        	it.close();
        }
				
		sb.append("]");
		System.out.println("total sb.toString() : "+ sb.toString());
		String jsonOnReady = sb.toString().replaceAll("},]", "}]");
		
		System.out.println("jsonOnReady : "+ jsonOnReady);
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
			predictionlist = Arrays.asList(objectMapper.readValue(jsonOnReady, Prediction[].class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("predictionlist.get(0) : "+ predictionlist.get(0));
        
        List<SbPropertyDO> rslt = originalprintlist;
        for( SbPropertyDO tmpprintlist : rslt ) {
        	tmpprintlist.setPrediction(0);
        }
        if( rslt!=null && predictionlist != null ) {
        	for (SbPropertyDO tmpprintlist : rslt ) {
        		
        		for( Prediction tmp : predictionlist ) {
        			
        			if( tmp.getUser() == useridlong && tmp.getProperty()==tmpprintlist.getPropId()) {
	        			
	        			tmpprintlist.setPrediction(tmp.getPrediction());
	        			break;
        			}
        			
        		}
        		
        		
        	}
        }
        
        return rslt;
	}
}
