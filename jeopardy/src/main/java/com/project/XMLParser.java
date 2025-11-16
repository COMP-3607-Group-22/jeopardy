package com.project;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class XMLParser implements ParserAdaptee{
        private ArrayList<Category> categories;

    public XMLParser(){
        this.categories = new ArrayList<>();
    }

    public ArrayList<Category> parse(String fileName){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            document.getDocumentElement().normalize();
            NodeList nodes = document.getElementsByTagName("QuestionItem");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element parsedInfo = (Element) node;
                    categories.add(init(new Category(), parsedInfo));
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.err.println(e.getMessage());
        }
        return categories;
    }

    public Category init(Category category, Element parsedInfo){
        category.setCategory(parsedInfo.getElementsByTagName("Category").item(0).getTextContent());
        category.setValue(Integer.parseInt(parsedInfo.getElementsByTagName("Value").item(0).getTextContent()));
        category.setQuestion(parsedInfo.getElementsByTagName("QuestionText").item(0).getTextContent());
        Element options = (Element) parsedInfo.getElementsByTagName("Options").item(0);
        category.setOptions(new ArrayList<>(Arrays.asList(
            options.getElementsByTagName("OptionA").item(0).getTextContent(),
            options.getElementsByTagName("OptionB").item(0).getTextContent(),
            options.getElementsByTagName("OptionC").item(0).getTextContent(),
            options.getElementsByTagName("OptionD").item(0).getTextContent()
        )));
        category.setAnswer(parsedInfo.getElementsByTagName("Category").item(0).getTextContent());
        return category;
    }
}
