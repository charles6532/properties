/**
 * 
 */
package kr.starbocks.rapms.handler;

/**
 * @author Playdata
 *
 */
public class Notification {
	private String sender;
	private String addressee;
	private String message;
	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * @return the addressee
	 */
	public String getAddressee() {
		return addressee;
	}
	/**
	 * @param addressee the addressee to set
	 */
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return message;
	}
}
