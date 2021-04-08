from django.urls import path
from . import views

app_name="views"

urlpatterns = [
    path('recipe/<title>/<userid>', views.index), #연관 레시피
    path('recommend/<user_id>', views.choice), #추천 레시피(리뷰 3개 이하면 취향조사 반영, 리뷰 3개이상이면 협업필터링)
    path('main/<title>/<user_id>', views.main_index),#메인 레시피
]