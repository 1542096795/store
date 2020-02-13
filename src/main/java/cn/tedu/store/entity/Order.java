package cn.tedu.store.entity;

import java.util.Date;


/**
 * 订单实体类
 */
public class Order extends BaseEntity {
    public Order() {
    }

    private static final long serialVersionUID = -5782547388054077832L;
    private Integer oid;                 //订单编号
    private Integer uid;                 //用户编号
    private String receiverName;        //收货人
    private String receiverPhone;       //收货人电话
    private String receiverProvince;    //收货地址-省
    private String receiverCity;        //收货地址-市
    private String receiverArea;        //收货地址-区
    private String receiverAddress;     //收货地址-详细地址
    private Long totalPrice;            //总价
    private Integer state;               //状态：0-未支付，1-已支付，2-已取消，3-已关闭
    private Date orderTime;             //下单时间
    private Date payTime;               //支付时间
    private Integer payType;            //支付方式 ： 0-银联,1-支付宝， 2-微信....

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return oid != null ? oid.equals(order.oid) : order.oid == null;
    }

    @Override
    public int hashCode() {
        return oid != null ? oid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverProvince='" + receiverProvince + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverArea='" + receiverArea + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", state=" + state +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", payType=" + payType +
                "} " + super.toString();
    }

    public Order(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer oid, Integer uid, String receiverName, String receiverPhone, String receiverProvince, String receiverCity, String receiverArea, String receiverAddress, Long totalPrice, Integer state, Date orderTime, Date payTime, Integer payType) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.oid = oid;
        this.uid = uid;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverProvince = receiverProvince;
        this.receiverCity = receiverCity;
        this.receiverArea = receiverArea;
        this.receiverAddress = receiverAddress;
        this.totalPrice = totalPrice;
        this.state = state;
        this.orderTime = orderTime;
        this.payTime = payTime;
        this.payType = payType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}

