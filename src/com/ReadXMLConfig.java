package com;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 读取XML配置文件类
 *
 * @author Franco
 */
public class ReadXMLConfig {

    private static ReadXMLConfig readXMLConfig;

    /** Config */
    private HashMap<String, Node> confMap;
    /** Attribute */
    private HashMap<String, String> attrMap = new HashMap<>();

    static {
        readXMLConfig = new ReadXMLConfig();
    }

    private ReadXMLConfig() {
        try {
            initReadXMLConfig("com" + File.separator + "config.xml");
        } catch (Exception e) {
            throw new RuntimeException("config.xml init failure!");
        }
    }

    /**
     *
     * @return readXMLConfig
     */
    public static ReadXMLConfig getInstance() {
        return readXMLConfig;
    }

    /**
     * initXMLConfig
     * @param xmlFileName
     * @throws Exception
     */
    private void initReadXMLConfig(String xmlFileName) throws Exception {
        InputStream in = ReadXMLConfig.class.getClassLoader().getResourceAsStream(xmlFileName);
        if(null == in) {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(xmlFileName);
        }
        if(null == in) {
            throw new NullPointerException(xmlFileName + "not Found.");
        }
        try {
            DocumentBuilder domBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document configDoc = domBuilder.parse(in);
            configDoc.getDocumentElement().normalize();
            Element root = configDoc.getDocumentElement();
            NodeList switches = root.getChildNodes();
            int confNum = switches.getLength();
            confMap = new HashMap<>();
            for (int i = 0; i < confNum; i++) {
                Node node = switches.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    confMap.put(node.getNodeName(), node);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("xml init failure!");
        } finally {
            in.close();
        }
    }

    private void initAttributeMap(String object) {
        if(confMap.containsKey(object)) {
            NodeList nodes = confMap.get(object).getChildNodes();
            for(int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    attrMap.put(object + "." + node.getNodeName(),
                            node.getFirstChild().getNodeValue().trim());
                }
            }
        }
    }

    public String getConfigValue(String key) {
        return getConfigValue(key, null);
    }

    public String getConfigValue(String key, String defaultValue) {
        if(attrMap.containsKey(key)) {
            return attrMap.get(key);
        } else {
            initAttributeMap(key.split("\\.")[0].trim());
        }
        String result = attrMap.get(key);
        if(result == null) {
            if(defaultValue == null) {
                throw new RuntimeException(key + " not found!");
            }
            return defaultValue;
        } else {
            return result;
        }
    }
}
