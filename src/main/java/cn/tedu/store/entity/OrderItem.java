package cn.tedu.store.entity;

import java.util.Date;

/**
 * 订单所含商品的实体类
 */
public class OrderItem extends BaseEntity {

    public OrderItem() {
    }

    private static final long serialVersionUID = -7192307248837637662L;
    private Integer id;      //
    private Integer oid;     //归属订单编号
    private Integer pid;     //商品编号
    private String title;    //商品标题
    private String image;    //商品图片
    private Long price;      //商品单价
    private Integer num;     //购买数量

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                "} " + super.toString();
    }

    public OrderItem(String createdUser, Date createdTime, String modifiedUser, Date modifiedTime, Integer id) {
        super(createdUser, createdTime, modifiedUser, modifiedTime);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return id != null ? id.equals(orderItem.id) : orderItem.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
