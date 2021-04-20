package org.zhyuliuk.xmltask.validator;

import org.testng.annotations.Test;
import org.zhyuliuk.xmltask.exception.FlowerException;

public class FlowerValidatorTest {
    @Test
    public void validateXmlFileTest() throws FlowerException {
        String xmlRelativePath = "src/main/resources/data/flowers.xml";
        String xsdRelativePath = "src/main/resources/data/flowers.xsd";

        FlowerXmlValidator.validateXmlFile(xmlRelativePath, xsdRelativePath);
    }
}