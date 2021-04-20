package org.zhyuliuk.xmltask.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.zhyuliuk.xmltask.tag.FlowerXmlTag;
import org.zhyuliuk.xmltask.entity.*;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {


    private Set<TimeFlower> flowers;
    private TimeFlower current;
    private FlowerXmlTag currentXmlTag;
    private EnumSet<FlowerXmlTag> withText;
    private static final String ELEMENT_FLOWER = "time-flower";
    private GrowingTips currentGrowingTips;
    private VisualParameter currentVisualParameter;

    public FlowerHandler() {
        flowers = new HashSet<TimeFlower>();
        withText = EnumSet.range(FlowerXmlTag.NAME, FlowerXmlTag.TRANSFER_TIME);
    }

    public Set<TimeFlower> getFlowers() {
        return flowers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_FLOWER.equals(qName)) {
            current = new TimeFlower();
            currentGrowingTips = new GrowingTips();
            currentVisualParameter = new VisualParameter();
            current.setId(attrs.getValue(0));
            if (attrs.getLength() == 2) {

                current.setOrigin(OriginType.valueOf(attrs.getValue(1).toUpperCase()));
            }
        } else {
            FlowerXmlTag temp = FlowerXmlTag.valueOf(convertToEnumValue(qName));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_FLOWER.equals(qName)) {
            flowers.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {

                case NAME: {
                    current.setName(data);
                    break;
                }
                case SOIL: {
                    current.setSoil(SoilType.valueOf(data.toUpperCase()));
                    break;
                }
                case STEM_COLOR: {
                    currentVisualParameter.setStemColor(data);
                    break;
                }
                case LEAF_COLOR: {
                    currentVisualParameter.setLeafColor(data);
                    break;
                }
                case AVERAGE_SIZE: {
                    currentVisualParameter.setAverageSize(Integer.parseInt(data));
                    break;
                }
                case TEMPERATURE: {
                    currentGrowingTips.setTemperature(Integer.parseInt(data));
                    break;
                }
                case WATERING: {
                    currentGrowingTips.setWatering(Integer.parseInt(data));
                    break;
                }
                case LIGHTNING: {
                    currentGrowingTips.setLightning(Boolean.parseBoolean(data));
                    break;
                }
                case TRANSFER_TIME: {
                    current.setTransferTime(LocalDateTime.parse(data));
                    break;
                }
                case MULTIPLYING: {

                    current.setMultiplying(MultiplyingType.valueOf(data.toUpperCase()));
                    break;
                }
                default: {
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                }
            }
            current.setGrowingTips(currentGrowingTips);
            current.setVisualParameter(currentVisualParameter);
        }
        currentXmlTag = null;
    }

    private String convertToEnumValue(String valueTag) {
        final String HYPHEN = "-";
        final String UNDERSCORE = "_";
        return valueTag.toUpperCase().replaceAll("-", "_");
    }
}


