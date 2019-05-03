package cn.chainof.sunup.model;

import java.io.Serializable;
import java.util.Date;

public class ProductItem implements Serializable {
    private String id;

    private Integer rank;

    private String itemName;

    private String itemImg;

    private String itemParent;

    private String itemDeclare;

    private Byte isRoot;

    private Byte isDeleted;

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg == null ? null : itemImg.trim();
    }

    public String getItemParent() {
        return itemParent;
    }

    public void setItemParent(String itemParent) {
        this.itemParent = itemParent == null ? null : itemParent.trim();
    }

    public String getItemDeclare() {
        return itemDeclare;
    }

    public void setItemDeclare(String itemDeclare) {
        this.itemDeclare = itemDeclare == null ? null : itemDeclare.trim();
    }

    public Byte getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Byte isRoot) {
        this.isRoot = isRoot;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductItem other = (ProductItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRank() == null ? other.getRank() == null : this.getRank().equals(other.getRank()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getItemImg() == null ? other.getItemImg() == null : this.getItemImg().equals(other.getItemImg()))
            && (this.getItemParent() == null ? other.getItemParent() == null : this.getItemParent().equals(other.getItemParent()))
            && (this.getItemDeclare() == null ? other.getItemDeclare() == null : this.getItemDeclare().equals(other.getItemDeclare()))
            && (this.getIsRoot() == null ? other.getIsRoot() == null : this.getIsRoot().equals(other.getIsRoot()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRank() == null) ? 0 : getRank().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getItemImg() == null) ? 0 : getItemImg().hashCode());
        result = prime * result + ((getItemParent() == null) ? 0 : getItemParent().hashCode());
        result = prime * result + ((getItemDeclare() == null) ? 0 : getItemDeclare().hashCode());
        result = prime * result + ((getIsRoot() == null) ? 0 : getIsRoot().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}