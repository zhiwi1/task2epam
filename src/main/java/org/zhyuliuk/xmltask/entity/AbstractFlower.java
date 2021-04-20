package org.zhyuliuk.xmltask.entity;

import java.util.Objects;

public abstract class AbstractFlower {
    private String id;
    private OriginType origin;
    private String name;
    private SoilType soil;
    private VisualParameter visualParameter;
    private GrowingTips growingTips;
    private MultiplyingType multiplying;

    {
        origin = OriginType.DEFAULT;
    }

    public AbstractFlower(String id, OriginType origin, String name, SoilType soil,
                          VisualParameter visualParameter, GrowingTips growingTips, MultiplyingType multiplying) {
        this.id = id;
        this.origin = origin;
        this.name = name;
        this.soil = soil;
        this.visualParameter = visualParameter;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
    }

    public AbstractFlower() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OriginType getOrigin() {
        return origin;
    }

    public void setOrigin(OriginType origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SoilType getSoil() {
        return soil;
    }

    public void setSoil(SoilType soil) {
        this.soil = soil;
    }

    public VisualParameter getVisualParameter() {
        return visualParameter;
    }

    public void setVisualParameter(VisualParameter visualParameter) {
        this.visualParameter = visualParameter;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public MultiplyingType getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(MultiplyingType multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFlower that = (AbstractFlower) o;
        return id.equals(that.id) && origin == that.origin && name.equals(that.name)
                && soil == that.soil && visualParameter.equals(that.visualParameter) &&
                growingTips.equals(that.growingTips) && multiplying == that.multiplying;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < id.length(); i++) {
            result = 31 * result + id.charAt(i);
        }
        result = 31 * result + origin.hashCode();
        for (int i = 0; i < name.length(); i++) {
            result = 31 * result + name.charAt(i);
        }
        result = 31 * result + soil.hashCode();
        result = 31 * result + visualParameter.hashCode();
        result = 31 * result + multiplying.hashCode();
        result = 31 * result + growingTips.hashCode();


        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id='").append(id).append('\'').append(", origin=").
                append(origin).append(", name='").append(name).append('\'').append(", soil=").append(soil).
                append(", visualParameter=").append(visualParameter).append(", growingTips=").append(growingTips).
                append(", multiplying=").append(multiplying);
        return stringBuilder.toString();
    }
}
