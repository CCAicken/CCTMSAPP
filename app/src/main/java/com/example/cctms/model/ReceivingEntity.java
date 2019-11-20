package com.example.cctms.model;

public class ReceivingEntity {
    public String name;
    public String logistics_code;
    public String order_number;
    public String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogistics_code() {
        return logistics_code;
    }

    public void setLogistics_code(String logistics_code) {
        this.logistics_code = logistics_code;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ReceivingEntity() {
        super();
    }

    public ReceivingEntity(String name,
                           String logistics_code,
                           String order_number,
                           String data) {
        super();
        this.name=name;
        this.logistics_code=logistics_code;
        this.order_number=order_number;
        this.data=data;
    }

}
