package cn.tedu.store.entity.vo;

import java.io.Serializable;

public class CartVO implements Serializable {
    public CartVO() {
    }

    private Integer cid;
    private Integer pid;
    private Integer uid;
    private Integer num;
    private Long cartPrice;//购物车价格
    private Long realPrice;//商品的真实价格
    private String title;
    private String image;

    private static final long serialVersionUID = -5442925551652702858L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartVO cartVO = (CartVO) o;
        return cid != null ? cid.equals(cartVO.cid) : cartVO.cid == null;
    }

    @Override
    public int hashCode() {
        return cid != null ? cid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", uid=" + uid +
                ", num=" + num +
                ", cartPrice=" + cartPrice +
                ", realPrice=" + realPrice +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public CartVO(Integer cid, Integer pid, Integer uid, Integer num, Long cartPrice, Long realPrice, String title, String image) {
        this.cid = cid;
        this.pid = pid;
        this.uid = uid;
        this.num = num;
        this.cartPrice = cartPrice;
        this.realPrice = realPrice;
        this.title = title;
        this.image = image;
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

    public Long getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Long cartPrice) {
        this.cartPrice = cartPrice;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }


    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
