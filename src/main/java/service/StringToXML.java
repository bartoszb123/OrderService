package service;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;

public class StringToXML {


    public void convertToXml(String xmlString , String nameFile) { // String to xml

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));
            String thisFile = new String(nameFile);
            OutputStreamWriter oos = new OutputStreamWriter (new FileOutputStream("Orders/"+thisFile));
            oos.write (xmlString);
            oos.close();
            oos = null;
            thisFile=null;
        }
        catch (IOException ioe)
        {
            System.out.println("IO error: " + ioe);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

}
