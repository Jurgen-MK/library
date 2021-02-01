package kz.ktzh.models;

public class UserCreationRequest {

	private Users user;
	private UserInfo userInfo;

	public UserCreationRequest() {

	}

	public UserCreationRequest(Users user, UserInfo userInfo) {
		this.user = user;
		this.userInfo = userInfo;
	}

	public Users getUser() {
		return user;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
