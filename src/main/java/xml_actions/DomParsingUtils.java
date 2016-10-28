package xml_actions;

import org.w3c.dom.*;

import java.io.*;

/**
 * Created by Jack on 28.10.2016.
 */
public class DomParsingUtils {


    public static String parse(Element root) {
        StringBuilder sb = new StringBuilder();
        if (root.hasAttributes()) {
            sb.append("<" + root.getTagName() + getAttributesString(root) + ">");
        } else {
            sb.append("<" + root.getTagName() + ">");
        }
        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                sb.append(parse(element));
            } else if (node.getNodeType() == Node.TEXT_NODE) {
                Text text = (Text) node;
                sb.append(text.getTextContent());
            } else if (node.getNodeType() == Node.COMMENT_NODE) {
                Comment com = (Comment) node;
                sb.append("<!--" + com.getTextContent() + "-->");
            }
        }
        sb.append("</" + root.getTagName() + ">");
        return sb.toString();
    }


    private static String getAttributesString(Element root) {
        NamedNodeMap map = root.getAttributes();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.getLength(); i++) {
            Attr attr = (Attr) map.item(i);
            sb.append(" " + attr.getName() + "=" + "\"" + attr.getValue() + "\"");
        }
        return sb.toString();
    }

    public static String parseDoc(Document doc) {
        String header = "<?xml version=" + "\"" + doc.getXmlVersion() + "\"" + " encoding=" + "\"" + doc.getXmlEncoding() + "\"" + "?>\n";
        String parseResult = parse(doc.getDocumentElement());
        return header + parseResult;
    }

    public static void stringToXml(String xml, String path) {

        try (FileWriter fw = new FileWriter(new File(path))) {
            fw.write(xml);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("wrong path");
        }

    }

}
