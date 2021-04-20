package org.zhyuliuk.xmltask.entity;

public enum MultiplyingType {
    SEED,
    STALK,
    LIAF;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
