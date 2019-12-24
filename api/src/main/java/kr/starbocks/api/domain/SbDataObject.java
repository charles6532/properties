package kr.starbocks.api.domain;

public class SbDataObject {
	
	private long userId;
	private String state;
	private String city;
	private String legalDistrictNm;
	private String bldgNm;
	private int startRow;
	private int endRow;
	private int currentPage;
	private int rowCntPerPage;
	private int ridx;
	private String nickname;
	private long priceMin;
	private long priceMax;
	private String priceRange;
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public long getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(long priceMin) {
		this.priceMin = priceMin;
	}

	public long getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(long priceMax) {
		this.priceMax = priceMax;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLegalDistrictNm() {
		return legalDistrictNm;
	}

	public void setLegalDistrictNm(String legalDistrictNm) {
		this.legalDistrictNm = legalDistrictNm;
	}

	public String getBldgNm() {
		return bldgNm;
	}

	public void setBldgNm(String bldgNm) {
		this.bldgNm = bldgNm;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowCntPerPage() {
		return rowCntPerPage;
	}

	public void setRowCntPerPage(int rowCntPerPage) {
		this.rowCntPerPage = rowCntPerPage;
	}

	public int getRidx() {
		return ridx;
	}

	public void setRidx(int ridx) {
		this.ridx = ridx;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	@Override
	public String toString() {
		return "SbDataObject [userId=" + userId + ", state=" + state + ", city=" + city
				+ ", legalDistrictNm=" + legalDistrictNm + ", bldgNm=" + bldgNm + ", startRow=" + startRow + ", endRow="
				+ endRow + ", currentPage=" + currentPage + ", rowCntPerPage=" + rowCntPerPage + ", ridx=" + ridx
				+ ", nickname=" + nickname + ", priceMin=" + priceMin + ", priceMax=" + priceMax + ", priceRange="
				+ priceRange + "]";
	}
	
	
}
