package br.app;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginBean")
public class LoginBean {
	private String email = "Roberto@teste.com";
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void showEmailTyped() {
		System.out.println(email);
	}
	
}
