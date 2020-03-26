package entity;

import java.util.Vector;

public class User 
{
	private String username;
	private String password;
	private String authcode;
	private String school;
	private String sex;
	private String grade;
	private int credit;
	private String privilege;
	private Vector<Commodity> shoppingRecord;
	private Vector<GeZi> geZi;
	private String ip;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Vector<GeZi> getGeZi() {
		return geZi;
	}
	public void setGeZi(Vector<GeZi> geZi) {
		this.geZi = geZi;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}


	public Vector<Commodity> getShoppingRecord() {
		return shoppingRecord;
	}
	public void setShoppingRecord(Vector<Commodity> shoppingRecord) {
		this.shoppingRecord = shoppingRecord;
	}

	
}
