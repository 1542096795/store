package cn.tedu.store.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 购物车实体类
 */
public class Cart extends BaseEntity {
    private Integer cid;
    private Integer pid;
    private Integer uid;
    private Integer num;
    private Long price;

    public Cart() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cid, cart.cid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid);
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", uid=" + uid +
                ", num=" + num +
                ", price=" + price +
                "} " + super.toString();
    }

    public Cart(Integer cid, Integer pid, Integer uid, Integer num, Long price) {
        this.cid = cid;
        this.pid = pid;
        this.uid = uid;
        this.num = num;
        this.price = price;
    }

    public Cart(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer cid, Integer pid, Integer uid, Integer num, Long price) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.cid = cid;
        this.pid = pid;
        this.uid = uid;
        this.num = num;
        this.price = price;
    }
}
