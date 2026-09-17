package com.jkweilai.dept.entity;

/**
 * 数据库表中的每一行记录映射一个Java对象
 * Java对象所在的包一般命名为entity（实体类）
 */
public class Dept {
    private Integer deptNo;
    private String dName;
    private String loc;

    public Dept() {
    }

    public Dept(Integer deptNo, String dName, String loc) {
        this.deptNo = deptNo;
        this.dName = dName;
        this.loc = loc;
    }

    public String getLoc() {
        return loc;
    }

    public String getdName() {
        return dName;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptNo=" + deptNo +
                ", dName='" + dName + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
