package com.test.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.test.props.IProps.StaticProps;

import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;

public class Locator {

    public HashMap<String, String> locatorData;
    private String elementClassName;
    private String elementName;
    private static String SIMPLENAME = "SimpleName";
    private static String LOCATOR = "Locator";
    private static String ELEMENT_FILE_PATH = System.getProperty("user.dir")+"/src/test/resources/elements/";
    
    public Locator() {
        locatorData = new HashMap<>();
    }
    
    private JsonObject getElementJSON() {
        String elementFileName = ELEMENT_FILE_PATH + elementClassName + ".json";
        
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = null;
        try {
            jsonObject = (JsonObject) parser.parse(new FileReader(elementFileName));
        } catch (FileNotFoundException e) {
            System.out.println("No Element File found.");
        } catch (IOException e) {
        	System.out.println("Failed to query Class Element File '" + elementClassName + "' "
                    + "with element '" + elementName + "'.");
        } catch (ParseException e) {
        	System.out.println("Failed to query Class Element File '" + elementClassName + "' "
                    + "with element '" + elementName + "'.");
        }
        return (JsonObject) jsonObject.get(elementName);
    }
    
    private By parseLocator(String inText) {
        String locatorString = "";
        String simpleName = "";
        By locator = null;
        try {
        	JsonObject elementObject = getElementJSON();
        	simpleName =  elementObject.get(SIMPLENAME).toString();
        	locatorString = elementObject.get(LOCATOR).toString();
        } catch (Exception e) {
        	System.out.println("Error on JSON query.");
        }
        
        String locatorArr[] = locatorString.split("::");
        String locatorType = locatorArr[0];
        String locatorDef = locatorArr[1];
        
        if (inText != null) {
            if (locatorDef.contains(StaticProps.LOCATOR_VARIABLE_UPPER)) {
                locatorDef = locatorDef.replace(StaticProps.LOCATOR_VARIABLE_UPPER, inText.toUpperCase());
            } else if (locatorDef.contains(StaticProps.LOCATOR_VARIABLE_LOWER)) {
                locatorDef = locatorDef.replace(StaticProps.LOCATOR_VARIABLE_LOWER, inText.toLowerCase());
            } else {
                locatorDef = locatorDef.replace(StaticProps.LOCATOR_VARIABLE, inText);
            }
            
            locatorData.put(SIMPLENAME, simpleName.replace(StaticProps.LOCATOR_VARIABLE, inText));
        } else {
            locatorData.put(SIMPLENAME, simpleName);
        }
        
        if (locatorType.equalsIgnoreCase(StaticProps.LOCATOR_XPATH)) {
            locator = By.xpath(locatorDef);
        } else if (locatorType.equalsIgnoreCase(StaticProps.LOCATOR_CSS)) {
        	locator = By.cssSelector(locatorDef);
        } else if (locatorType.equalsIgnoreCase(StaticProps.LOCATOR_ID)) {
        	locator = By.id(locatorDef);
        } else if (locatorType.equalsIgnoreCase(StaticProps.LOCATOR_NAME)){
        	locator = By.name(locatorDef);
        } else if (locatorType.equalsIgnoreCase(StaticProps.LOCATOR_LINK_TEXT)) {
        	locator = By.linkText(locatorDef);
        } else if (locatorType.equalsIgnoreCase(StaticProps.LOCATOR_CLASS_NAME)) {
        	locator = By.className(locatorDef);
        }
        
        if (locator == null) {
            Assert.fail("No matching locator found for Class Element '" + elementClassName + "' "
                    + "with element '" + elementName + "'.");
        }
        return locator;
    }
    
    public By getLocator() {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        int startingIndex = className.lastIndexOf(".");
        elementClassName = className.substring(startingIndex, className.length()).replace(".", "");
        elementName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return parseLocator(null);
    }
    
    public By getLocator(String inText) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        int startingIndex = className.lastIndexOf(".");
        elementClassName = className.substring(startingIndex, className.length()).replace(".", "");
        elementName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return parseLocator(inText);
    }
    
}