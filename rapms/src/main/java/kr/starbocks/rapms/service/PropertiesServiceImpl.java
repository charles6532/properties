package kr.starbocks.rapms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.starbocks.api.dao.SbConsultPropertyDao;
import kr.starbocks.api.dao.SbFavoritePropertiesDao;
import kr.starbocks.api.dao.SbLocationInfoDao;
import kr.starbocks.api.dao.SbPhotoDao;
import kr.starbocks.api.dao.SbPropertyDao;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbDataObject;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbPhotoDO;
import kr.starbocks.api.domain.SbPropertyDO;
import kr.starbocks.api.value.PropertyDetailVO;
import kr.starbocks.api.value.SbHashMapVO;
import kr.starbocks.util.ImageUtil;
import kr.starbocks.util.codes.PhotoType;
import kr.starbocks.util.codes.PropertyStatus;

@Service
public class PropertiesServiceImpl implements PropertiesService {

	@Autowired
	SbPropertyDao sbPropertyDao;
	@Autowired
	SbPhotoDao sbPhotoDao;
	@Autowired
	SbPhotoDO sbPhotoDo;
	@Autowired
	SbLocationInfoDao sbLocationDao;
	@Autowired
	SbConsultPropertyDao conproDao;
	@Autowired
	SbFavoritePropertiesDao favProDao;
	@Autowired
	SbHashMapVO hmvo;
	
	
	public HashMap<String, Object> searchConditionRapms(SbLocationInfoDO searchObj) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbLocationInfoDO doObj = null;
		
		if (hmvo.get_SEARCH_CONDITION_RAPMS() == null) {

			doObj = searchObj;
			HashMap<String, SbLocationInfoDO> tmp = new HashMap<String, SbLocationInfoDO>();
			tmp.put("doObj", doObj);

			System.out.println("service if doObj : " + doObj);
			hmvo.set_SEARCH_CONDITION_RAPMS((HashMap<String, SbLocationInfoDO>) tmp); // save the first condition for looking around with properties

		} else {
			if (searchObj.getUserId() == 0) {
				// is not the first condition but being along with pagination
				doObj = hmvo.get_SEARCH_CONDITION_RAPMS().get("doObj");
				System.out.println("service else-if doObj : " + doObj);
			} else {
				// is not the first condition but the second condition
				doObj = searchObj;
				HashMap<String, SbLocationInfoDO> tmp = new HashMap<String, SbLocationInfoDO>();
				tmp.put("locationdo", doObj);
				System.out.println("service else-else locationdo : " + doObj);
				hmvo.set_SEARCH_CONDITION_RAPMS((HashMap<String, SbLocationInfoDO>) tmp); // save the first condition in a hashmap for looking around with
												// properties
			}
		}
		doObj.setStartRow(1);
		doObj.setEndRow(500);
		List<SbPropertyDO> searchlist = this.priceSetter(doObj);
		System.out.println("service commonPagination searchlist : "+ searchlist);
		int totalnum = 0;
		if( searchlist != null) {
			for (SbPropertyDO tem : searchlist) {
				totalnum += 1;
			}
		}

		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		doObj.setStartRow((searchObj.getCurrentPage() - 1) * searchObj.getRowCntPerPage() + 1);
		
		
		int endrow = searchObj.getCurrentPage() * searchObj.getRowCntPerPage();
		if(totalnum<endrow)doObj.setEndRow(totalnum);
		else doObj.setEndRow(endrow);
		System.out.println("service commonPagination doObj : " + doObj);
		
		List<SbPropertyDO> printlist = this.priceSetter(doObj);

		result.put("printlist", printlist);
		
