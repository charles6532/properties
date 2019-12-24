package kr.starbocks.rapms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.starbocks.api.dao.SbAskDisclosureDao;
import kr.starbocks.api.dao.SbConsultPropertyDao;
import kr.starbocks.api.dao.SbFavoritePropertiesDao;
import kr.starbocks.api.dao.SbInquiryDao;
import kr.starbocks.api.dao.SbLocationInfoDao;
import kr.starbocks.api.dao.SbMemberDao;
import kr.starbocks.api.dao.SbPropertyDao;
import kr.starbocks.api.dao.SbUserDao;
import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.value.SbHashMapVO;
import kr.starbocks.util.codes.DisclosureStatus;

@Service
public class AskDisclosureServiceImpl implements AskDisclosureService {
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
	SbFavoritePropertiesDao favproDao;
	@Autowired
	SbAskDisclosureDao disclosureDao;
	@Autowired
	SbHashMapVO hmvo;
	
	/* (non-Javadoc)
	 * @see kr.starbocks.rapms.service.AskDisclosureService#selectMyProperties(kr.starbocks.api.domain.SbAskDisclosureDO)
	 */
	@Override
	public HashMap<String, Object> selectMyProperties(SbAskDisclosureDO aSearch){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		SbAskDisclosureDO askdo = null;

		if (hmvo.get_ASK_DISCLOSURE_SF() == null) {

			askdo = aSearch;
			HashMap<String, SbAskDisclosureDO> tmp = new HashMap<String, SbAskDisclosureDO>();
			tmp.put("askdo", askdo);

			System.out.println("service selectMyProperties if askdo : " + askdo);
			hmvo.set_ASK_DISCLOSURE_SF(tmp); // save the first condition for looking around with properties

		} else {
			
			// is not the first condition but being along with pagination
			askdo = hmvo.get_ASK_DISCLOSURE_SF().get("askdo");
			System.out.println("service selectMyProperties else askdo : " + askdo);
			
		}
		String stringProps = askdo.getPropsId();
		System.out.println("service stringProps : "+ stringProps);
		String[] arrayProps = stringProps.split(" ");
		System.out.println("service arrayProps : "+Arrays.toString(arrayProps));
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
	
	/* (non-Javadoc)
	 * @see kr.starbocks.rapms.service.AskDisclosureService#addtoPropsId(java.lang.String, java.lang.String)
	 */
	@Override
	public HashMap<String, String> addtoPropsId(String propsid, String propId) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		if (hmvo.get_ASK_DISCLOSURE_PROP_IDS_RAPMS() == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(propId).append(" ");
			result.put(propsid, sb.toString());

			System.out.println("service addtoPropsId if propsid, sb.toString() : " + propsid+"     "+ sb.toString());
			hmvo.set_ASK_DISCLOSURE_PROP_IDS_RAPMS(result); 
		} else {
			
			String temp = hmvo.get_ASK_DISCLOSURE_PROP_IDS_RAPMS().get(propsid);
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
			hmvo.set_ASK_DISCLOSURE_PROP_IDS_RAPMS(result);
		}
//		result.put(Long.toString(propids[0]), null);
//		hmvo.setPropsIds(result);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see kr.starbocks.rapms.service.AskDisclosureService#deleteFromPropsId(java.lang.String, java.lang.String)
	 */
	@Override
	public HashMap<String, String> deleteFromPropsId(String propsid, String propId) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		if (hmvo.get_ASK_DISCLOSURE_PROP_IDS_RAPMS() == null) {
			
		} else {
			if( !propId.equals("0") ) {
				String temp = hmvo.get_ASK_DISCLOSURE_PROP_IDS_RAPMS().get(propsid);
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
				hmvo.set_ASK_DISCLOSURE_PROP_IDS_RAPMS(result);
			} else {
				result.put(propsid, null);
				hmvo.set_ASK_DISCLOSURE_PROP_IDS_RAPMS(result);
			}
		}
//		result.put(Long.toString(propids[0]), null);
//		hmvo.setPropsIds(result);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see kr.starbocks.rapms.service.AskDisclosureService#getAskDislist(kr.starbocks.api.domain.SbAskDisclosureDO)
	 */
	@Override
	public HashMap<String, Object> getAskDislistByUser(SbAskDisclosureDO askSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbAskDisclosureDO askdo = null;
		askdo = askSearch;
		askSearch.setStartRow(1);
		askSearch.setEndRow(500);
		System.out.println("service getAskDislistByUser : " + askSearch);
		List<SbAskDisclosureDO> searchlist = disclosureDao.getAskDislistByUser(askSearch);

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
		
		List<SbAskDisclosureDO> printlist = disclosureDao.getAskDislistByUser(askdo);

		result.put("printlist", printlist);
		return result;
	}

	/* (non-Javadoc)
	 * @see kr.starbocks.rapms.service.AskDisclosureService#askDisGetCount()
	 */
	@Override
	public int askDisGetCount() {
		return disclosureDao.askDisclosureGetCount();
	}

	/* (non-Javadoc)
	 * @see kr.starbocks.rapms.service.AskDisclosureService#getaAskDis(long)
	 */
	@Override
	public SbAskDisclosureDO getaAskDis(long id) {
		return disclosureDao.getData(id);
	}
	

	/* (non-Javadoc)
	 * @see kr.starbocks.rapms.service.AskDisclosureService#askdisclosurereply(kr.starbocks.api.domain.SbAskDisclosureDO)
	 */
	@Override
	public int askdisclosurereply(SbAskDisclosureDO askSearch) {
		long id = disclosureDao.getNextSeq();
		askSearch.setAskId(id);
		askSearch.setPublicCd(DisclosureStatus.DISCLOSED.getCode());
		askSearch.setpAskId(askSearch.getThrAskId());

		int result = disclosureDao.sfaskdisreply(askSearch);

		return result;
	}
	
	public List<SbPropertyDO> getProperties(long[] array){
		return disclosureDao.getProperties(array);
	}
	
}
