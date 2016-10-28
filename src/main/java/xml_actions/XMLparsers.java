package xml_actions;

import org.jsoup.nodes.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static xml_actions.Constants.PATH_TO_XML_DOCUMENT;

/**
 * Created by Jack on 28.10.2016.
 */
public class XMLparsers {

    public static void testXPath(String pathToTheXML) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

            Document doc = DocumentBuilderFactory.newInstance().
                    newDocumentBuilder().parse(new File
                    (pathToTheXML));
            //pattern for selection
            String xPathMatcher = "//user[@id='1']";//select user with id 1
            String xPathMatcher2 = "//user";//select all users
            String xPathMatcher3 = "//address";//select all addresses
            String xPathMatcher4 = "//user/address";//also select all addresses from all users
            String xPathMatcher5 = "//user[address[city='Kiev']]";//select user form Kiev !dont show all!

           /* XPathExpression xPathExpression = xPath.compile(xPathMatcher);
              NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);*/

            XPathExpression xPathExpression = XPathFactory.newInstance().newXPath().compile(xPathMatcher5);

            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println("<" + nodeList.item(i).getNodeName() + ">");
                System.out.println(nodeList.item(i).getTextContent());
            }


    }

    public static void xmlDOMparser(String pathToTheXML) throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(pathToTheXML));

        org.w3c.dom.Element element = doc.getDocumentElement();

        System.out.println("<?xml version="+"\""+doc.getXmlVersion()+"\"" + " encoding="+"\""+doc.getXmlEncoding()+"\""+"?>");
        System.out.println(DomParsingUtils.parse(element));
    }


    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {

        testXPath(PATH_TO_XML_DOCUMENT);

        System.out.println("======================");

        xmlDOMparser(PATH_TO_XML_DOCUMENT);
    }
}