		return result;
	}
	
	public HashMap<String, Object> searchMineRapm(SbLocationInfoDO searchObj) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbLocationInfoDO doObj = null;
		
		if (hmvo.get_MY_PROP_SEARCH_RAPMS() == null) {

			doObj = searchObj;
			HashMap<String, SbLocationInfoDO> tmp = new HashMap<String, SbLocationInfoDO>();
			tmp.put("doObj", doObj);

			System.out.println("service if doObj : " + doObj);
			hmvo.set_MY_PROP_SEARCH_RAPMS((HashMap<String, SbLocationInfoDO>) tmp); // save the first condition for looking around with properties

		} else {
			if (searchObj.getUserId() == 0) {
				// is not the first condition but being along with pagination
				doObj = hmvo.get_MY_PROP_SEARCH_RAPMS().get("doObj");
				System.out.println("service else-if doObj : " + doObj);
			} else {
				// is not the first condition but the second condition
				doObj = searchObj;
				HashMap<String, SbLocationInfoDO> tmp = new HashMap<String, SbLocationInfoDO>();
				tmp.put("locationdo", doObj);
				System.out.println("service else-else locationdo : " + doObj);
				hmvo.set_MY_PROP_SEARCH_RAPMS((HashMap<String, SbLocationInfoDO>) tmp); // save the first condition in a hashmap for looking around with
												// properties
			}
		}
		doObj.setStartRow(1);
		doObj.setEndRow(500);
		List<SbPropertyDO> searchlist = this.priceSetter(doObj);
		System.out.println("service commonPagination searchlist : "+ searchlist);
		int totalnum = 0;
		if( searchlist != null) {
			for (SbPropertyDO tem : searchlist) {
				totalnum += 1;
			}
		}

		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		doObj.setStartRow((searchObj.getCurrentPage() - 1) * searchObj.getRowCntPerPage() + 1);
		
		
		int endrow = searchObj.getCurrentPage() * searchObj.getRowCntPerPage();
		if(totalnum<endrow)doObj.setEndRow(totalnum);
		else doObj.setEndRow(endrow);
		System.out.println("service commonPagination doObj : " + doObj);
		
		List<SbPropertyDO> printlist = this.priceSetter(doObj);

		result.put("printlist", printlist);
		
		return result;
	}
	
	public HashMap<String, Object> selectMyProperties(SbConsultPropertyDO searchCon){
HashMap<String, Object> result = new HashMap<String, Object>();
		
		SbConsultPropertyDO consultdo = null;

		if (hmvo.get_MY_PROPERTIES_RAPMS() == null) {

			consultdo = searchCon;
			HashMap<String, SbConsultPropertyDO> tmp = new HashMap<String, SbConsultPropertyDO>();
			tmp.put("consultdo", consultdo);

			System.out.println("service selectMyProperties if consultdo : " + consultdo);
			hmvo.set_MY_PROPERTIES_RAPMS(tmp); // save the first condition for looking around with properties

		} else {
			
			// is not the first condition but being along with pagination
			consultdo = hmvo.get_MY_PROPERTIES_RAPMS().get("consultdo");
			System.out.println("service selectMyProperties else consultdo : " + consultdo);
			
		}
		consultdo.setStartRow(1);
		consultdo.setEndRow(500);
		List<SbPropertyDO> searchlist = sbPropertyDao.selectMyProperties(consultdo);
		
		int totalnum = 0;
		if( searchlist != null) {
			for (SbPropertyDO tem : searchlist) {
				totalnum += 1;
			}
		}
		System.out.println("service selectMyProperties totalnum : " + totalnum);
		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		consultdo.setStartRow((searchCon.getCurrentPage() - 1) * searchCon.getRowCntPerPage() + 1);
		consultdo.setEndRow(searchCon.getCurrentPage() * searchCon.getRowCntPerPage());
		
		int endrow = searchCon.getCurrentPage() * searchCon.getRowCntPerPage();
		if(totalnum<endrow)consultdo.setEndRow(totalnum);
		else consultdo.setEndRow(endrow);
		System.out.println("service consultdo : " + consultdo);
		
		List<SbPropertyDO> printlist = sbPropertyDao.selectMyProperties(consultdo);

		result.put("printlist", printlist);
		
		return result;
	}
	
	public List<SbPropertyDO> priceSetter(SbLocationInfoDO searchObj) {		
		
		if( searchObj.getPriceRange() != null) {
			String[] pricelist = searchObj.getPriceRange().split(",");
	
			searchObj.setPriceMin(Long.parseLong(pricelist[0]));
			searchObj.setPriceMax(Long.parseLong(pricelist[1]));
			System.out.println("service commonPriceSetter searchObj : " + searchObj);
			System.out.println("priceMin : " + pricelist[0]);
			System.out.println("priceMax : " + pricelist[1]);
		}
		List<SbPropertyDO> result = null;
		if (searchObj.getUserId() != 0) result = sbLocationDao.getMyProperties((SbLocationInfoDO) searchObj);
		else result = sbLocationDao.getProperties((SbLocationInfoDO) searchObj);

		return result;
	}
	// 매물 상세보기
	@Override
	public SbPropertyDO detail(SbPropertyDO sbPropertyDo, Model model) {

		sbPropertyDo = sbPropertyDao.detail(sbPropertyDo.getPropId());

		return sbPropertyDo;
	}
	
	public int add (String rootPath, SbPropertyDO sbPropertyDo) {
		
		long propId = sbPropertyDao.getNextSeq();
		sbPropertyDo.setPropId(propId);
		
		long photoId = 0;
	
		if( ! ArrayUtils.isEmpty(sbPropertyDo.getPhotoNames()) ) {
			StringBuilder sb = new StringBuilder();
			for ( CommonsMultipartFile photo : sbPropertyDo.getPhotoNames() ) {
				
				photoId = sbPhotoDao.getNextSeq();
				SbPhotoDO sbPhotoDo = new SbPhotoDO();
				sbPhotoDo.setPhotoId( photoId );
				sbPhotoDo.setPhotoName( photo.getFileItem().getName() );
				sbPhotoDo.setPropId( propId );
				sbPhotoDo.setTypeCd( PhotoType.INTERNAL.getCode() );
				sb.append(sbPhotoDo.getPhotoName());
				sb.append(" ");
				
				sbPhotoDao.add( sbPhotoDo );
				ImageUtil.storeAndMakeThumbnail(rootPath, photo, photoId);
			}
			String temp = sb.toString().trim();
			sbPropertyDo.setPhotoName(temp);
			sb = null;
		}
		if( ! ArrayUtils.isEmpty(sbPropertyDo.getPhotoRightImages()) ) {
			StringBuilder sb = new StringBuilder();
			for ( CommonsMultipartFile photo : sbPropertyDo.getPhotoRightImages() ) {
				photoId = sbPhotoDao.getNextSeq();
				SbPhotoDO sbPhotoDo = new SbPhotoDO();
				sbPhotoDo.setPhotoId( photoId );
				sbPhotoDo.setPhotoName( photo.getFileItem().getName() );
				sbPhotoDo.setPropId( propId );
				sbPhotoDo.setTypeCd( PhotoType.RIGHT.getCode() );
				//나중에 사진 불러올떄는 propId로 PhotoType.Right해서 가져오면됨
				sb.append(sbPhotoDo.getPhotoName());
				sb.append(" ");
				
				sbPhotoDao.add( sbPhotoDo );
				ImageUtil.storeAndMakeThumbnail(rootPath, photo, photoId);
			}
			String temp = sb.toString().trim();
			sbPropertyDo.setPhotoRight(temp);
			sb = null;
		}
		System.out.println("PropertiesServiceImpl add sbPropertyDo : " + sbPropertyDo);
		sbPropertyDo.setAddress(sbLocationDao.selectLocIdP(sbPropertyDo));
		sbPropertyDo.setStatusCd(PropertyStatus.REGISTERED.getCode());
		System.out.println("PropertiesServiceImpl add PropertyStatus.REGISTERED.getCode() : " + PropertyStatus.REGISTERED.getCode());
		return sbPropertyDao.add( sbPropertyDo );
	}
	
	@Override
	public List<SbPhotoDO> getPhotos(long propId, PhotoType type) {
		return sbPhotoDao.getList(propId, type.getCode(), 1, Integer.MAX_VALUE);
	}

	// 계약완료 업데이트
    @Override
    public int complete(long propId) {
        int result = sbPropertyDao.complete(propId);
        return result;
    }

	// 매물 수정
	@Override
	public int modifyProp(SbPropertyDO sbPropertyDo) {
		int result = 0;

		result = sbPropertyDao.update(sbPropertyDo);
		
		return result;
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

		for (SbConsultPropertyDO tem : searchlist) {
			totalnum += 1;
		}

		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		conprodo.setStartRow((conproSearch.getCurrentPage() - 1) * conproSearch.getRowCntPerPage() + 1);
		
		
		int endrow = conproSearch.getCurrentPage() * conproSearch.getRowCntPerPage();
		if(totalnum<endrow)conprodo.setEndRow(totalnum);
		else conprodo.setEndRow(endrow);
		System.out.println("service conprodo : " + conprodo);
		
		List<SbConsultPropertyDO> printlist = conproDao.conproIdList(conprodo);

		result.put("printlist", printlist);

		return result;
	}

	public SbConsultPropertyDO getaCon(long id) {
		return conproDao.getData(id);
	}

	public HashMap<String, Object> listByLegalDistrictCd(SbConsultPropertyDO conproSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		SbConsultPropertyDO conprodo = null;
		conprodo = conproSearch;
		conproSearch.setStartRow(1);
		conproSearch.setEndRow(500000);

		System.out.println("propertiesServiceImpl listByLegalDistrictCd conproSearch : " + conproSearch);

		List<SbConsultPropertyDO> searchlist = conproDao.listByLegalDistrictCd(conproSearch);
		System.out.println("service getconlist searchlist : " + searchlist);
		int totalnum = 0;
		if (searchlist != null) {
			for (SbConsultPropertyDO tem : searchlist) {
				totalnum += 1;

			}
		}

		result.put("totalnum", totalnum);
		result.put("searchlist", searchlist);

		conprodo.setStartRow((conproSearch.getCurrentPage() - 1) * conproSearch.getRowCntPerPage() + 1);
		
		
		int endrow = conproSearch.getCurrentPage() * conproSearch.getRowCntPerPage();
		if(totalnum<endrow)conprodo.setEndRow(totalnum);
		else conprodo.setEndRow(endrow);
		System.out.println("service conprodo : " + conprodo);
		
		List<SbConsultPropertyDO> printlist = conproDao.listByLegalDistrictCd(conprodo);
		if (printlist != null) {
			for (SbConsultPropertyDO tem : printlist) {
				String address = sbLocationDao.getAddress(tem.getZipcode());
				tem.setAddress(address);
			}
		}
		result.put("printlist", printlist);

		return result;
	}

	public int askforreply(SbConsultPropertyDO conOD) {
		long id = conproDao.getNextSeq();
		conOD.setCpId(id);
		conOD.setThrCpId(conOD.getpCpId());
		int result = conproDao.add(conOD);

		return result;
	}

	public List<SbLocationInfoDO> getLocationCity(String state) {
		List<SbLocationInfoDO> slido = sbLocationDao.getlocationcity(state);
		return slido;
	}

	public List<SbLocationInfoDO> getLocationDanji(String city) {
		List<SbLocationInfoDO> slido = sbLocationDao.getlocationdanji(city);
		return slido;
	}
	


	public int removeHmVO() {
		int result = 0;

		hmvo.set_AUTH_INFO_RAPMS(null);
		hmvo.set_SEARCH_CONDITION_RAPMS(null);

		return result;
	}
	
	
	public List<SbLocationInfoDO> getBldgNm(String dong) {
		System.out.println("getBldgNm dong: " + dong);
		String[] address = dong.split(" ");
		List<SbLocationInfoDO> sbs = null;
		long legalDistrictCd = sbLocationDao.selectLegalDistrictCd(address);
		System.out.println("getBldgNm legalDistrictCd: " + legalDistrictCd);
		
		sbs = sbLocationDao.getBldgNm(legalDistrictCd);
		System.out.println("getBldgNm sbs: " + sbs);
		return sbs;

	}
	
	
	
	public HashMap<String, String> addtoPropsId(String consultid, String propId) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		if (hmvo.get_ASK_PROP_PROPS_IDS_RAPMS() == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(propId).append(" ");
			result.put(consultid, sb.toString());

			System.out.println("service addtoPropsId if consultid, sb.toString() : " + consultid+" "+ sb.toString());
			hmvo.set_ASK_PROP_PROPS_IDS_RAPMS(result); 
		} else {
			
			String temp = hmvo.get_ASK_PROP_PROPS_IDS_RAPMS().get(consultid);
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
			result.put(consultid, sb.toString());
			hmvo.set_ASK_PROP_PROPS_IDS_RAPMS(result);
		}
