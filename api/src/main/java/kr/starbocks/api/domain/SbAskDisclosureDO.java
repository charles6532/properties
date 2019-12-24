/*
s * Copyright (c) 2019, Playdata. All rights reserved.
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
public class SbAskDisclosureDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long askId;
	private long totalCount;
	private String addedAt;
	private long addedBy;
	private String propId;
	private String publicCd;
	private String updatedAt;
	private long userId;
	private long pAskId;
	private long thrAskId;
	private long[] longProps;
	private String propsId;
	private String name;
	private long priorAddedBy;
	
	

	public long getPriorAddedBy() {
		return priorAddedBy;
	}

	public void setPriorAddedBy(long priorAddedBy) {
		this.priorAddedBy = priorAddedBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getAskId() {
		return askId;
	}

	public void setAskId(long askId) {
		this.askId = askId;
	}



	public long getpAskId() {
		return pAskId;
	}

	public void setpAskId(long pAskId) {
		this.pAskId = pAskId;
	}

	public long getThrAskId() {
		return thrAskId;
	}

	public void setThrAskId(long thrAskId) {
		this.thrAskId = thrAskId;
	}

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
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(final long addedBy) {
		this.addedBy=addedBy;
	}

	/**
	 * @return the addedBy
	 */
	public long getAddedBy() {
		return this.addedBy;
	}


	/**
	 * @param propId the propId to set
	 */
	public void setPropId(final String propId) {
		this.propId=propId;
	}

	/**
	 * @return the propId
	 */
	public String getPropId() {
		return this.propId;
	}

	/**
	 * @param publicCd the publicCd to set
	 */
	public void setPublicCd(final String publicCd) {
		this.publicCd=publicCd;
	}

	/**
	 * @return the publicCd
	 */
	public String getPublicCd() {
		return this.publicCd;
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

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(final long userId) {
		this.userId=userId;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return this.userId;
	}
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}
	public long getTotalCount() {
		return totalCount;
	}

	@Override
	public String toString() {
		return "SbAskDisclosureDO ["+super.toString()+"askId=" + askId + ", totalCount=" + totalCount + ", addedAt=" + addedAt
				+ ", addedBy=" + addedBy + ", propId=" + propId + ", publicCd=" + publicCd + ", updatedAt=" + updatedAt
				+ ", userId=" + userId + ", pAskId=" + pAskId + ", thrAskId=" + thrAskId + ", longProps="
				+ Arrays.toString(longProps) + ", propsId=" + propsId + ", name=" + name + "]";
	}
	
	
	

	
	
}