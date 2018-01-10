package cn.upc.database.model.operation;

/**
 * private Integer startId;
 private Integer line1;
 private Integer rote;
 private Integer line2;
 private Integer endId;
 private Integer tCount;
 */

import java.io.Serializable;

public class InquiryT1M implements Serializable {

    private Station start;
    private Line line1;
    private Station rote;
    private Line line2;
    private Station end;
    private Integer count;

    public Station getStart() {
        return start;
    }

    public void setStart(Station start) {
        this.start = start;
    }

    public Line getLine1() {
        return line1;
    }

    public void setLine1(Line line1) {
        this.line1 = line1;
    }

    public Station getRote() {
        return rote;
    }

    public void setRote(Station rote) {
        this.rote = rote;
    }

    public Line getLine2() {
        return line2;
    }

    public void setLine2(Line line2) {
        this.line2 = line2;
    }

    public Station getEnd() {
        return end;
    }

    public void setEnd(Station end) {
        this.end = end;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "InquiryT1M{" +
                "start=" + start +
                ", line1=" + line1 +
                ", rote=" + rote +
                ", line2=" + line2 +
                ", end=" + end +
                ", count=" + count +
                '}';
    }
}