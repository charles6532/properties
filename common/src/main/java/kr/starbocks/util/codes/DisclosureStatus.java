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
public enum DisclosureStatus {
	DISCLOSED("DS00", "공개됨", "Disclosed","DS01"),
	REQUESTED("DS01", "공개 요청", "Requested","")
	;
	
	private String code;
	private String name;
	private String alias;
	private String parent;
	private DisclosureStatus(final String code, final String name, final String alias, final String parent) {
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