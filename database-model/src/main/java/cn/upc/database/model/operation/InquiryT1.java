package cn.upc.database.model.operation;

import java.io.Serializable;

public class InquiryT1 implements Serializable {

    private Integer startId;
    private Integer line1;
    private Integer rote;
    private Integer line2;
    private Integer endId;
    private Integer tCount;

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public Integer getLine1() {
        return line1;
    }

    public void setLine1(Integer line1) {
        this.line1 = line1;
    }

    public Integer getRote() {
        return rote;
    }

    public void setRote(Integer rote) {
        this.rote = rote;
    }

    public Integer getLine2() {
        return line2;
    }

    public void setLine2(Integer line2) {
        this.line2 = line2;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public Integer gettCount() {
        return tCount;
    }

    public void settCount(Integer tCount) {
        this.tCount = tCount;
    }

    @Override
    public String toString() {
        return "InquiryT1{" +
                "startId=" + startId +
                ", line1=" + line1 +
                ", rote=" + rote +
                ", line2=" + line2 +
                ", endId=" + endId +
                ", tCount=" + tCount +
                '}';
    }
}