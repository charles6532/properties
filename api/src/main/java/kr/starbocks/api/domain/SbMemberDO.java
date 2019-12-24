/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.domain;


import java.util.HashMap;

import org.springframework.stereotype.Component;

/**
 * @author Playdata
 *
 */
@Component
public class SbMemberDO extends SbUserDO {
	private static final long serialVersionUID = 1L;
	private String address;
	private String regOn;
	private String tel;
	private String zipcode;
	private String authStr;
	private String randomStr;
	

	public String getRandomStr() {
		return randomStr;
	}

	public void setRandomStr(String randomStr) {
		this.randomStr = randomStr;
	}

	public String getAuthStr() {
		return authStr;
	}

	public void setAuthStr(String authStr) {
		this.authStr = authStr;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(final String address) {
		this.address=address;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.address;
	}


	/**
	 * @param regOn the regOn to set
	 */
	public void setRegOn(final String regOn) {
		this.regOn=regOn;
	}

	/**
	 * @return the regOn
	 */
	public String getRegOn() {
		return this.regOn;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(final String tel) {
		this.tel=tel;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return this.tel;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(final String zipcode) {
		this.zipcode=zipcode;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return this.zipcode;
	}

	@Override
	public String toString() {
		return "SbMemberDO ["+super.toString()+"address=" + address + ", regOn=" + regOn + ", tel=" + tel + ", zipcode=" + zipcode
				+ ", authStr=" + authStr + ", randomStr=" + randomStr + "]";
	}

	

}