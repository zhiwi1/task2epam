package org.zhyuliuk.xmltask.builder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.zhyuliuk.xmltask.entity.*;
import org.zhyuliuk.xmltask.exception.FlowerException;

public class FlowersDomBuilder extends AbstractFlowerBuilder {
    private final static Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public FlowersDomBuilder() throws FlowerException {
        flowers = new HashSet<TimeFlower>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.info("FlowersDomBuilder", e);
            throw new FlowerException();
        }
    }

    @Override
    public Set<TimeFlower> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetFlowers(String filename) throws FlowerException {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName("time-flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                TimeFlower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException | SAXException e) {
            logger.info("FlowersDomBuilder", e);
            throw new FlowerException();

        }
    }

    private TimeFlower buildFlower(Element studentElement) {
        var flower = new TimeFlower();
        flower.setId(studentElement.getAttribute("id"));
        if (studentElement.getAttribute("origin") != null) {
            flower.setOrigin(OriginType.valueOf(studentElement.getAttribute("origin").toUpperCase()));
        }
        flower.setName(getElementTextContent(studentElement, "name"));
        flower.setMultiplying(MultiplyingType.valueOf(getElementTextContent(studentElement, "multiplying").toUpperCase()));
        flower.setTransferTime(LocalDateTime.parse(getElementTextContent(studentElement, "transfer-time")));
        flower.setSoil(SoilType.valueOf(getElementTextContent(studentElement, "soil").toUpperCase()));
        var visualParameter = new VisualParameter();
        visualParameter.setAverageSize(Integer.parseInt(getElementTextContent(studentElement, "average-size")));
        visualParameter.setLeafColor(getElementTextContent(studentElement, "leaf-color"));
        visualParameter.setStemColor(getElementTextContent(studentElement, "stem-color"));
        flower.setVisualParameter(visualParameter);
        var growingTips = new GrowingTips();
        growingTips.setLightning(Boolean.parseBoolean(getElementTextContent(studentElement, "lightning")));
        growingTips.setWatering(Integer.parseInt(getElementTextContent(studentElement, "watering")));
        growingTips.setTemperature(Integer.parseInt(getElementTextContent(studentElement, "temperature")));
        flower.setGrowingTips(growingTips);
        return flower;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}