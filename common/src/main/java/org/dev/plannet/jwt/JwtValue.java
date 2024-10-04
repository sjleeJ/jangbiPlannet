package org.dev.plannet.jwt;

public class JwtValue {
	private String sub;
	private String auth;
	private String id;
	private String email;
	private Long exp;

	public Long getExp() {
		return exp;
	}

	public String getAuth() {
		return auth;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getSub() {
		return sub;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}
}
