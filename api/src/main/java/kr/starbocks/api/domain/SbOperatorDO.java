/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.domain;


import org.springframework.stereotype.Component;

/**
 * @author Playdata
 *
 */
@Component
public class SbOperatorDO extends SbUserDO {
	private static final long serialVersionUID = 1L;
	private String tel;

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

	@Override
	public String toString() {
		return "SbOperatorDO ["+super.toString()+", tel=" + tel + "]";
	}

}