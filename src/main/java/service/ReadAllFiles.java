package service;

import model.Requests;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReadAllFiles {

    public void writeFiles() {


        try {
            org.w3c.dom.Document doc = null;

//            String xmlFile = "src/main/webapp/resources/file.xml";
            String xmlFiles = "src/main/webapp/resources/xml";
            String absolutePath = new File("").getAbsolutePath();
            String xmlFilesPath = absolutePath + File.separator + xmlFiles;
            File fXmlFile = new File(xmlFilesPath);
            File[] listOfFiles = fXmlFile.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                File absoluteFile = listOfFiles[i].getAbsoluteFile();
//                if(absoluteFile.endsWith(".xml")||absoluteFile.endsWith(".XML"))
//                {

                JAXBContext jaxbContext = JAXBContext.newInstance(Requests.class);

//            XMLStreamReader

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Requests orders = (Requests) jaxbUnmarshaller.unmarshal(absoluteFile);

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(orders, System.out);

                String names = "Names [Zamowienie nr: "+(i+1)+"]";
                int licznik=0;
                System.out.println(names);
                for (int j =0; j<orders.getFields().size();j++) {

                    System.out.println(j+". "+orders.getFields().get(j).getName());
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }


}
