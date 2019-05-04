package com.example.wework.model;

public class house_Information {
    private Integer id;

    private Integer landlordid;

    private String city;

    private String area;

    private String address;

    private String officetype;

    private Integer officenumber;

    private Integer remainnumber;

    private Integer personNumber;

    private Integer price;

    private Integer prepayment;

    private String image;

    private String status;

    private String rentornot;

    private String uuid;

    private String empty;

    private String introduce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(Integer landlordid) {
        this.landlordid = landlordid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOfficetype() {
        return officetype;
    }

    public void setOfficetype(String officetype) {
        this.officetype = officetype == null ? null : officetype.trim();
    }

    public Integer getOfficenumber() {
        return officenumber;
    }

    public void setOfficenumber(Integer officenumber) {
        this.officenumber = officenumber;
    }

    public Integer getRemainnumber() {
        return remainnumber;
    }

    public void setRemainnumber(Integer remainnumber) {
        this.remainnumber = remainnumber;
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Integer prepayment) {
        this.prepayment = prepayment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRentornot() {
        return rentornot;
    }

    public void setRentornot(String rentornot) {
        this.rentornot = rentornot == null ? null : rentornot.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty == null ? null : empty.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}