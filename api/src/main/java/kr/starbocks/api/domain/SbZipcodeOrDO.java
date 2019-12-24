/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * @author Playdata
 *
 */
@Component
public class SbZipcodeOrDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private int zipcode;
	private String stateEn;
	private String cityEn;
	private String district;
	private String districtEn;
	private long streetCd;
	private String streetNm;
	private String streeNmEn;
	private String basement;
	private short bldgMainNo;
	private short bldgSubNo;
	private BigDecimal bldgMgtNo;
	private String complexNm;
	private String bldgNameCity;
	private long legalDistrictCd;
	private String areaNm;
	private String admDistrictNm;
	private String mountain;
	private short areaMainNo;
	private short districtSeq;
	private String areaSubNo;
	private int oldZipcode;
	private int zipcodeSeq;
	/**
	 * @return the zipcode
	 */
	public int getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return the state
	 */
	
	/**
	 * @return the stateEn
	 */
	public String getStateEn() {
		return stateEn;
	}
	/**
	 * @param stateEn the stateEn to set
	 */
	public void setStateEn(String stateEn) {
		this.stateEn = stateEn;
	}
	/**
	 * @return the city
	 */
	
	/**
	 * @return the cityEn
	 */
	public String getCityEn() {
		return cityEn;
	}
	/**
	 * @param cityEn the cityEn to set
	 */
	public void setCityEn(String cityEn) {
		this.cityEn = cityEn;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the districtEn
	 */
	public String getDistrictEn() {
		return districtEn;
	}
	/**
	 * @param districtEn the districtEn to set
	 */
	public void setDistrictEn(String districtEn) {
		this.districtEn = districtEn;
	}
	/**
	 * @return the streetCd
	 */
	public long getStreetCd() {
		return streetCd;
	}
	/**
	 * @param streetCd the streetCd to set
	 */
	public void setStreetCd(long streetCd) {
		this.streetCd = streetCd;
	}
	/**
	 * @return the streetNm
	 */
	public String getStreetNm() {
		return streetNm;
	}
	/**
	 * @param streetNm the streetNm to set
	 */
	public void setStreetNm(String streetNm) {
		this.streetNm = streetNm;
	}
	/**
	 * @return the streeNmEn
	 */
	public String getStreeNmEn() {
		return streeNmEn;
	}
	/**
	 * @param streeNmEn the streeNmEn to set
	 */
	public void setStreeNmEn(String streeNmEn) {
		this.streeNmEn = streeNmEn;
	}
	/**
	 * @return the basement
	 */
	public String getBasement() {
		return basement;
	}
	/**
	 * @param basement the basement to set
	 */
	public void setBasement(String basement) {
		this.basement = basement;
	}
	/**
	 * @return the bldgMainNo
	 */
	public short getBldgMainNo() {
		return bldgMainNo;
	}
	/**
	 * @param bldgMainNo the bldgMainNo to set
	 */
	public void setBldgMainNo(short bldgMainNo) {
		this.bldgMainNo = bldgMainNo;
	}
	/**
	 * @return the bldgSubNo
	 */
	public short getBldgSubNo() {
		return bldgSubNo;
	}
	/**
	 * @param bldgSubNo the bldgSubNo to set
	 */
	public void setBldgSubNo(short bldgSubNo) {
		this.bldgSubNo = bldgSubNo;
	}
	/**
	 * @return the bldgMgtNo
	 */
	public BigDecimal getBldgMgtNo() {
		return bldgMgtNo;
	}
	/**
	 * @param bldgMgtNo the bldgMgtNo to set
	 */
	public void setBldgMgtNo(BigDecimal bldgMgtNo) {
		this.bldgMgtNo = bldgMgtNo;
	}
	/**
	 * @return the complexNm
	 */
	public String getComplexNm() {
		return complexNm;
	}
	/**
	 * @param complexNm the complexNm to set
	 */
	public void setComplexNm(String complexNm) {
		this.complexNm = complexNm;
	}
	/**
	 * @return the bldgNameCity
	 */
	public String getBldgNameCity() {
		return bldgNameCity;
	}
	/**
	 * @param bldgNameCity the bldgNameCity to set
	 */
	public void setBldgNameCity(String bldgNameCity) {
		this.bldgNameCity = bldgNameCity;
	}
	/**
	 * @return the legalDistrictCd
	 */
	public long getLegalDistrictCd() {
		return legalDistrictCd;
	}
	/**
	 * @param legalDistrictCd the legalDistrictCd to set
	 */
	public void setLegalDistrictCd(long legalDistrictCd) {
		this.legalDistrictCd = legalDistrictCd;
	}
	/**
	 * @return the legalDistrictNm
	 */
	
	/**
	 * @return the areaNm
	 */
	public String getAreaNm() {
		return areaNm;
	}
	/**
	 * @param areaNm the areaNm to set
	 */
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}
	/**
	 * @return the admDistrictNm
	 */
	public String getAdmDistrictNm() {
		return admDistrictNm;
	}
	/**
	 * @param admDistrictNm the admDistrictNm to set
	 */
	public void setAdmDistrictNm(String admDistrictNm) {
		this.admDistrictNm = admDistrictNm;
	}
	/**
	 * @return the mountain
	 */
	public String getMountain() {
		return mountain;
	}
	/**
	 * @param mountain the mountain to set
	 */
	public void setMountain(String mountain) {
		this.mountain = mountain;
	}
	/**
	 * @return the areaMainNo
	 */
	public short getAreaMainNo() {
		return areaMainNo;
	}
	/**
	 * @param areaMainNo the areaMainNo to set
	 */
	public void setAreaMainNo(short areaMainNo) {
		this.areaMainNo = areaMainNo;
	}
	/**
	 * @return the districtSeq
	 */
	public short getDistrictSeq() {
		return districtSeq;
	}
	/**
	 * @param districtSeq the districtSeq to set
	 */
	public void setDistrictSeq(short districtSeq) {
		this.districtSeq = districtSeq;
	}
	/**
	 * @return the areaSubNo
	 */
	public String getAreaSubNo() {
		return areaSubNo;
	}
	/**
	 * @param areaSubNo the areaSubNo to set
	 */
	public void setAreaSubNo(String areaSubNo) {
		this.areaSubNo = areaSubNo;
	}
	/**
	 * @return the oldZipcode
	 */
	public int getOldZipcode() {
		return oldZipcode;
	}
	/**
	 * @param oldZipcode the oldZipcode to set
	 */
	public void setOldZipcode(int oldZipcode) {
		this.oldZipcode = oldZipcode;
	}
	/**
	 * @return the zipcodeSeq
	 */
	public int getZipcodeSeq() {
		return zipcodeSeq;
	}
	/**
	 * @param zipcodeSeq the zipcodeSeq to set
	 */
	public void setZipcodeSeq(int zipcodeSeq) {
		this.zipcodeSeq = zipcodeSeq;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SbZipcodeOrDO ["+super.toString()+"zipcode=" + zipcode + ", stateEn=" + stateEn + ", cityEn=" + cityEn + ", district="
				+ district + ", districtEn=" + districtEn + ", streetCd=" + streetCd + ", streetNm=" + streetNm
				+ ", streeNmEn=" + streeNmEn + ", basement=" + basement + ", bldgMainNo=" + bldgMainNo + ", bldgSubNo="
				+ bldgSubNo + ", bldgMgtNo=" + bldgMgtNo + ", complexNm=" + complexNm + ", bldgNameCity=" + bldgNameCity
				+ ", legalDistrictCd=" + legalDistrictCd + ", areaNm=" + areaNm + ", admDistrictNm=" + admDistrictNm
				+ ", mountain=" + mountain + ", areaMainNo=" + areaMainNo + ", districtSeq=" + districtSeq
				+ ", areaSubNo=" + areaSubNo + ", oldZipcode=" + oldZipcode + ", zipcodeSeq=" + zipcodeSeq + "]";
	}
	
}