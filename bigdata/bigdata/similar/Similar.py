import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from ast import literal_eval
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from . import dao
from django.db import connection
from .models import Recipe
from .models import RecipeIngredients
from django.core import serializers
from django.http import HttpResponse, JsonResponse
import json

m = 1
C = 1
# 해당 유저 알레르기에 포함되는 재료id 리스트


def allergy_list(user_id):
    allergy_small_id_list = []
    cursor = connection.cursor()
    cursor.execute(
        'SELECT a.user_id,b.small_id FROM allergy_group a, allergy_food b where a.allergy_id=b.allergy_id')
    for c in cursor:
        if(c[0] == user_id):
            allergy_small_id_list.append(c[1])
    return allergy_small_id_list

# 레시피 리스트중 알레르기 재료 있는 레시피 제거하기


def allergy_remove(recommend_list, small_id_list):
    remove_result = []

    for r in recommend_list.title:
        flag = True
        # 레시피 이름에 해당하는 recipe찾기
        recipe = Recipe.objects.filter(recipe_title=r)
        # recipe에 해당하는 recipeIngredients찾기
        recipeIngredients = RecipeIngredients.objects.filter(recipe=recipe[0])
        # recipe에 해당하는 재료와 알레르기 포함 재료 비교하기
        for a in recipeIngredients:
            for b in small_id_list:
                # 레시피 재료와 알레르기 재료가 같다면
                if(b == a.small_id):
                    print("알레르기")
                    print(b)
                    flag = False
                    break
            if(flag == False):
                break
        # 알레르기 재료가 포함되지 않았을 때 list 넣기
        if(flag == True):
            recipe_dict = {
                "recipe_id": recipe[0].recipe_id,
                "recipe_title": recipe[0].recipe_title,
                "recipe_created": recipe[0].recipe_created,
                "recipe_image": recipe[0].recipe_image,
                "recipe_context": recipe[0].recipe_context,
                "recipe_ingredient": recipe[0].recipe_ingredient,
                "recipe_time": recipe[0].recipe_time,
                "recipe_main_image": recipe[0].recipe_main_image,
                "recipe_count": recipe[0].recipe_count,
                "recipe_sub_id": recipe[0].recipe_sub_id,
            }
            remove_result.append(recipe_dict)
    return remove_result


def weighted_rating(x):
    v = x['vote_count']
    R = x['vote_average']
    return (v / (v+m) * R) + (m / (m + v) * C)


def get_recommend_movie_list(df, gerne_c_sim, keyword_c_sim, movie_title, top=30):
    # 특정 영화와 비슷한 영화를 추천해야 하기 때문에 '특정 영화' 정보를 뽑아낸다.
    target_movie_index = df[df['title'] == movie_title].index.values

    # 코사인 유사도 중 비슷한 코사인 유사도를 가진 정보를 뽑아낸다.
    sim_index = gerne_c_sim[target_movie_index, :top].reshape(-1)
    sim_index_keyword = keyword_c_sim[target_movie_index, :top].reshape(-1)
    # 본인을 제외
    sim_index = sim_index[sim_index != target_movie_index]
    sim_index_keyword = sim_index_keyword[sim_index_keyword !=
                                          target_movie_index]
    # data frame으로 만들고 vote_count으로 정렬한 뒤 return
    result = df.iloc[sim_index].sort_values('score', ascending=False)[:5]
    result2 = df.iloc[sim_index_keyword].sort_values(
        'score', ascending=False)[:5]

    result = pd.concat([result2, result])
    return result


def similar_recommend(title, userid):

    data = dao.recipe_test()
    # data = pd.read_csv('C:/Users/multicampus/Desktop/real_recipe.csv',encoding='cp949')

    data = data[['id', 'genres', 'vote_average',
                 'vote_count', 'title',  'keywords']]

    global m
    m = data['vote_count'].quantile(0.9)

    global C
    C = data['vote_average'].mean()

    data['score'] = data.apply(weighted_rating, axis=1)

    # data.to_csv('C:/Users/multicampus/Desktop/real_recipe_new.csv', index = False,encoding='utf-8-sig')
    count_vector = CountVectorizer(ngram_range=(1, 3))

    c_vector_genres = count_vector.fit_transform(data['genres'])
    c_vector_keywords = count_vector.fit_transform(data['keywords'])

    # 코사인 유사도를 구한 벡터를 미리 저장
    gerne_c_sim = cosine_similarity(
        c_vector_genres, c_vector_genres).argsort()[:, ::-1]
    keyword_c_sim = cosine_similarity(
        c_vector_keywords, c_vector_keywords).argsort()[:, ::-1]

    result = get_recommend_movie_list(
        data, gerne_c_sim, keyword_c_sim, movie_title=title)

    small_id_list = allergy_list(userid)
    # print('알레르기 리스트')
    # print(small_id_list)

    real_result = allergy_remove(result, small_id_list)

    print(data[data['title'] == title])
    print(real_result)

    return real_result
