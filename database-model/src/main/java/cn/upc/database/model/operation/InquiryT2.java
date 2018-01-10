package cn.upc.database.model.operation;

import java.io.Serializable;

public class InquiryT2 implements Serializable {

    private Integer startId;
    private Integer line1;
    private Integer rote1;
    private Integer line2;
    private Integer rote2;
    private Integer line3;
    private Integer endId;
    private Integer tCount;

    @Override
    public String toString() {
        return "InquiryT2{" +
                "startId=" + startId +
                ", line1=" + line1 +
                ", rote1=" + rote1 +
                ", line2=" + line2 +
                ", rote2=" + rote2 +
                ", line3=" + line3 +
                ", endId=" + endId +
                ", tCount=" + tCount +
                '}';
    }

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

    public Integer getRote1() {
        return rote1;
    }

    public void setRote1(Integer rote1) {
        this.rote1 = rote1;
    }

    public Integer getLine2() {
        return line2;
    }

    public void setLine2(Integer line2) {
        this.line2 = line2;
    }

    public Integer getRote2() {
        return rote2;
    }

    public void setRote2(Integer rote2) {
        this.rote2 = rote2;
    }

    public Integer getLine3() {
        return line3;
    }

    public void setLine3(Integer line3) {
        this.line3 = line3;
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
}