//		result.put(Long.toString(propids[0]), null);
//		hmvo.setPropsIds(result);
		return result;
	}
	
	public HashMap<String, String> deleteFromPropsId(String consultid, String propId) {
			HashMap<String, String> result = new HashMap<String, String>();
		
		if (hmvo.get_ASK_PROP_PROPS_IDS_RAPMS() == null) {
			
		} else {
			if( !propId.equals("0") ) {
				String temp = hmvo.get_ASK_PROP_PROPS_IDS_RAPMS().get(consultid);
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
				
				result.put(consultid, sb.toString());
				hmvo.set_ASK_PROP_PROPS_IDS_RAPMS(result);
			} else {
				result.put(consultid, null);
				hmvo.set_ASK_PROP_PROPS_IDS_RAPMS(result);
			}
		}
//		result.put(Long.toString(propids[0]), null);
//		hmvo.setPropsIds(result);
		return result;
	}
	
	public PropertyDetailVO getPropLocVO(long propId) {
		return sbPropertyDao.getPropLocVO(propId);
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
	
	public long[] addressReturnsUserIds(SbLocationInfoDO addr){
		long[] result = null;
		
		String locIdstr = sbLocationDao.selectLocId(addr);
		System.out.println("service locIdstr : "+ locIdstr);
		long locCd = Long.parseLong(locIdstr.substring(27));
		System.out.println("service locId : " + locCd);
		result = favProDao.addressReturnsUserIds(locCd);
		System.out.println("service addressReturnsUserIds result : "+ Arrays.toString(result));
		return result;
	}
	
	public SbDataObject getStateCityLegalDistrictNm(String addr) {
		SbDataObject result = new SbDataObject();
		result = sbLocationDao.getStateCityLegalDistrictNm(addr);
		return result;
	}
}
