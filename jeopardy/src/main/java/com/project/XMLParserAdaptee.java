package com.project;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParserAdaptee implements ParserAdaptee{
    private ArrayList<Question> questions;

    public XMLParserAdaptee(){
        this.questions = new ArrayList<>();
    }

    @Override
    public ArrayList<Question> parse(String fileName){
        questions.clear(); // Clear previous data
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
                    try {
                        QuestionBuilder questionBuilder = QuestionBuilder.create();
                        
                        if (parsedInfo.getElementsByTagName("Category").getLength() > 0) {
                            questionBuilder.setCategory(parsedInfo.getElementsByTagName("Category").item(0).getTextContent());
                        }
                        if (parsedInfo.getElementsByTagName("Value").getLength() > 0) {
                            questionBuilder.setValue(Integer.parseInt(parsedInfo.getElementsByTagName("Value").item(0).getTextContent()));
                        }
                        if (parsedInfo.getElementsByTagName("QuestionText").getLength() > 0) {
                            questionBuilder.setQuestion(parsedInfo.getElementsByTagName("QuestionText").item(0).getTextContent());
                        }

                        if (parsedInfo.getElementsByTagName("Options").getLength() > 0) {
                            Element options = (Element) parsedInfo.getElementsByTagName("Options").item(0);
                            String optA = options.getElementsByTagName("OptionA").getLength() > 0 ? 
                                options.getElementsByTagName("OptionA").item(0).getTextContent() : "";
                            String optB = options.getElementsByTagName("OptionB").getLength() > 0 ? 
                                options.getElementsByTagName("OptionB").item(0).getTextContent() : "";
                            String optC = options.getElementsByTagName("OptionC").getLength() > 0 ? 
                                options.getElementsByTagName("OptionC").item(0).getTextContent() : "";
                            String optD = options.getElementsByTagName("OptionD").getLength() > 0 ? 
                                options.getElementsByTagName("OptionD").item(0).getTextContent() : "";
                            questionBuilder.setOptions(new ArrayList<>(Arrays.asList(optA, optB, optC, optD)));
                        }
                        if (parsedInfo.getElementsByTagName("CorrectAnswer").getLength() > 0) {
                            questionBuilder.setAnswer(parsedInfo.getElementsByTagName("CorrectAnswer").item(0).getTextContent());
                        }
                        questions.add(questionBuilder.build());

                    } catch (NullPointerException | NumberFormatException e) {
                        System.err.println("Not expected XML element: " + e.getMessage());
                    } catch (IllegalStateException e) {
                        System.err.println("Builder validation failed: " + e.getMessage());
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.err.println("Error reading XML file: " + e.getMessage());
        }
        return questions;
    }
}
