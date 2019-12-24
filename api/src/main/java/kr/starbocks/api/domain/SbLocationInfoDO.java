package kr.starbocks.api.domain;

import org.springframework.stereotype.Component;
/**
 * @author Playdata
 *
 */
@Component
public class SbLocationInfoDO extends SbDataObject{
	private static final long serialVersionUID = 1L;
	private short cityCountryCd;
	private long entranceSeq;
	private long legalDistrictCd;
	private long streetCd;
	private String streetNm;
	private String basement;
	private short bldgMainNo;
	private short bldgSubNo;
	private short zipcode;
	private String bldgUsage;
	private String complex;
	private String admDistrictNm;
	private long longitude;
	private long latitude;
	private String aptTitle;
	private int space;
	private int builtAt;
	private int housesCount;
	private int roomCount;
	private String floorDesc;
	
	
	
	
	
	
	public String getFloorDesc() {
		return floorDesc;
	}
	public void setFloorDesc(String floorDesc) {
		this.floorDesc = floorDesc;
	}
	public String getAptTitle() {
		return aptTitle;
	}
	public void setAptTitle(String aptTitle) {
		this.aptTitle = aptTitle;
	}
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	public int getBuiltAt() {
		return builtAt;
	}
	public void setBuiltAt(int builtAt) {
		this.builtAt = builtAt;
	}
	public int getHousesCount() {
		return housesCount;
	}
	public void setHousesCount(int housesCount) {
		this.housesCount = housesCount;
	}
	public int getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}
		
	public short getCityCountryCd() {
		return cityCountryCd;
	}
	public void setCityCountryCd(short cityCountryCd) {
		this.cityCountryCd = cityCountryCd;
	}
	public long getEntranceSeq() {
		return entranceSeq;
	}
	public void setEntranceSeq(long entranceSeq) {
		this.entranceSeq = entranceSeq;
	}
	public long getLegalDistrictCd() {
		return legalDistrictCd;
	}
	public void setLegalDistrictCd(long legalDistrictCd) {
		this.legalDistrictCd = legalDistrictCd;
	}
	
	public long getStreetCd() {
		return streetCd;
	}
	public void setStreetCd(long streetCd) {
		this.streetCd = streetCd;
	}
	public String getStreetNm() {
		return streetNm;
	}
	public void setStreetNm(String streetNm) {
		this.streetNm = streetNm;
	}
	public String getBasement() {
		return basement;
	}
	public void setBasement(String basement) {
		this.basement = basement;
	}
	public short getBldgMainNo() {
		return bldgMainNo;
	}
	public void setBldgMainNo(short bldgMainNo) {
		this.bldgMainNo = bldgMainNo;
	}
	public short getBldgSubNo() {
		return bldgSubNo;
	}
	public void setBldgSubNo(short bldgSubNo) {
		this.bldgSubNo = bldgSubNo;
	}
	
	public short getZipcode() {
		return zipcode;
	}
	public void setZipcode(short zipcode) {
		this.zipcode = zipcode;
	}
	public String getBldgUsage() {
		return bldgUsage;
	}
	public void setBldgUsage(String bldgUsage) {
		this.bldgUsage = bldgUsage;
	}
	public String getComplex() {
		return complex;
	}
	public void setComplex(String complex) {
		this.complex = complex;
	}
	public String getAdmDistrictNm() {
		return admDistrictNm;
	}
	public void setAdmDistrictNm(String admDistrictNm) {
		this.admDistrictNm = admDistrictNm;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "SbLocationInfoDO ["+super.toString()+"cityCountryCd=" + cityCountryCd + ", entranceSeq=" + entranceSeq
				+ ", legalDistrictCd=" + legalDistrictCd + ", streetCd=" + streetCd + ", streetNm=" + streetNm
				+ ", basement=" + basement + ", bldgMainNo=" + bldgMainNo + ", bldgSubNo=" + bldgSubNo + ", zipcode="
				+ zipcode + ", bldgUsage=" + bldgUsage + ", complex=" + complex + ", admDistrictNm=" + admDistrictNm
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", aptTitle=" + aptTitle + ", space=" + space
				+ ", builtAt=" + builtAt + ", housesCount=" + housesCount + ", roomCount=" + roomCount + ", floorDesc="
				+ floorDesc + "]";
	}
	
	
	
		
}
