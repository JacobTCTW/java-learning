package com.example.druid.multisource.model;

public class HouseType {
    //房屋Id
    private String Id;
    //房屋类型
    private String typeName;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
