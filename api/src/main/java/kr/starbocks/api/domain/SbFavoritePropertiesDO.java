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
public class SbFavoritePropertiesDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private String addedAt;
	private long propId;
	private String statusCd;
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
	 * @param propId the propId to set
	 */
	public void setPropId(final long propId) {
		this.propId=propId;
	}

	/**
	 * @return the propId
	 */
	public long getPropId() {
		return this.propId;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(final String statusCd) {
		this.statusCd=statusCd;
	}

	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return this.statusCd;
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
		return "SbFavoritePropertiesDO ["+super.toString()+"totalCount=" + totalCount + ", addedAt=" + addedAt + ", propId=" + propId
				+ ", statusCd=" + statusCd + ", updatedAt=" + updatedAt + "]";
	}

	
	
}