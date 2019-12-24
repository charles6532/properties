package kr.starbocks.sf.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Prediction.class)
public class Prediction {
	@JsonProperty("user")
	private long user;
	@JsonProperty("property")
	private long property;
	@JsonProperty("prediction")
	private float prediction;
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
	public float getPrediction() {
		return prediction;
	}
	public void setPrediction(float prediction) {
		this.prediction = prediction;
	}
	
}
