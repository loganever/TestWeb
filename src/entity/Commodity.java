package entity;

import org.springframework.web.multipart.MultipartFile;

public class Commodity 
{
	private int commodityId;
	private String commodityName;
	private double price;
	private String salerName;
	private String location;
	private String date;
	private String imgsrc;
	private int isSaled;
	private String introducation;
	private String school;
	private String commodityClass;
	private String detail;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public int getIsSaled() {
		return isSaled;
	}
	public void setIsSaled(int isSaled) {
		this.isSaled = isSaled;
	}
	public String getIntroducation() {
		return introducation;
	}
	public void setIntroducation(String introducation) {
		this.introducation = introducation;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCommodityClass() {
		return commodityClass;
	}
	public void setCommodityClass(String commodityClass) {
		this.commodityClass = commodityClass;
	}
	public int getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSalerName() {
		return salerName;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
