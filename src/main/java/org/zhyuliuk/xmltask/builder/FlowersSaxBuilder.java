package org.zhyuliuk.xmltask.builder;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.zhyuliuk.xmltask.entity.TimeFlower;
import org.zhyuliuk.xmltask.exception.FlowerException;
import org.zhyuliuk.xmltask.handler.FlowerErrorHandler;
import org.zhyuliuk.xmltask.handler.FlowerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class FlowersSaxBuilder extends AbstractFlowerBuilder {
    private final static Logger logger = LogManager.getLogger();
    private FlowerHandler handler = new FlowerHandler();
    private XMLReader reader;

    public FlowersSaxBuilder() throws FlowerException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.warn(e.getMessage());
            throw new FlowerException(e);
        }
        reader.setErrorHandler(new FlowerErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public Set<TimeFlower> getFlowers() {
        return flowers;
    }

    @Override
    public void buildSetFlowers(String filename) throws FlowerException {
        try {
            reader.parse(filename);

        } catch (IOException | SAXException e) {
            logger.warn(e.getMessage());
            throw new FlowerException(e);
        }
        flowers = handler.getFlowers();
    }
}