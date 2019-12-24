/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.domain;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Playdata
 *
 */
@Component
public class SbPropertyDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long propId;
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
	private float areaSpace;
	private String entrance;
	private String aspect;
	private long bathroomNo;
	private String statusCd;
	private long numApartment;
	private String realRootPath;
	private boolean checkBox;
	private float prediction;
	private String floorDesc;
	
	
	
	
	public String getFloorDesc() {
		return floorDesc;
	}
	public void setFloorDesc(String floorDesc) {
		this.floorDesc = floorDesc;
	}
	public float getPrediction() {
		return prediction;
	}
	public void setPrediction(float prediction) {
		this.prediction = prediction;
	}
	public long getPropId() {
		return propId;
	}
	public void setPropId(long propId) {
		this.propId = propId;
	}
	public boolean isCheckBox() {
		return checkBox;
	}
	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}
	public String getRealRootPath() {
		return realRootPath;
	}
	public void setRealRootPath(String realRootPath) {
		this.realRootPath = realRootPath;
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
	public float getAreaSpace() {
		return areaSpace;
	}
	public void setAreaSpace(float areaSpace) {
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
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
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
	@Override
	public String toString() {
		return "SbPropertyDO ["+super.toString()+"totalCount=" + totalCount + ", propId=" + propId + ", address=" + address + ", title="
				+ title + ", aptName=" + aptName + ", price=" + price + ", moveAt=" + moveAt + ", features=" + features
				+ ", dong=" + dong + ", hoNo=" + hoNo + ", addedAt=" + addedAt + ", updatedAt=" + updatedAt
				+ ", photoName=" + photoName + ", photoRight=" + photoRight + ", photoNames="
				+ Arrays.toString(photoNames) + ", photoRightImages=" + Arrays.toString(photoRightImages) + ", floor="
				+ floor + ", room=" + room + ", repaired=" + repaired + ", builtYear=" + builtYear + ", parking="
				+ parking + ", areaSpace=" + areaSpace + ", entrance=" + entrance + ", aspect=" + aspect
				+ ", bathroomNo=" + bathroomNo + ", statusCd=" + statusCd + ", numApartment=" + numApartment
				+ ", realRootPath=" + realRootPath + ", checkBox=" + checkBox + "]";
	}
	
	
	
	
	
	
}