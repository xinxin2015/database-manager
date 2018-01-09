package cn.upc.database.model.operation;

import java.io.Serializable;

public class Rote implements Serializable {


    private Integer id;
    private Integer stationId;
    private Integer transitId;
    private Integer position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getTransitId() {
        return transitId;
    }

    public void setTransitId(Integer transitId) {
        this.transitId = transitId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Rote{" +
                "id=" + id +
                ", stationId=" + stationId +
                ", transitId=" + transitId +
                ", position=" + position +
                '}';
    }
}