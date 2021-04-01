# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Allergy(models.Model):
    allergy_id = models.AutoField(primary_key=True)
    allergy_name = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'allergy'


class AllergyFood(models.Model):
    allergy = models.ForeignKey(Allergy, models.DO_NOTHING, primary_key=True)
    small = models.ForeignKey('IngredientsSmall', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'allergy_food'
        unique_together = (('allergy', 'small'),)


class AllergyGroup(models.Model):
    allergy_group_id = models.AutoField(primary_key=True)
    allergy = models.ForeignKey(Allergy, models.DO_NOTHING)
    user = models.ForeignKey('User', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'allergy_group'
        unique_together = (('allergy_group_id', 'allergy', 'user'),)


class Food(models.Model):
    food_id = models.AutoField(primary_key=True)
    recipe = models.ForeignKey('Recipe', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'food'
        unique_together = (('food_id', 'recipe'),)


class FoodLike(models.Model):
    food_like_id = models.AutoField(primary_key=True)
    user = models.ForeignKey('User', models.DO_NOTHING)
    food = models.ForeignKey(Food, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'food_like'
        unique_together = (('food_like_id', 'user', 'food'),)


class Fridge(models.Model):
    fridge_id = models.AutoField(primary_key=True)
    user = models.ForeignKey('User', models.DO_NOTHING)
    small = models.ForeignKey('IngredientsSmall', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'fridge'
        unique_together = (('fridge_id', 'user', 'small'),)


class Hashtag(models.Model):
    hashtag_id = models.AutoField(primary_key=True)
    hashtag_name = models.CharField(max_length=45)
    hashtag_count = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'hashtag'


class IngredientsBig(models.Model):
    big_id = models.AutoField(primary_key=True)
    big_name = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'ingredients_big'


class IngredientsMiddle(models.Model):
    mid_id = models.AutoField(primary_key=True)
    big = models.ForeignKey(IngredientsBig, models.DO_NOTHING)
    middle_name = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'ingredients_middle'
        unique_together = (('mid_id', 'big'),)


class IngredientsSmall(models.Model):
    small_id = models.AutoField(primary_key=True)
    mid = models.ForeignKey(IngredientsMiddle, models.DO_NOTHING)
    small_name = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'ingredients_small'
        unique_together = (('small_id', 'mid'),)


class Recipe(models.Model):
    recipe_id = models.AutoField(primary_key=True)
    recipe_title = models.CharField(max_length=100)
    recipe_created = models.DateTimeField()
    recipe_image = models.TextField()
    recipe_context = models.TextField()
    recipe_ingredient = models.TextField()
    recipe_time = models.CharField(max_length=45)
    recipe_main_image = models.TextField()
    recipe_count = models.IntegerField()
    recipe_sub_id = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'recipe'
        unique_together = (('recipe_id', 'recipe_sub_id'),)


class RecipeHashtag(models.Model):
    recipe = models.ForeignKey(Recipe, models.DO_NOTHING, primary_key=True)
    hashtag = models.ForeignKey(Hashtag, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'recipe_hashtag'
        unique_together = (('recipe', 'hashtag'),)


class RecipeIngredients(models.Model):
    ingredients_id = models.AutoField(primary_key=True)
    recipe = models.ForeignKey(Recipe, models.DO_NOTHING)
    small = models.ForeignKey(IngredientsSmall, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'recipe_ingredients'
        unique_together = (('ingredients_id', 'recipe', 'small'),)


class RecipeLike(models.Model):
    like_id = models.AutoField(primary_key=True)
    user = models.ForeignKey('User', models.DO_NOTHING)
    recipe = models.ForeignKey(Recipe, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'recipe_like'
        unique_together = (('like_id', 'user', 'recipe'),)


class Review(models.Model):
    review_id = models.AutoField(primary_key=True)
    user = models.ForeignKey('User', models.DO_NOTHING)
    review_context = models.CharField(max_length=1000, blank=True, null=True)
    review_image = models.CharField(max_length=1500)
    review_created = models.DateTimeField()
    review_rating = models.FloatField()
    recipe_sub = models.ForeignKey(Recipe, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'review'
        unique_together = (('review_id', 'user', 'recipe_sub'),)


class Sequence(models.Model):
    sequence_id = models.AutoField(primary_key=True)
    sequence_image = models.CharField(max_length=300)
    sequence_number = models.IntegerField()
    sequence_context = models.CharField(max_length=300)

    class Meta:
        managed = False
        db_table = 'sequence'


class User(models.Model):
    user_id = models.CharField(primary_key=True, max_length=45)
    user_email = models.CharField(max_length=45)
    user_name = models.CharField(max_length=45)
    user_password = models.CharField(max_length=45)
    user_image = models.CharField(max_length=300, blank=True, null=True)
    user_introduce = models.CharField(max_length=300, blank=True, null=True)
    user_gid = models.CharField(max_length=45, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'user'


class UserHashtag(models.Model):
    hashtag = models.ForeignKey(Hashtag, models.DO_NOTHING, primary_key=True)
    user = models.ForeignKey(User, models.DO_NOTHING)
    user_hashtag_count = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'user_hashtag'
        unique_together = (('hashtag', 'user'),)


class Video(models.Model):
    video_id = models.AutoField(primary_key=True)
    video_name = models.CharField(max_length=45)
    video_url = models.CharField(max_length=300)

    class Meta:
        managed = False
        db_table = 'video'
