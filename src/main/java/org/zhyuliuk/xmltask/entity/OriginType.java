package org.zhyuliuk.xmltask.entity;

public enum OriginType {

    BELARUS,
    RUSSIA,
    CHINA,
    AMERICA,
    TUNIS,
    IRAN,
    GREECE,
    DEFAULT;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }


}
