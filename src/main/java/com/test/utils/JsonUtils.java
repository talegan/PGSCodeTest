package com.test.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonIOException;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;
import gherkin.deps.com.google.gson.JsonSyntaxException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {


	/**
	 * Convert a Java String with json formatting into a google.gson JsonObject
	 * 
	 * @param json
	 *            {@link String} Java String in Json format.
	 * @return {@link JsonObject}
	 * @author Nikhil Talegaonkar <br>
	 *         Date Created: Sep 30, 2018
	 */
	public static JsonObject convertStringToJson(String json) {
		JsonParser parse = new JsonParser();
		return (JsonObject) parse.parse(json);
	}

	/**
	 * Reads a json file from path given as an argument and converts it into
	 * JsonObject
	 * 
	 * @param filepath
	 *            {@link String} Path to the Json file
	 * @return {@link JsonObject}
	 * @author Nikhil Talegaonkar <br>
	 *         Date Created: Sep 30, 2018
	 */
	public JsonObject convertFileToJson(String filepath) {
		JsonParser parse = new JsonParser();
		JsonObject json = null;
		try {
			json = (JsonObject) parse.parse(new FileReader(filepath));
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * This method converts a HashMap of strings to a Pretty Printed Json
	 * String.
	 * 
	 * @param map
	 *            {@link HashMap} The HashMap of Strings that need to be
	 *            converted to Json
	 * 
	 * @return {@link String} Pretty printed Json String
	 */
	public String convertHashMapToJsonString(HashMap<String, String> map) {
		JsonObject json = new JsonObject();
		String jString = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			json = convertStringToJson(new ObjectMapper()
					.writeValueAsString(map));
			jString = gson.toJson(json);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jString;
	}
	
	
	/**
	 * Method to convert a HashMap into a Json file.
	 * 
	 * @param json {@link JsonObject} The JsonObject that needs to be converted into HashMap
	 * @return {@link HashMap} Converted Hashmap is returned.
	 */
	@SuppressWarnings({"unchecked" })
	public static HashMap<String, String> convertJsonToHashMap (JsonObject json){
		return new Gson().fromJson(json, HashMap.class);
	}

}
