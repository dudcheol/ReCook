package com.web.project.service.recipe;

import java.io.FileReader;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.project.dao.recipe.RecipeDao;
import com.web.project.model.recipe.Recipe;


@Service
public class RecipeServiceImpl implements RecipeService{

	@Autowired
	private RecipeDao recipeDao;
	
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
				
				// recipe 객체 생성
				Recipe recipe = new Recipe();
				
				JSONObject result = (JSONObject) recipeList.get(i);
				
				System.out.println("#" + i + " Recipe Read");
				
				// 레시피 제목
				String recipeTitle = (String) result.get("recipe_title");
				recipe.setRecipeTitle(recipeTitle);
				System.out.println("recipeTitle : " + recipeTitle);
				
				// 레시피의 조리 시간  ( x인분 )
				String recipeTime = (String) result.get("recipe_time");
				recipe.setRecipeTime(recipeTime);
				System.out.println("recipeTime : " + recipeTime);
			
				// 레시피 재료 총 목록 + 수량 포함
				String recipeIngredientString = (String) result.get("recipe_ingredient_string");
				recipe.setRecipeIngredient(recipeIngredientString);
				System.out.println("recipeIngredientString : " + recipeIngredientString);
				
				// 레시피 내용
				String recipeContext = (String) result.get("recipe_context");
				recipe.setRecipeContext("hi");
//				recipe.setRecipeContext(recipeContext);
				System.out.println("recipeContext : " + recipeContext);
				
				// 레시피 이미지
				String recipeImage = (String) result.get("recipe_image");
				recipe.setRecipeImage("hi");
//				recipe.setRecipeImage(recipeImage);
				System.out.println("recipeImage : " + recipeImage);
				
				// 레시피 재료 => Recipe_Ingredients 테이블로
				JSONArray recipeIngredientList = (JSONArray) result.get("recipe_ingredient");
//				System.out.print("recipeIngredientList : ");
//				System.out.println(recipeIngredientList);
				
				// 레시피에 연결된 해시태그 -> hashtag 테이블과 recipe_hashtag 테이블로
				JSONArray recipeHashtagList = (JSONArray) result.get("recipe_hashtag");
//				System.out.print("recipeHashtagList : ");
//				System.out.println(recipeHashtagList);
				
				System.out.println("===============================");
				
				// recipe DB에 저장
				recipeDao.save(recipe);
			}
		} catch (Exception e) {
			logger.error("JSON File 읽어오기 실패 : {}", e);
		}
		
	}

}
