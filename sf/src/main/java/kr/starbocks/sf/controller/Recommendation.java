package kr.starbocks.sf.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Recommendation.class)
public class Recommendation {
	@JsonProperty("user")
	private long user;
	@JsonProperty("property")
	private long property;
	
	public long getUser() {
		return user;
	}
	public void setUser(long user) {
		this.user = user;
	}
	public long getProperty() {
		return property;
	}
	public void setProperty(long property) {
		this.property = property;
	}
	@Override
	public String toString() {
		return "Recommendation [user=" + user + ", property=" + property + "]";
	}
	
	
}
