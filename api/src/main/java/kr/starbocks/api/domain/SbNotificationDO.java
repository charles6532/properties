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
public class SbNotificationDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long id;
	private String cd;
	private String subject;
	private String content;
	private long refId;
	private long addedBy;
	private String addedAt;
	private String updatedAt;
	
	
	
	
	
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
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getRefId() {
		return refId;
	}
	public void setRefId(long refId) {
		this.refId = refId;
	}
	public long getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(long addedBy) {
		this.addedBy = addedBy;
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
	@Override
	public String toString() {
		return "SbNotificationDO ["+super.toString()+"totalCount=" + totalCount + ", id=" + id + ", cd=" + cd + ", subject=" + subject
				+ ", content=" + content + ", refId=" + refId + ", addedBy=" + addedBy + ", addedAt=" + addedAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	

	
	
}