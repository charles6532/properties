/*
 * Copyright (c) 2019, Playdata. All rights reserved.
 * Playdata PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package kr.starbocks.api.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * @author Playdata
 *
 */
@Component
public class SbFavoriteMemberDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private String addedAt;
	private long memId;
	private String updatedAt;
	

	/**
	 * @param addedAt the addedAt to set
	 */
	public void setAddedAt(final String addedAt) {
		this.addedAt=addedAt;
	}

	/**
	 * @return the addedAt
	 */
	public String getAddedAt() {
		return this.addedAt;
	}

	/**
	 * @param memId the memId to set
	 */
	public void setMemId(final long memId) {
		this.memId=memId;
	}

	/**
	 * @return the memId
	 */
	public long getMemId() {
		return this.memId;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(final String updatedAt) {
		this.updatedAt=updatedAt;
	}

	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return this.updatedAt;
	}

	
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalCount() {
		return totalCount;
	}

	@Override
	public String toString() {
		return "SbFavoriteMemberDO ["+super.toString()+"totalCount=" + totalCount + ", addedAt=" + addedAt + ", memId=" + memId
				+ ", updatedAt=" + updatedAt + "]";
	}

	
	
}