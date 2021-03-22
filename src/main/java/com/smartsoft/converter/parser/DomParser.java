package com.smartsoft.converter.parser;

import com.smartsoft.converter.entities.Currency;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DomParser implements Parser {
    @Override
    public List<Currency> parser(String xmlResult) {
        List<Currency> currencies = new ArrayList<>();
        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(xmlResult)));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        NodeList nList = doc.getElementsByTagName("Valute");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                currencies.add(new Currency(
                        eElement.getElementsByTagName("NumCode").
                                item(0).getTextContent(),
                        eElement.getElementsByTagName("CharCode").
                                item(0).getTextContent(),
                        eElement.getElementsByTagName("Name").
                                item(0).getTextContent(),
                        Long.valueOf(eElement.getElementsByTagName("Nominal").
                                item(0).getTextContent()),
                        Double.valueOf(eElement.getElementsByTagName("Value").
                                item(0).getTextContent().replace(',', '.')),
                        LocalDate.now()
                ));
            }
        }
        return currencies;
    }
}
