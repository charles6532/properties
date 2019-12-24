package kr.starbocks.api.value;

import java.util.Arrays;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PropertyDetailVO {
	private long totalCount;
	private long id;
	private long propId;
	private long userId;
	private String address; // 주소
	private String title;
	private String aptName; // 단지명
	private long price;
	private String moveAt; // 입주가능일
	private String features; // 매물 특징
	private long dong; // 아파트 동
	private long hoNo; // 아파트 호수
	private String addedAt;
	private String updatedAt;
	private String photoName;
	private String photoRight; // 등기부등본
	private CommonsMultipartFile[] photoNames;
	private CommonsMultipartFile[] photoRightImages;
	private long floor; // 층수
	private String room; // 방개수
	private String repaired;
	private long builtYear;
	private long parking;
	private long areaSpace;
	private String entrance;
	private String aspect;
	private long bathroomNo;
	private String statusCd;
	private long numApartment;
	private String realRootPath;
	private short cityCountryCd;
	private long entranceSeq;
	private long legalDistrictCd;
	private String state;
	private String city;
	private String legalDistrictNm;
	private long streetCd;
	private String streetNm;
	private String basement;
	private short bldgMainNo;
	private short bldgSubNo;
	private String bldgNm;
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
	private int startRow;
	private int endRow;
	private int currentPage;
	private int rowCntPerPage;
	private int ridx;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getPropId() {
		return propId;
	}
	public void setPropId(long propId) {
		this.propId = propId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getMoveAt() {
		return moveAt;
	}
	public void setMoveAt(String moveAt) {
		this.moveAt = moveAt;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public long getDong() {
		return dong;
	}
	public void setDong(long dong) {
		this.dong = dong;
	}
	public long getHoNo() {
		return hoNo;
	}
	public void setHoNo(long hoNo) {
		this.hoNo = hoNo;
	}
	public String getAddedAt() {
		return addedAt;
	}
	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoRight() {
		return photoRight;
	}
	public void setPhotoRight(String photoRight) {
		this.photoRight = photoRight;
	}
	public CommonsMultipartFile[] getPhotoNames() {
		return photoNames;
	}
	public void setPhotoNames(CommonsMultipartFile[] photoNames) {
		this.photoNames = photoNames;
	}
	public CommonsMultipartFile[] getPhotoRightImages() {
		return photoRightImages;
	}
	public void setPhotoRightImages(CommonsMultipartFile[] photoRightImages) {
		this.photoRightImages = photoRightImages;
	}
	public long getFloor() {
		return floor;
	}
	public void setFloor(long floor) {
		this.floor = floor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getRepaired() {
		return repaired;
	}
	public void setRepaired(String repaired) {
		this.repaired = repaired;
	}
	public long getBuiltYear() {
		return builtYear;
	}
	public void setBuiltYear(long builtYear) {
		this.builtYear = builtYear;
	}
	public long getParking() {
		return parking;
	}
	public void setParking(long parking) {
		this.parking = parking;
	}
	public long getAreaSpace() {
		return areaSpace;
	}
	public void setAreaSpace(long areaSpace) {
		this.areaSpace = areaSpace;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getAspect() {
		return aspect;
	}
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	public long getBathroomNo() {
		return bathroomNo;
	}
	public void setBathroomNo(long bathroomNo) {
		this.bathroomNo = bathroomNo;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public long getNumApartment() {
		return numApartment;
	}
	public void setNumApartment(long numApartment) {
		this.numApartment = numApartment;
	}
	public String getRealRootPath() {
		return realRootPath;
	}
	public void setRealRootPath(String realRootPath) {
		this.realRootPath = realRootPath;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLegalDistrictNm() {
		return legalDistrictNm;
	}
	public void setLegalDistrictNm(String legalDistrictNm) {
		this.legalDistrictNm = legalDistrictNm;
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
	public String getBldgNm() {
		return bldgNm;
	}
	public void setBldgNm(String bldgNm) {
		this.bldgNm = bldgNm;
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
	public String getFloorDesc() {
		return floorDesc;
	}
	public void setFloorDesc(String floorDesc) {
		this.floorDesc = floorDesc;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRowCntPerPage() {
		return rowCntPerPage;
	}
	public void setRowCntPerPage(int rowCntPerPage) {
		this.rowCntPerPage = rowCntPerPage;
	}
	public int getRidx() {
		return ridx;
	}
	public void setRidx(int ridx) {
		this.ridx = ridx;
	}
	
}
