package com.web.project.service.recipe;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class RecipeServiceImpl implements RecipeService{

	public static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	@Override
	public void read() {
		
		JSONParser parser = new JSONParser();
		
		try {
			// JSON 파일 이름
			String fileName = "recipesample.json";
			
			// JSON 파일 주소 => 각 Local PC마다 경로 다름
			String fileLoc = "C:\\BigData_PJT\\backend\\Project_A204\\src\\main\\resources\\json\\" + fileName;
			
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileLoc));
			
			// "recipe_list" key 값으로 모든 recipe 정보 가져옴
			JSONArray recipeList = (JSONArray) jsonObject.get("recipe_list");
			for (int i = 0; i < recipeList.size(); i++) {
				JSONObject result = (JSONObject) recipeList.get(i);
				
				
			}
			
			
		} catch (Exception e) {
			logger.error("JSON File 읽어오기 실패 : {}", e);
		}
		
	}

}
