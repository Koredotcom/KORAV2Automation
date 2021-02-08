package com.org.kore.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PropertyLoader {
	public static String getAppProperties(String key) throws IOException {
		String value = "";
		try {
			/*
			 * System.out.println("=================" +
			 * System.getProperty("app.prop") + "================="); String
			 * fileValue = "PropertiesFiles/" + System.getProperty("app.prop");
			 */
			FileInputStream fileInputStream = new FileInputStream("PropertiesFiles/data.properties");
			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	public static LinkedHashMap<String, String> jsonRead(String App) throws FileNotFoundException, IOException {
		LinkedHashMap<String, String> props = new LinkedHashMap<>();
		try {

			ObjectMapper objectMapper = new ObjectMapper();

			Map<String, Object> map = objectMapper.readValue(new File("PropertiesFiles/data.json"),
					new TypeReference<Map<String, Object>>() {
					});
			props = (LinkedHashMap<String, String>) map.get(App);

			for (String key : props.keySet()) {
				// System.out.println(key + ":" + props.get(key));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return props;
	}

}