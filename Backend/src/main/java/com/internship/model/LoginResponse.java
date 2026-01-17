package com.internship.model;

public class LoginResponse {
	private String token;
	private String role;
	private boolean firstLogin;
	private String nextAction;
	
	public LoginResponse(String token, String role, boolean firstLogin, String nextAction) {

		this.token = token;
		this.role = role;
		this.firstLogin = firstLogin;
		this.nextAction = nextAction;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
	
}
