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
public class SbUserDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private String addedAt;
	private long addedBy;
	private String email;
	private String name;
	private String passwd;
	private String updatedAt;
	private long updatedBy;
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Override
	public String toString() {
		return "SbUserDO ["+super.toString()+"totalCount=" + totalCount + ", addedAt=" + addedAt + ", addedBy=" + addedBy + ", email="
				+ email + ", name=" + name + ", passwd=" + passwd + ", updatedAt=" + updatedAt + ", updatedBy="
				+ updatedBy + "]";
	}
	
		
}