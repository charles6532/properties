/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.domain;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

/**
 * @author Playdata
 *
 */
@Component
public class SbAgentDO extends SbUserDO {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long zipcodeId;
	private String tel;	
	private String address;
	private String regOn;
	private String faxNumber;
	private long deposit;
	
	
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public long getZipcodeId() {
		return zipcodeId;
	}
	public void setZipcodeId(long zipcodeId) {
		this.zipcodeId = zipcodeId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegOn() {
		return regOn;
	}
	public void setRegOn(String regOn) {
		this.regOn = regOn;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public long getDeposit() {
		return deposit;
	}
	public void setDeposit(long deposit) {
		this.deposit = deposit;
	}
	@Override
	public String toString() {
		return "SbAgentDO [totalCount=" + totalCount + ", zipcodeId=" + zipcodeId + ", tel=" + tel + ", address="
				+ address + ", regOn=" + regOn + ", faxNumber=" + faxNumber + ", deposit=" + deposit + "]";
	}
	
}