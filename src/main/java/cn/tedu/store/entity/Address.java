package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

public class Address extends BaseEntity {
    public Address() {
    }

    private static final long serialVersionUID = 9161882632251938608L;
    private Integer aid;
    private Integer uid;            //数据所归属的用户的id
    private String receiver;            //收货人
    private String provinceName;    //省的名称
    private String provinceCode;    //省的代号
    private String cityName;        //市的名称
    private String cityCode;        //是的代号
    private String areaName;        // 区的名称
    private String areaCode;        //区的代号
    private String zip;             //邮政编码
    private String address;         //详细收获地址
    private String phone;           //手机
    private String tel;             //固定电话
    private String tag;             //地址类型/标签
    private Integer isDefault;     //是否为默认收获地址  0-不默认，1-默认


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return aid.equals(address.aid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid);
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", receiver='" + receiver + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", zip='" + zip + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault= '" + isDefault +'\''+" , "+super.toString()+
                '}';
    }

    public Address(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer aid, Integer uid, String receiver, String provinceName, String provinceCode, String cityName, String cityCode, String areaName, String areaCode, String zip, String address, String phone, String tel, String tag, Integer isDefault) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.aid = aid;
        this.uid = uid;
        this.receiver = receiver;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.areaName = areaName;
        this.areaCode = areaCode;
        this.zip = zip;
        this.address = address;
        this.phone = phone;
        this.tel = tel;
        this.tag = tag;
        this.isDefault = isDefault;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
