package org.zhyuliuk.xmltask.entity;

public class GrowingTips {
    private int temperature;
    private boolean lightning;
    private int watering;

    public GrowingTips(int temperature, boolean lightning, int watering) {
        this.temperature = temperature;
        this.lightning = lightning;
        this.watering = watering;
    }

    public GrowingTips() {
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isLightning() {
        return lightning;
    }

    public void setLightning(boolean lightning) {
        this.lightning = lightning;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTips that = (GrowingTips) o;
        return temperature == that.temperature && lightning == that.lightning && watering == that.watering;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int hashBoolean = lightning ? 1 : 0;
        result = 31 * result + temperature;
        result = 31 * result + hashBoolean;
        result = 31 * result + watering;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("temperature=").append(temperature).append(", lightning=").
                append(lightning).append(", watering=").append(watering).append('}');
        return stringBuilder.toString();
    }
}
