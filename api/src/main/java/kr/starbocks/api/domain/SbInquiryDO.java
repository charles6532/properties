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
public class SbInquiryDO extends SbDataObject{ 
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long id;
	private long pId;
	private long thrId;
	private long refId;
	private long addedBy;
	private String typeCd;
	private String subject;
	private String content;
	private String addedAt;
	private String updatedAt;
	private String email;
	private String tel;
	private long propId;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPropId() {
		return propId;
	}
	public void setPropId(long propId) {
		this.propId = propId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public long getThrId() {
		return thrId;
	}
	public void setThrId(long thrId) {
		this.thrId = thrId;
	}
	public String getAddedAt() {
		return addedAt;
	}
	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}
	public long getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(long addedBy) {
		this.addedBy = addedBy;
	}
	public String getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(String typeCd) {
		this.typeCd = typeCd;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "SbInquiryDO ["+super.toString()+"totalCount=" + totalCount + ", id=" + id + ", pId=" + pId + ", thrId=" + thrId + ", refId="
				+ refId + ", addedBy=" + addedBy + ", typeCd=" + typeCd + ", subject=" + subject + ", content="
				+ content + ", addedAt=" + addedAt + ", updatedAt=" + updatedAt + ", email=" + email + ", tel=" + tel
				+ ", propId=" + propId + "]";
	}
	
	
	
	
	
		
}