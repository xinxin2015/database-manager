package cn.upc.database.model.operation;

import java.io.Serializable;

public class Transit implements Serializable {

    public static final int TYPE_TO = 1;
    public static final int TYPE_RETURN = 9;

    private Integer id;
    private Integer lineId;
    private Integer startStation;
    private Integer endStation;
    private String startTime;
    private String endTime;
    private Float price;
    private Float maxPrice;
    private Integer cityId;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getStartStation() {
        return startStation;
    }

    public void setStartStation(Integer startStation) {
        this.startStation = startStation;
    }

    public Integer getEndStation() {
        return endStation;
    }

    public void setEndStation(Integer endStation) {
        this.endStation = endStation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Transit{" +
                "id=" + id +
                ", lineId=" + lineId +
                ", startStation=" + startStation +
                ", endStation=" + endStation +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", price=" + price +
                ", maxPrice=" + maxPrice +
                ", cityId=" + cityId +
                ", type=" + type +
                '}';
    }
}