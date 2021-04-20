package org.zhyuliuk.xmltask.entity;

public enum SoilType {
        PODZOL,
        SODPODZOL,
        UNPAVED;


        @Override
        public String toString() {
            return this.name().toLowerCase();
        }



}
