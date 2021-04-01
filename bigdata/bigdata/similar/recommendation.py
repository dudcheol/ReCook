import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import csv
import json
from ast import literal_eval
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from pandas import Series, DataFrame

from rest_framework.decorators import api_view
from rest_framework.response import Response

from django.db import models
from django.db import connection

from .models import Recipe
from .models import RecipeIngredients

# movies데이터 갖고오기


def recipe_list():
    recipe_list = {}
    recipe_list['recipeId'] = []
    recipe_list['title'] = []
    recipe_list['genres'] = []

    cursor = connection.cursor()
    cursor.execute('SELECT a.recipe_sub_id,a.recipe_title,group_concat(distinct c.hashtag_name ORDER BY c.hashtag_id separator "|") as hashtag_name FROM recipe a, recipe_hashtag b,hashtag c where a.recipe_id=b.recipe_id and b.hashtag_id=c.hashtag_id group by recipe_title')

    for c in cursor:
        recipe_list['recipeId'].append(c[0])
        recipe_list['title'].append(c[1])
        recipe_list['genres'].append(c[2])

    return DataFrame(recipe_list)

# rating데이터 갖고오기


def rating_list():
    rating_list = {}
    rating_list['userId'] = []
    rating_list['recipeId'] = []
    rating_list['rating'] = []

    cursor = connection.cursor()
    cursor.execute(
        'SELECT a.user_id,a.recipe_sub_id, a.review_rating FROM review a')

    for c in cursor:
        rating_list['userId'].append(c[0])
        rating_list['recipeId'].append(c[1])
        rating_list['rating'].append(c[2])

    return DataFrame(rating_list)


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
    for r in recommend_list.index:
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

# Collaborative Filtering


def recommend(title, user_id):
    recipe_data = recipe_list()
    rating_data = rating_list()

    print(rating_data)

    user_recipe_rating = pd.merge(rating_data, recipe_data, on='recipeId')

    recipe_user_rating = user_recipe_rating.pivot_table(
        'rating', index='title', columns='userId')
    user_recipe_rating = user_recipe_rating.pivot_table(
        'rating', index='userId', columns='title')

    recipe_user_rating.fillna(0, inplace=True)

    item_based_collabor = cosine_similarity(recipe_user_rating)

    print(recipe_user_rating)

    item_based_collabor = pd.DataFrame(
        data=item_based_collabor, index=recipe_user_rating.index, columns=recipe_user_rating.index)

    print(item_based_collabor)

    result = item_based_collabor[title].sort_values(ascending=False)[1:11]

    print(result)

    # 유저 알레르기에 포함되는 재료 뽑기
    small_id_list = allergy_list(user_id)
    print(small_id_list)

    # 레시피중 알러지 포함 레시피 제거하기
    real_result = allergy_remove(result, small_id_list)

    return real_result
