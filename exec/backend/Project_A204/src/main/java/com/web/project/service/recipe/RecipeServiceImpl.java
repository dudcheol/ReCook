package com.web.project.service.recipe;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.hashtag.HashtagDao;
import com.web.project.dao.ingredients.IngredientsSmallDao;
import com.web.project.dao.recipe.RecipeDao;
import com.web.project.dao.recipe.RecipeHashtagDao;
import com.web.project.dao.recipe.RecipeIngredientsDao;
import com.web.project.dao.survey.AllergyFoodDao;
import com.web.project.dao.survey.AllergyGroupDao;
import com.web.project.model.hashtag.Hashtag;
import com.web.project.model.ingredients.IngredientsRequest;
import com.web.project.model.ingredients.IngredientsSmall;
import com.web.project.model.recipe.Recipe;
import com.web.project.model.recipe.RecipeHashtag;
import com.web.project.model.recipe.RecipeIngredients;
import com.web.project.model.survey.AllergyFood;
import com.web.project.model.survey.AllergyGroup;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDao recipeDao;

	@Autowired
	private IngredientsSmallDao ingredientsSmallDao;

	@Autowired
	private RecipeIngredientsDao recipeIngredientsDao;

	@Autowired
	private HashtagDao hashtagDao;

	@Autowired
	private AllergyGroupDao allergyGroupDao;

	@Autowired
	private AllergyFoodDao allergyFoodDao;

	@Autowired
	private RecipeHashtagDao recipeHashtagDao;

	public static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

	@Override
	public void read() {

		JSONParser parser = new JSONParser();

		try {
			// JSON 파일 이름
			String fileName = "recipe.json";

			// JSON 파일 주소 => 각 Local PC마다 경로 다름
			String fileLoc = "C:\\BigData_PJT\\backend\\Project_A204\\src\\main\\resources\\json\\" + fileName;

			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileLoc));

			// "recipe_list" key 값으로 모든 recipe 정보 가져옴
			JSONArray recipeList = (JSONArray) jsonObject.get("recipe_list");
			for (int i = 0; i < recipeList.size(); i++) {

				// recipe 객체 생성
				Recipe recipe = new Recipe();

				JSONObject result = (JSONObject) recipeList.get(i);

//				System.out.println("#" + i + " Recipe Read");

				Recipe skipRecipe = recipeDao.findRecipeByRecipeTitle((String) result.get("recipe_title"));

				// 이미 들어가 있는 데이터는 제외
				if (skipRecipe == null) {
					// 레시피 제목
					String recipeTitle = (String) result.get("recipe_title");
					recipe.setRecipeTitle(recipeTitle);
//					System.out.println("recipeTitle : " + recipeTitle);

					// 레시피의 조리 시간 ( x인분 )
					String recipeTime = (String) result.get("recipe_time");
					recipe.setRecipeTime(recipeTime);
//					System.out.println("recipeTime : " + recipeTime);

					// 레시피 재료 총 목록 + 수량 포함
					String recipeIngredientString = (String) result.get("recipe_ingredient_string");
					recipe.setRecipeIngredient(recipeIngredientString);
//					System.out.println("recipeIngredientString : " + recipeIngredientString);

					// 레시피 내용
					String recipeContext = (String) result.get("recipe_context");
					recipe.setRecipeContext(recipeContext);
//					System.out.println("recipeContext : " + recipeContext);

					// 레시피 이미지
					String recipeImage = (String) result.get("recipe_image");
					recipe.setRecipeImage(recipeImage);
//					System.out.println("recipeImage : " + recipeImage);

					// 레시피 메인 이미지
					StringTokenizer st = new StringTokenizer(recipeImage, "####");
					String mainImage = st.nextToken();
					recipe.setRecipeMainImage(mainImage);

					// recipe DB에 저장
//					System.out.print("recipe : ");
					Recipe resultRecipe = recipeDao.save(recipe);
//					System.out.println(resultRecipe);

					// 레시피 재료 => Recipe_Ingredients 테이블로
					JSONArray recipeIngredientList = (JSONArray) result.get("recipe_ingredient");
//					System.out.print("recipeIngredientList : ");
//					System.out.println(recipeIngredientList);

					// 각 리스트에 있는 재료를 소분류에서 찾아서 저장
					// 이때, 소분류 없는건 스킵하고 ()가 있는건 앞에거로 찾아보기
					for (int j = 0; j < recipeIngredientList.size(); j++) {
						String ingredient = (String) recipeIngredientList.get(j);

						// () 있는지 검사 => ( 기준으로 잘라내기
						if (ingredient.contains("(")) {
							st = new StringTokenizer(ingredient, " (");
							ingredient = st.nextToken();
						}

						IngredientsSmall small = ingredientsSmallDao.findIngredientsSmallBySmallName(ingredient);

						RecipeIngredients recipeIngredients = new RecipeIngredients();
						if (small == null) { // 데이터가 없다면 생성!
							IngredientsSmall ingredientsSmall = new IngredientsSmall();
							ingredientsSmall.setMidId(120); // 120번이 기타이기 때문에 그냥 이렇게 넣음 ㅎ
							ingredientsSmall.setSmallName(ingredient);
							small = ingredientsSmallDao.save(ingredientsSmall);
//							System.out.println(small);
						}
						recipeIngredients.setRecipeId(resultRecipe.getRecipeId());
						recipeIngredients.setSmallId(small.getSmallId());
						recipeIngredientsDao.save(recipeIngredients);
					}

					// 레시피에 연결된 해시태그 -> hashtag 테이블과 recipe_hashtag 테이블로
					JSONArray recipeHashtagList = (JSONArray) result.get("recipe_hashtag");
//					System.out.print("recipeHashtagList : ");
//					System.out.println(recipeHashtagList);

					for (int j = 0; j < recipeHashtagList.size(); j++) {
						String recipehashtag = (String) recipeHashtagList.get(j);

						// 1. 해당 해시태그가 테이블에 존재하는지 확인
						Hashtag hashtagOpt = hashtagDao.findHashtagByHashtagName(recipehashtag);

						Hashtag resultHashtag = null;
						if (hashtagOpt != null) {
							// 2. 있으면 해시태그 count + 1
							hashtagOpt.setHashtagCount(hashtagOpt.getHashtagCount() + 1);
							resultHashtag = hashtagDao.save(hashtagOpt);
						} else {
							// 3. 없으면 해시태그 데이터 추가
							Hashtag hashtag = new Hashtag();
							hashtag.setHashtagCount(1);
							hashtag.setHashtagName(recipehashtag);
							resultHashtag = hashtagDao.save(hashtag);
						}

						// 4. 레시피랑 해시태그랑 연결
						RecipeHashtag recipeHashtag = new RecipeHashtag();
						recipeHashtag.setHashtagId(resultHashtag.getHashtagId());
						recipeHashtag.setRecipeId(resultRecipe.getRecipeId());
						recipeHashtagDao.save(recipeHashtag);
					}
				}
//				System.out.println("===============================");
			}
		} catch (Exception e) {
			logger.error("JSON File 읽어오기 실패 : {}", e);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> showRecipeByRecipeId(int recipeId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		Recipe recipe = recipeDao.findRecipeByRecipeId(recipeId);

		try {
			if (recipe != null) {
				// 레시피 아이디
				resultMap.put("recipe-id", recipe.getRecipeId());
				// 레시피 제목
				resultMap.put("recipe-title", recipe.getRecipeTitle());
				// 레시피 생성 날짜
				resultMap.put("recipe-created", recipe.getRecipeCreated());
				// 레시피 이미지
				resultMap.put("recipe-image", recipe.getRecipeImage());
				// 레시피 내용
				resultMap.put("recipe-context", recipe.getRecipeContext());
				// 레시피 재료
				resultMap.put("recipe-ingredient", recipe.getRecipeIngredient());
				StringTokenizer st = new StringTokenizer(recipe.getRecipeTime(), "\n");
				// 레시피 시간
				resultMap.put("recipe-time", st.nextToken());
				// 레시피 인분
				resultMap.put("recipe-person", st.nextToken());
				// 레시피 메인 사진
				resultMap.put("recipe-main-image", recipe.getRecipeMainImage());
				// 레시피 서브 ID
				resultMap.put("recipe-sub-id", recipe.getRecipeSubId());
				resultMap.put("recipe-count", recipe.getRecipeCount());

				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "해당 ID의 레시피 데이터가 없음");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (RuntimeException e) {
			logger.error("레시피 정보 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> showRecipeByRecipeSubId(int recipeSubId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		Recipe recipe = recipeDao.findRecipeByRecipeSubId(recipeSubId);

		try {
			if (recipe != null) {
				// 레시피 아이디
				resultMap.put("recipe-id", recipe.getRecipeId());
				// 레시피 제목
				resultMap.put("recipe-title", recipe.getRecipeTitle());
				// 레시피 생성 날짜
				resultMap.put("recipe-created", recipe.getRecipeCreated());
				// 레시피 이미지
				resultMap.put("recipe-image", recipe.getRecipeImage());
				// 레시피 내용
				resultMap.put("recipe-context", recipe.getRecipeContext());
				// 레시피 재료
				resultMap.put("recipe-ingredient", recipe.getRecipeIngredient());
				StringTokenizer st = new StringTokenizer(recipe.getRecipeTime(), "\n");
				// 레시피 시간
				resultMap.put("recipe-time", st.nextToken());
				// 레시피 인분
				resultMap.put("recipe-person", st.nextToken());
				// 레시피 메인 사진
				resultMap.put("recipe-main-image", recipe.getRecipeMainImage());
				// 레시피 서브 ID
				resultMap.put("recipe-sub-id", recipe.getRecipeSubId());
				resultMap.put("recipe-count", recipe.getRecipeCount());

				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "해당 ID의 레시피 데이터가 없음");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (RuntimeException e) {
			logger.error("레시피 정보 조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Override
	public ResponseEntity<List<Map<String, Object>>> newRecipeList() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		HttpStatus status = HttpStatus.OK;

		List<Recipe> newList = recipeDao.findTop10ByOrderByRecipeIdDesc();

		try {
			if (newList != null) {
				for (int i = 0; i < newList.size(); i++) {
					Recipe recipe = newList.get(i);

					Map<String, Object> resultMap = new HashMap<>();

					// 레시피 아이디
					resultMap.put("recipe-id", recipe.getRecipeId());
					// 레시피 제목
					resultMap.put("recipe-title", recipe.getRecipeTitle());
					// 레시피 생성 날짜
					resultMap.put("recipe-created", recipe.getRecipeCreated());
					// 레시피 이미지
					resultMap.put("recipe-image", recipe.getRecipeImage());
					// 레시피 내용
					resultMap.put("recipe-context", recipe.getRecipeContext());
					// 레시피 재료
					resultMap.put("recipe-ingredient", recipe.getRecipeIngredient());
					StringTokenizer st = new StringTokenizer(recipe.getRecipeTime(), "\n");
					// 레시피 시간
					resultMap.put("recipe-time", st.nextToken());
					// 레시피 인분
					resultMap.put("recipe-person", st.nextToken());
					// 레시피 메인 사진
					resultMap.put("recipe-main-image", recipe.getRecipeMainImage());

					resultList.add(resultMap);
				}
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (RuntimeException e) {
			logger.error("레시피 정보 조회 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<Map<String, Object>>>(resultList, status);
	}

	@Override
	public ResponseEntity<Page<Recipe>> newRecipeListAll(Pageable pageable) {
		return new ResponseEntity<Page<Recipe>> (recipeDao.findAllByOrderByRecipeIdDesc(pageable), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<Map<String, Object>>> hotRecipeList() {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		HttpStatus status = HttpStatus.OK;

		List<Recipe> hotList = recipeDao.findTop10ByOrderByRecipeCountDesc();

		try {
			if (hotList != null) {
				for (int i = 0; i < hotList.size(); i++) {
					Recipe recipe = hotList.get(i);

					Map<String, Object> resultMap = new HashMap<>();

					// 레시피 아이디
					resultMap.put("recipe-id", recipe.getRecipeId());
					// 레시피 제목
					resultMap.put("recipe-title", recipe.getRecipeTitle());
					// 레시피 생성 날짜
					resultMap.put("recipe-created", recipe.getRecipeCreated());
					// 레시피 이미지
					resultMap.put("recipe-image", recipe.getRecipeImage());
					// 레시피 내용
					resultMap.put("recipe-context", recipe.getRecipeContext());
					// 레시피 재료
					resultMap.put("recipe-ingredient", recipe.getRecipeIngredient());
					StringTokenizer st = new StringTokenizer(recipe.getRecipeTime(), "\n");
					// 레시피 시간
					resultMap.put("recipe-time", st.nextToken());
					// 레시피 인분
					resultMap.put("recipe-person", st.nextToken());
					// 레시피 메인 사진
					resultMap.put("recipe-main-image", recipe.getRecipeMainImage());

					resultList.add(resultMap);
				}
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (RuntimeException e) {
			logger.error("레시피 정보 조회 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<Map<String, Object>>>(resultList, status);
	}
	
	@Override
	public ResponseEntity<Page<Recipe>> hotRecipeListAll(Pageable pageable) {
		return new ResponseEntity<Page<Recipe>> (recipeDao.findAllByOrderByRecipeCountDesc(pageable), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> recipeIngredients(int recipeId) {
		List<String> resultList = new ArrayList<String>();
		HttpStatus status = null;

		List<RecipeIngredients> recipeIngredientsList = recipeIngredientsDao.findAllByRecipeId(recipeId);

		try {
			for (int i = 0; i < recipeIngredientsList.size(); i++) {
				resultList.add(ingredientsSmallDao
						.findIngredientsSmallBySmallId(recipeIngredientsList.get(i).getSmallId()).getSmallName());
			}

			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("재료 찾기 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<String>>(resultList, status);
	}

	@Override
	public ResponseEntity<List<Recipe>> selectIngredinets(IngredientsRequest ingredientsRequest, Pageable pageable) {
		List<Recipe> resultList = new ArrayList<Recipe>();
		HttpStatus status = null;

		int ingredientsSize = ingredientsRequest.getIngredientList().size();

		try {
			// 재료의 개수에 따라 레시피 가져오기

			Page<Recipe> recipeList = null;

			if (ingredientsSize == 1) {
				recipeList = recipeDao.findRecipeWithIngredientOne(ingredientsRequest.getIngredientList().get(0),
						pageable);
			} else if (ingredientsSize == 2) {
				recipeList = recipeDao.findRecipeWithIngredientTwo(ingredientsRequest.getIngredientList().get(0),
						ingredientsRequest.getIngredientList().get(1), pageable);
			} else if (ingredientsSize == 3) {
				recipeList = recipeDao.findRecipeWithIngredientThree(ingredientsRequest.getIngredientList().get(0),
						ingredientsRequest.getIngredientList().get(1), ingredientsRequest.getIngredientList().get(2),
						pageable);
			} else {
				return new ResponseEntity<List<Recipe>>(resultList, HttpStatus.BAD_REQUEST);
			}

			Iterator<Recipe> iterator = recipeList.iterator();
			while (iterator.hasNext()) {
				resultList.add(iterator.next());
			}

			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("재료로 레시피 찾기 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<Recipe>>(resultList, status);
	}

	@Override
	public ResponseEntity<List<Recipe>> selectIngredinetsWithAllergy(IngredientsRequest ingredientsRequest,
			Pageable pageable) {
		List<Recipe> resultList = new ArrayList<Recipe>();
		HttpStatus status = null;

		int ingredientsSize = ingredientsRequest.getIngredientList().size();

		try {
			// 재료의 개수에 따라 레시피 가져오기

			Page<Recipe> recipeList = null;

			if (ingredientsSize == 1) {
				recipeList = recipeDao.findRecipeWithIngredientOne(ingredientsRequest.getIngredientList().get(0),
						pageable);
			} else if (ingredientsSize == 2) {
				recipeList = recipeDao.findRecipeWithIngredientTwo(ingredientsRequest.getIngredientList().get(0),
						ingredientsRequest.getIngredientList().get(1), pageable);
			} else if (ingredientsSize == 3) {
				recipeList = recipeDao.findRecipeWithIngredientThree(ingredientsRequest.getIngredientList().get(0),
						ingredientsRequest.getIngredientList().get(1), ingredientsRequest.getIngredientList().get(2),
						pageable);
			} else {
				return new ResponseEntity<List<Recipe>>(resultList, HttpStatus.BAD_REQUEST);
			}

			// 가져온 레시피에서 사용자의 알레르기 종류에 따라 재료가 들어가 있는 경우 제외시키기
			// 1. ID로 알레르기 정보 가져오기
			List<AllergyGroup> allergyGroupList = allergyGroupDao.findAllByUserId(ingredientsRequest.getUserId());

			List<String> allergyIngredientList = new ArrayList<String>();

			// 2. 소분류 재료 저장
			for (int i = 0; i < allergyGroupList.size(); i++) {
				List<AllergyFood> allergyFoodList = allergyFoodDao
						.findAllByAllergyId(allergyGroupList.get(i).getAllergyId());

				for (int j = 0; j < allergyFoodList.size(); j++) {
					allergyIngredientList.add(ingredientsSmallDao
							.findIngredientsSmallBySmallId(allergyFoodList.get(j).getSmallId()).getSmallName());
				}
			}

			Iterator<Recipe> iterator = recipeList.iterator();
			O: while (iterator.hasNext()) {
				Recipe recipe = iterator.next();
				
				for (int i = 0; i < allergyIngredientList.size(); i++) {
					if(recipe.getRecipeIngredient().contains(allergyIngredientList.get(i))) {
						continue O;
					}
				}
				
				resultList.add(recipe);
			}

			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("재료로 레시피 찾기 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<Recipe>>(resultList, status);
	}

	@Override
	public ResponseEntity<Page<Recipe>> allRecipeByTitle(String title, Pageable pageable) {		
		return new ResponseEntity<Page<Recipe>> (recipeDao.findAllByTitle(title, pageable), HttpStatus.OK);
	}

}
