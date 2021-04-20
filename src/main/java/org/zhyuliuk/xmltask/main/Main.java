package org.zhyuliuk.xmltask.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zhyuliuk.xmltask.builder.FlowersSaxBuilder;
import org.zhyuliuk.xmltask.builder.FlowersDomBuilder;
import org.zhyuliuk.xmltask.builder.FlowersStaxBuilder;
import org.zhyuliuk.xmltask.exception.FlowerException;
import org.zhyuliuk.xmltask.validator.FlowerXmlValidator;

import java.io.IOException;

public class Main {
private final static Logger logger=LogManager.getLogger();

    public static void main(String[] args) throws FlowerException, IOException {
        String xmlRelativePath = "src/main/resources/data/flowers.xml";
        String xsdRelativePath = "src/main/resources/data/flowers.xsd";
        FlowerXmlValidator.validateXmlFile(xmlRelativePath, xsdRelativePath);
        var saxBuilder = new FlowersSaxBuilder();
        saxBuilder.buildSetFlowers(xmlRelativePath);
        logger.info(saxBuilder.getFlowers());
        var domBuilder = new FlowersDomBuilder();
        domBuilder.buildSetFlowers(xmlRelativePath);
        logger.info(domBuilder.getFlowers());
        var staxBuilder = new FlowersStaxBuilder();
        staxBuilder.buildSetFlowers(xmlRelativePath);
        logger.info(staxBuilder.getFlowers());
    }
}
