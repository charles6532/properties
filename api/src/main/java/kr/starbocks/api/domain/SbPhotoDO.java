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
public class SbPhotoDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long photoId;
	private String addedAt;
	private String photoName;
	private long propId;
	private String typeCd;
	private String updatedAt;
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}
	public String getAddedAt() {
		return addedAt;
	}
	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public long getPropId() {
		return propId;
	}
	public void setPropId(long propId) {
		this.propId = propId;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "SbPhotoDO ["+super.toString()+"totalCount=" + totalCount + ", photoId=" + photoId + ", addedAt=" + addedAt + ", photoName="
				+ photoName + ", propId=" + propId + ", typeCd=" + typeCd + ", updatedAt=" + updatedAt + "]";
	}
	
}