package org.zhyuliuk.xmltask.builder;

import org.zhyuliuk.xmltask.entity.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zhyuliuk.xmltask.exception.FlowerException;
import org.zhyuliuk.xmltask.tag.FlowerXmlTag;

public class FlowersStaxBuilder extends AbstractFlowerBuilder {
    private final static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public FlowersStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        flowers = new HashSet<TimeFlower>();
    }

    @Override
    public Set<TimeFlower> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetFlowers(String filename) throws FlowerException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FlowerXmlTag.TIME_FLOWER.getValue())) {
                        TimeFlower flower = buildStudent(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.warn(e.getMessage());
            throw new FlowerException(e);
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw new FlowerException(e);
        }

    }

    private TimeFlower buildStudent(XMLStreamReader reader)
            throws XMLStreamException {
        var flower = new TimeFlower();
        flower.setId(reader.getAttributeValue(null, FlowerXmlTag.ID.getValue()));
        flower.setOrigin(OriginType.valueOf(reader.getAttributeValue(null, FlowerXmlTag.ORIGIN.getValue()).toUpperCase()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (FlowerXmlTag.valueOf(convertToEnumValue(name))) {
                        case NAME: {
                            flower.setName(getXMLText(reader));
                            break;
                        }
                        case SOIL: {
                            flower.setSoil(SoilType.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        }
                        case VISUAL_PARAMETERS: {
                            flower.setVisualParameter(getXmlVisualParameter(reader));
                            break;
                        }
                        case GROWING_TIPS: {
                            flower.setGrowingTips(getXmlGrowingTips(reader));
                            break;
                        }
                        case MULTIPLYING: {
                            flower.setMultiplying(MultiplyingType.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        }

                        case TRANSFER_TIME: {
                            flower.setTransferTime(LocalDateTime.parse(getXMLText(reader)));
                            break;
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerXmlTag.valueOf(convertToEnumValue(name)) == FlowerXmlTag.TIME_FLOWER) {
                        return flower;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <student>");
    }

    private GrowingTips getXmlGrowingTips(XMLStreamReader reader)
            throws XMLStreamException {
        GrowingTips growingTips = new GrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerXmlTag.valueOf(name.toUpperCase())) {
                        case TEMPERATURE: {
                            growingTips.setTemperature(Integer.parseInt(getXMLText(reader)));
                            break;
                        }
                        case LIGHTNING: {
                            growingTips.setLightning(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        }
                        case WATERING: {
                            growingTips.setWatering(Integer.parseInt(getXMLText(reader)));
                            break;
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerXmlTag.valueOf(convertToEnumValue(name)) == FlowerXmlTag.GROWING_TIPS) {
                        return growingTips;

                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <growing-tips>");
    }

    private VisualParameter getXmlVisualParameter(XMLStreamReader reader)
            throws XMLStreamException {
        var visualParameter = new VisualParameter();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerXmlTag.valueOf(convertToEnumValue(name))) {
                        case STEM_COLOR: {
                            visualParameter.setStemColor(getXMLText(reader));
                            break;
                        }
                        case LEAF_COLOR: {
                            visualParameter.setLeafColor(getXMLText(reader));
                            break;
                        }
                        case AVERAGE_SIZE: {
                            visualParameter.setAverageSize(Integer.parseInt(getXMLText(reader)));
                            break;
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerXmlTag.valueOf(convertToEnumValue(name)) == FlowerXmlTag.VISUAL_PARAMETERS) {
                        return visualParameter;

                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <visual-parameters>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private String convertToEnumValue(String valueTag) {
        final String HYPHEN = "-";
        final String UNDERSCORE = "_";
        return valueTag.toUpperCase().replaceAll("-", "_");
    }
}
