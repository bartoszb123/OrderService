package service;

import model.Orders;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReaderXML {

   private List<Orders> listOrders = new ArrayList<Orders>();


    public  List<Orders> writeXML() {

        org.w3c.dom.Document doc = null;

        try {

//            String xmlFile = "src/main/webapp/resources/xml/file.xml";
//            String absolutePath = new File("").getAbsolutePath();
//            String xmlFilePath = absolutePath + File.separator + xmlFile;
            String xmlFilePath= "Orders/";
            File XmlFile = new File(xmlFilePath);
            File[] listOfFiles = XmlFile.listFiles();

            for(File file:listOfFiles){
//            File fXmlFile = new File(xmlFilePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
//            dbFactory.setNamespaceAware(true);
//            doc.getDocumentElement().normalize();
            org.w3c.dom.Element documentElement = doc.getDocumentElement();
            NodeList nodes = documentElement.getChildNodes();
            int length = (nodes.getLength() - 1) / 2; //9
            for (int i = 0; i < length; i++) {
                String ClientId = doc.getElementsByTagName("clientId").item(i).getTextContent();
                String RequestID = doc.getElementsByTagName("requestId").item(i).getTextContent();
                String Name = doc.getElementsByTagName("name").item(i).getTextContent();
                String Quantity = doc.getElementsByTagName("quantity").item(i).getTextContent();
                String Price = doc.getElementsByTagName("price").item(i).getTextContent();

                listOrders.add(new Orders.OrderBuilder()
                        .buildClientId(ClientId)
                        .buildRequestId(Long.parseLong(RequestID))
                        .buildName(Name)
                        .buildQuantity(Integer.parseInt(Quantity))
                        .buildPrice(Double.parseDouble(Price))
                        .build()
                );
            }
//            System.out.println(ClientID+"|"+ClientID2+"|"+ClientID3+"|"+ClientID4);
//                System.out.println("ClientID: \n" + ClientID + "\nRequestID: \n" + RequestID + "\nName:" + Name);
//            }
//            NodeList nodes = documentElement.getChildNodes();
//
//            for (int i = 0; i < nodes.getLength(); i++) {
//                if (i%2!=0) {
//                    System.out.print(nodes.item(i).getTextContent());
//                }
            }
            System.out.println(listOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrders;
    }


    public  List<Orders> buildOrderfromXML(String fileName) {

        org.w3c.dom.Document doc = null;

        try {
//            String xmlFile = "src/main/webapp/resources/xml/file.xml";
//            String absolutePath = new File("").getAbsolutePath();
//            String xmlFilePath = absolutePath + File.separator + xmlFile;
            String xmlFilePath = "Orders/"+fileName;
            File fXmlFile = new File(xmlFilePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
//            dbFactory.setNamespaceAware(true);
//            doc.getDocumentElement().normalize();
            org.w3c.dom.Element documentElement = doc.getDocumentElement();
            NodeList nodes = documentElement.getChildNodes();
            int length = (nodes.getLength() - 1) / 2; //9
            for (int i = 0; i < length; i++) {
                String ClientId = doc.getElementsByTagName("clientId").item(i).getTextContent();
                String RequestID = doc.getElementsByTagName("requestId").item(i).getTextContent();
                String Name = doc.getElementsByTagName("name").item(i).getTextContent();
                String Quantity = doc.getElementsByTagName("quantity").item(i).getTextContent();
                String Price = doc.getElementsByTagName("price").item(i).getTextContent();

                listOrders.add(new Orders.OrderBuilder()
                        .buildClientId(ClientId)
                        .buildRequestId(Long.parseLong(RequestID))
                        .buildName(Name)
                        .buildQuantity(Integer.parseInt(Quantity))
                        .buildPrice(Double.parseDouble(Price))
                        .build()
                );

//            System.out.println(ClientID+"|"+ClientID2+"|"+ClientID3+"|"+ClientID4);
//                System.out.println("ClientID: \n" + ClientID + "\nRequestID: \n" + RequestID + "\nName:" + Name);
//            }
//            NodeList nodes = documentElement.getChildNodes();
//
//            for (int i = 0; i < nodes.getLength(); i++) {
//                if (i%2!=0) {
//                    System.out.print(nodes.item(i).getTextContent());
//                }
            }
            System.out.println(listOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrders;
    }


}
