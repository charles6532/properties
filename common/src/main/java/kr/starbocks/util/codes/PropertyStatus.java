/**
 * 
 */
package kr.starbocks.util.codes;

/**
 * This defines codes for payment status 
 * 
 * @author Playdata
 * @since 1.8
 */
public enum PropertyStatus {
	REGISTERED("PR01", "등록됨", "Registered",""),
	REQUESTED("PR02", "등본 열람 요청함", "Requested","PR01"),
	INSPECTING("PR03", "등본 열람중", "Inspecting","PR02"),
	REJECTED("PR04", "등록 거부됨", "Rejected","PR04"),
	VERIFIED("PR00", "매물 확인됨", "Verified","PR03"),
	COMPLETED("PR11", "계약 체결됨", "Completed","PR00"),
	SUSPENDED("PR12", "게시 중단됨", "Suspended","PR00"),
	COMPLAINED("PR21", "신고됨", "Accused","PR00")	
	;
	
	private String code;
	private String name;
	private String alias;
	private String parent;
	private PropertyStatus(final String code, final String name, final String alias, final String parent) {
		this.code = code;
		this.name = name;
		this.alias = alias;
		this.parent = parent;		
	}
	/**
	 * @return the code for payment status
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name for payment status
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the alias for payment status
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the parent code for this payment status
	 */
	public String getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}
}