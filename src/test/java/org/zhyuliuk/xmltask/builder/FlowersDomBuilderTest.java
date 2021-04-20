package org.zhyuliuk.xmltask.builder;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.zhyuliuk.xmltask.entity.*;
import org.zhyuliuk.xmltask.exception.FlowerException;

import java.time.LocalDateTime;
import java.util.Set;

public class FlowersDomBuilderTest {
    private final static String relativeWay = "src/test/resources/data/flowers.xml";

    @Test
    public void buildSetStudentsFlw1Test() throws FlowerException {
        var domBuilder = new FlowersDomBuilder();
        domBuilder.buildSetFlowers(relativeWay);
        Set<TimeFlower> setActual = domBuilder.getFlowers();
        var timeFlower = new TimeFlower();
        timeFlower.setId("flw1");
        timeFlower.setOrigin(OriginType.CHINA);
        timeFlower.setName("rose");
        timeFlower.setSoil(SoilType.UNPAVED);
        timeFlower.setVisualParameter(new VisualParameter("light-green", "dark-green", 60));
        timeFlower.setGrowingTips(new GrowingTips(20, true, 1400));
        timeFlower.setMultiplying(MultiplyingType.STALK);
        timeFlower.setTransferTime(LocalDateTime.parse("2002-05-30T09:00:00"));
        Assert.assertTrue(setActual.contains(timeFlower));
    }

    @Test
    public void buildSetStudentsFlw2Test() throws FlowerException {
        var domBuilder = new FlowersDomBuilder();
        domBuilder.buildSetFlowers(relativeWay);
        Set<TimeFlower> setActual = domBuilder.getFlowers();
        var timeFlower = new TimeFlower();
        timeFlower.setId("flw2");
        timeFlower.setOrigin(OriginType.IRAN);
        timeFlower.setName("tulip");
        timeFlower.setSoil(SoilType.PODZOL);
        timeFlower.setVisualParameter(new VisualParameter("medium-green", "medium-green", 30));
        timeFlower.setGrowingTips(new GrowingTips(18, true, 1200));
        timeFlower.setMultiplying(MultiplyingType.SEED);
        timeFlower.setTransferTime(LocalDateTime.parse("2002-05-30T09:00:09"));
        Assert.assertTrue(setActual.contains(timeFlower));
    }
}
