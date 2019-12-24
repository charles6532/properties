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
public class SbPaymentDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long payment_id;
	private long amount;
	private String payedAt;
	private String statusCd;
	private String addedAt;
	private String updatedAt;
	
	
	public long getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(long payment_id) {
		this.payment_id = payment_id;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getPayedAt() {
		return payedAt;
	}
	public void setPayedAt(String payedAt) {
		this.payedAt = payedAt;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
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
		return "SbPaymentDO ["+super.toString()+"totalCount=" + totalCount + ", payment_id=" + payment_id + ", amount=" + amount
				+ ", payedAt=" + payedAt + ", statusCd=" + statusCd + ", addedAt=" + addedAt + ", updatedAt="
				+ updatedAt + "]";
	}
	
	
}