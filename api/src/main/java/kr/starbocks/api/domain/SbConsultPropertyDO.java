/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.domain;

import java.util.Arrays;

import org.springframework.stereotype.Component;

/**
 * @author Playdata
 *
 */
@Component
public class SbConsultPropertyDO extends SbDataObject {  // implements Serializable 으로 바꿈
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long cpId;
	private long userId;
	private String conTitle;
	private String remark;
	private long zipcode;
	private long floor;
	private float space;
	private String maintenance;
	private int roomCount;
	private long price;
	private String moveAt;
	private String builtAt;
	private float avgParkingSpace;
	private long housesCount;
	private String aptTitle; //단지명 추가
	private String addedAt;
	private String updatedAt;
	private String address;
	private long pCpId;
	private long thrCpId;
	private long[] longProps;
	private String propsId;
	private String floorDesc;
	
	
	
	
	
	
	
	public String getFloorDesc() {
		return floorDesc;
	}
	public void setFloorDesc(String floorDesc) {
		this.floorDesc = floorDesc;
	}
	public long getFloor() {
		return floor;
	}
	public void setFloor(long floor) {
		this.floor = floor;
	}
	public long getpCpId() {
		return pCpId;
	}
	public void setpCpId(long pCpId) {
		this.pCpId = pCpId;
	}
	public long[] getLongProps() {
		return longProps;
	}
	public void setLongProps(long[] longProps) {
		this.longProps = longProps;
	}
	public String getPropsId() {
		return propsId;
	}
	public void setPropsId(String propsId) {
		this.propsId = propsId;
	}
	public long getCpId() {
		return cpId;
	}
	public void setCpId(long cpId) {
		this.cpId = cpId;
	}
	
	public long getThrCpId() {
		return thrCpId;
	}
	public void setThrCpId(long thrCpId) {
		this.thrCpId = thrCpId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getConTitle() {
		return conTitle;
	}
	public void setConTitle(String conTitle) {
		this.conTitle = conTitle;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getZipcode() {
		return zipcode;
	}
	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}
	
	public float getSpace() {
		return space;
	}
	public void setSpace(float space) {
		this.space = space;
	}
	public String getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	public int getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
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
	public String getBuiltAt() {
		return builtAt;
	}
	public void setBuiltAt(String builtAt) {
		this.builtAt = builtAt;
	}
	public float getAvgParkingSpace() {
		return avgParkingSpace;
	}
	public void setAvgParkingSpace(float avgParkingSpace) {
		this.avgParkingSpace = avgParkingSpace;
	}
	public long getHousesCount() {
		return housesCount;
	}
	public void setHousesCount(long housesCount) {
		this.housesCount = housesCount;
	}
	public String getAptTitle() {
		return aptTitle;
	}
	public void setAptTitle(String aptTitle) {
		this.aptTitle = aptTitle;
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
	
	
	
	
	
	
	
	
	
	
	
			
}