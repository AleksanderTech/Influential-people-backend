package com.alek.influentialpeople;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationDemo {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, FileNotFoundException, IOException {

		String carpetString = "{ \"value\" : 2, \"material\" : \"gold\",\"lengthInCm\":250,\"widthInCm\":180 }";
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		
		

		try {
			Carpet carpet = mapper.readerWithView(Views.Public.class).forType(Carpet.class).readValue(carpetString);
			Map<String, Object> jsonMap = mapper.readValue(carpetString, new TypeReference<Map<String, Object>>() {
			});
			System.out.println(jsonMap.entrySet());
			System.out.println(carpet);

		} catch (IOException e) {
			throw new RuntimeException("Json data are invalid", e);
		}

		Carpet carpet2 = new Carpet(200, "silver", 200, 190);

		String carpet2String=mapper.writerWithView(Views.Public.class).writeValueAsString(carpet2);
		System.out.println(carpet2String);

	}

}
