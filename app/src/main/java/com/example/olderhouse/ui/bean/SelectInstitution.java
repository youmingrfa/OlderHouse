package com.example.olderhouse.ui.bean;

public class SelectInstitution {

    /**
     * 挑选机构的图片url
     */
    private String pic;

    /**
     * 挑选机构的名字
     */
    private String name;

    /**
     * 挑选机构的类型
     */
    private String type;

    /**
     * 挑选机构的床位数量
     */
    private int number;

    /**
     * 挑选机构的位置
     */
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
