package org.zhyuliuk.xmltask.entity;

import java.time.LocalDateTime;


public class TimeFlower extends AbstractFlower {
    private LocalDateTime transferTime;

    public TimeFlower(String id, OriginType origin, String name, SoilType soil, VisualParameter visualParameter, GrowingTips growingTips, MultiplyingType multiplying, LocalDateTime transferTime) {
        super(id, origin, name, soil, visualParameter, growingTips, multiplying);
        this.transferTime = transferTime;
    }

    public TimeFlower() {

    }

    public LocalDateTime getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(LocalDateTime transferTime) {
        this.transferTime = transferTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeFlower that = (TimeFlower) o;
        return transferTime.equals( that.transferTime);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + super.hashCode() + transferTime.getDayOfMonth();
        result = result * 31 + transferTime.getMonth().getValue();
        result = result * 31 + transferTime.getYear();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TimeFlower{").append(super.toString()).
                append(", transferTime=").append(transferTime).append('}');
        return stringBuilder.toString();
    }
}
