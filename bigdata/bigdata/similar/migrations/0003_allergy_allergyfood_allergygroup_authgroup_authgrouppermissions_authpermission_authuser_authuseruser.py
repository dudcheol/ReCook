# Generated by Django 2.2.7 on 2021-03-31 08:18

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('similar', '0002_auto_20210331_1640'),
    ]

    operations = [
        migrations.CreateModel(
            name='Allergy',
            fields=[
                ('allergy_id', models.AutoField(primary_key=True, serialize=False)),
                ('allergy_name', models.CharField(max_length=45)),
            ],
            options={
                'db_table': 'allergy',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AllergyGroup',
            fields=[
                ('allergy_group_id', models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                'db_table': 'allergy_group',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AuthGroup',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=150, unique=True)),
            ],
            options={
                'db_table': 'auth_group',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AuthGroupPermissions',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
            ],
            options={
                'db_table': 'auth_group_permissions',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AuthPermission',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=255)),
                ('codename', models.CharField(max_length=100)),
            ],
            options={
                'db_table': 'auth_permission',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AuthUser',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('password', models.CharField(max_length=128)),
                ('last_login', models.DateTimeField(blank=True, null=True)),
                ('is_superuser', models.IntegerField()),
                ('username', models.CharField(max_length=150, unique=True)),
                ('first_name', models.CharField(max_length=30)),
                ('last_name', models.CharField(max_length=150)),
                ('email', models.CharField(max_length=254)),
                ('is_staff', models.IntegerField()),
                ('is_active', models.IntegerField()),
                ('date_joined', models.DateTimeField()),
            ],
            options={
                'db_table': 'auth_user',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AuthUserUserPermissions',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
            ],
            options={
                'db_table': 'auth_user_user_permissions',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='DjangoAdminLog',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('action_time', models.DateTimeField()),
                ('object_id', models.TextField(blank=True, null=True)),
                ('object_repr', models.CharField(max_length=200)),
                ('action_flag', models.PositiveSmallIntegerField()),
                ('change_message', models.TextField()),
            ],
            options={
                'db_table': 'django_admin_log',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='DjangoContentType',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('app_label', models.CharField(max_length=100)),
                ('model', models.CharField(max_length=100)),
            ],
            options={
                'db_table': 'django_content_type',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='DjangoMigrations',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('app', models.CharField(max_length=255)),
                ('name', models.CharField(max_length=255)),
                ('applied', models.DateTimeField()),
            ],
            options={
                'db_table': 'django_migrations',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='DjangoSession',
            fields=[
                ('session_key', models.CharField(max_length=40, primary_key=True, serialize=False)),
                ('session_data', models.TextField()),
                ('expire_date', models.DateTimeField()),
            ],
            options={
                'db_table': 'django_session',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Food',
            fields=[
                ('food_id', models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                'db_table': 'food',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='FoodLike',
            fields=[
                ('food_like_id', models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                'db_table': 'food_like',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Fridge',
            fields=[
                ('fridge_id', models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                'db_table': 'fridge',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Hashtag',
            fields=[
                ('hashtag_id', models.AutoField(primary_key=True, serialize=False)),
                ('hashtag_name', models.CharField(max_length=45)),
                ('hashtag_count', models.IntegerField()),
            ],
            options={
                'db_table': 'hashtag',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='IngredientsBig',
            fields=[
                ('big_id', models.AutoField(primary_key=True, serialize=False)),
                ('big_name', models.CharField(max_length=45)),
            ],
            options={
                'db_table': 'ingredients_big',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='IngredientsMiddle',
            fields=[
                ('mid_id', models.AutoField(primary_key=True, serialize=False)),
                ('middle_name', models.CharField(max_length=45)),
            ],
            options={
                'db_table': 'ingredients_middle',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='IngredientsSmall',
            fields=[
                ('small_id', models.AutoField(primary_key=True, serialize=False)),
                ('small_name', models.CharField(max_length=100)),
            ],
            options={
                'db_table': 'ingredients_small',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Recipe',
            fields=[
                ('recipe_id', models.AutoField(primary_key=True, serialize=False)),
                ('recipe_title', models.CharField(max_length=100)),
                ('recipe_created', models.DateTimeField()),
                ('recipe_image', models.TextField()),
                ('recipe_context', models.TextField()),
                ('recipe_ingredient', models.TextField()),
                ('recipe_time', models.CharField(max_length=45)),
                ('recipe_main_image', models.TextField()),
                ('recipe_count', models.IntegerField()),
                ('recipe_sub_id', models.IntegerField()),
            ],
            options={
                'db_table': 'recipe',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='RecipeIngredients',
            fields=[
                ('ingredients_id', models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                'db_table': 'recipe_ingredients',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='RecipeLike',
            fields=[
                ('like_id', models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                'db_table': 'recipe_like',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Review',
            fields=[
                ('review_id', models.AutoField(primary_key=True, serialize=False)),
                ('review_context', models.CharField(blank=True, max_length=1000, null=True)),
                ('review_image', models.CharField(max_length=1500)),
                ('review_created', models.DateTimeField()),
                ('review_rating', models.FloatField()),
            ],
            options={
                'db_table': 'review',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Sequence',
            fields=[
                ('sequence_id', models.AutoField(primary_key=True, serialize=False)),
                ('sequence_image', models.CharField(max_length=300)),
                ('sequence_number', models.IntegerField()),
                ('sequence_context', models.CharField(max_length=300)),
            ],
            options={
                'db_table': 'sequence',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('user_id', models.CharField(max_length=45, primary_key=True, serialize=False)),
                ('user_email', models.CharField(max_length=45)),
                ('user_name', models.CharField(max_length=45)),
                ('user_password', models.CharField(max_length=45)),
                ('user_image', models.CharField(blank=True, max_length=300, null=True)),
                ('user_introduce', models.CharField(blank=True, max_length=300, null=True)),
                ('user_gid', models.CharField(blank=True, max_length=45, null=True)),
            ],
            options={
                'db_table': 'user',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Video',
            fields=[
                ('video_id', models.AutoField(primary_key=True, serialize=False)),
                ('video_name', models.CharField(max_length=45)),
                ('video_url', models.CharField(max_length=300)),
            ],
            options={
                'db_table': 'video',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='AllergyFood',
            fields=[
                ('allergy', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, primary_key=True, serialize=False, to='similar.Allergy')),
            ],
            options={
                'db_table': 'allergy_food',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='RecipeHashtag',
            fields=[
                ('recipe', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, primary_key=True, serialize=False, to='similar.Recipe')),
            ],
            options={
                'db_table': 'recipe_hashtag',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='UserHashtag',
            fields=[
                ('hashtag', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, primary_key=True, serialize=False, to='similar.Hashtag')),
                ('user_hashtag_count', models.IntegerField()),
            ],
            options={
                'db_table': 'user_hashtag',
                'managed': False,
            },
        ),
    ]
