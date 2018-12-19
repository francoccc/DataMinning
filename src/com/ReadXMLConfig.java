package com;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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

    private HashMap<String, Element> confMap;

    static {
        readXMLConfig = new ReadXMLConfig();
    }

    private ReadXMLConfig() {
        try {
            initReadXMLConfig("com" + File.separator + "config.xml");
        } catch (Exception e) {
            System.out.println("config.xml init failure!");
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
            DOMParser parser = new DOMParser();
            parser.parse(new InputSource(in));
            Document m_config = parser.getDocument();
            Element root = m_config.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("switch").item(0).getChildNodes();
            int confNum = nodeList.getLength();
            confMap = new HashMap<>();
            for (int i = 0; i < confNum; i++) {
                Element ele = (Element) nodeList.item(i);
                confMap.put(ele.getTagName(), ele);
            }
        } catch (Exception e) {
            System.out.println("xml init failure!");
        } finally {
            in.close();
        }
    }

    public String getConfigValue(String key) {
        return getConfigValue(key, null);
    }

    public String getConfigValue(String key, String defaultValue) {
        String[] splits = key.split("\\.");
        String k = splits[0].trim();
        if(confMap == null) {
            return defaultValue;
        } else {
            Element ele= confMap.get(k);
            String result = ele.getElementsByTagName(splits[1].trim()).item(0)
                    .getFirstChild().getNodeValue();
            if(result != null && !"".equals(result)) {
                return result;
            }
        }
        return defaultValue;
    }
}
