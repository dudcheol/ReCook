from .models import Review, Recipe, RecipeHashtag
from django.db import models
from django.db import connection
from pandas import Series, DataFrame


def review_test():
    review = Review.objects.all()

    for hi in review:
        print(hi.review_context)


def recipe_test():
    total_recipe = {}
    total_recipe['id'] = []
    total_recipe['title'] = []
    total_recipe['keywords'] = []
    total_recipe['genres'] = []
    total_recipe['vote_average'] = []
    total_recipe['vote_count'] = []

    cursor = connection.cursor()
    cursor.execute('select a.recipe_sub_id ,a.recipe_title,group_concat(distinct c.hashtag_name separator " ") as hashtag_name, group_concat(distinct e.small_name ORDER BY e.small_id separator " ") as ingredients ' +
                   'from recipe a, recipe_hashtag b,hashtag c, recipe_ingredients d, ingredients_small e ' +
                   'where a.recipe_id=b.recipe_id and b.hashtag_id=c.hashtag_id and a.recipe_id=d.recipe_id and d.small_id=e.small_id ' +
                   'group by a.recipe_sub_id ' +
                   'order by a.recipe_sub_id;')

    for c in cursor:
        total_recipe['id'].append(c[0])
        total_recipe['title'].append(c[1])
        total_recipe['keywords'].append(c[2])
        total_recipe['genres'].append(c[3])

    cursor = connection.cursor()
    cursor.execute('select avg(f.review_rating) as vote_average, count(f.review_id) ' +
                   'from recipe a inner join review f on a.recipe_id=f.recipe_id ' +
                   'group by a.recipe_sub_id ' +
                   'order by a.recipe_sub_id;')

    for c in cursor:
        total_recipe['vote_average'].append(c[0])
        total_recipe['vote_count'].append(c[1])

    # print(total_recipe)
    return DataFrame(total_recipe)
