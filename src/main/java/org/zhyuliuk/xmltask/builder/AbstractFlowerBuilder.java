package org.zhyuliuk.xmltask.builder;

import org.zhyuliuk.xmltask.entity.TimeFlower;
import org.zhyuliuk.xmltask.exception.FlowerException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowerBuilder {


    Set<TimeFlower> flowers;

    protected AbstractFlowerBuilder() {
        flowers = new HashSet<>();
    }

    public Set<TimeFlower> getFlowers() {
        return new HashSet<>(flowers);
    }

    public abstract void buildSetFlowers(String filePath) throws FlowerException;
}

