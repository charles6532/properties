package kr.starbocks.api.value;

public class RecommVO {
	long user;
	long property;
	float rating;
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
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "RecommVO [user=" + user + ", property=" + property + ", rating=" + rating + "]";
	}
	
}
