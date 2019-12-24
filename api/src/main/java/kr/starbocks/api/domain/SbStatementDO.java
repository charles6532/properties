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
public class SbStatementDO extends SbDataObject {
	private static final long serialVersionUID = 1L;
	private long totalCount;
	private long statement_id;
	private String txCd;
	private long amount;
	private long paymentId;
	private long askId;
	private String addedAt;
	private String updatedAt;
	private long balance;
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getStatement_id() {
		return statement_id;
	}
	public void setStatement_id(long statement_id) {
		this.statement_id = statement_id;
	}
	public String getTxCd() {
		return txCd;
	}
	public void setTxCd(String txCd) {
		this.txCd = txCd;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public long getAskId() {
		return askId;
	}
	public void setAskId(long askId) {
		this.askId = askId;
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
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "SbStatementDO ["+super.toString()+"totalCount=" + totalCount + ", statement_id=" + statement_id + ", txCd=" + txCd
				+ ", amount=" + amount + ", paymentId=" + paymentId + ", askId=" + askId + ", addedAt=" + addedAt
				+ ", updatedAt=" + updatedAt + ", balance=" + balance + "]";
	}
		
}