package org.zhyuliuk.xmltask.validator;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.zhyuliuk.xmltask.exception.FlowerException;
import org.zhyuliuk.xmltask.handler.FlowerErrorHandler;

public class FlowerXmlValidator {
    private static Logger logger = LogManager.getLogger();

    private FlowerXmlValidator() {
    }

    public static boolean validateXmlFile(String xmlRelativePath, String xsdRelativePath) throws FlowerException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(xsdRelativePath);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlRelativePath);
            validator.setErrorHandler(new FlowerErrorHandler());
            validator.validate(source);
        } catch (IOException e) {
            throw new FlowerException("Cannot open file: " + xmlRelativePath, e);
        } catch (SAXException e) {
            logger.warn("File " + xmlRelativePath + " is not valid: ", e);
            return false;
        }
        return true;

    }
}

