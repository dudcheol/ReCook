from django.shortcuts import render
from rest_framework.decorators import api_view
from . import Similar
from . import recommendation
from rest_framework.response import Response

@api_view(['GET'])
def index(request,title,userid):   
    return Response( Similar.similar_recommend(title,userid))

@api_view(['GET'])
def choice(request,user_id):       
    return Response(recommendation.check(user_id))

@api_view(['GET'])
def main_index(request,title,user_id):
    return Response(recommendation.recommend(title,user_id))
    
