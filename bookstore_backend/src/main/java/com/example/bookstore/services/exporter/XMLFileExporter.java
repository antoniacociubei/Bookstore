package com.example.bookstore.services.exporter;

import com.example.bookstore.model.Book;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import javax.xml.namespace.QName;

public class XMLFileExporter implements FileExporter {

    @Override
    public String exportData(Book object) {
        String xmlContent = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();

            QName qName = new QName("com.example.bookstore.model.Book", "book");
            JAXBElement<Book> root = new JAXBElement<>(qName, Book.class, object);

            jaxbMarshaller.marshal(root, sw);

            xmlContent = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlContent;
    }
}