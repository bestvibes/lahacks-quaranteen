from rest_framework import viewsets
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from django.core import serializers
from rest_framework.parsers import JSONParser
from gamify.models import Team, User
from gamify.serializers import UserSerializer
from rest_framework.decorators import api_view

@api_view(['POST'])
def createTeam(request):
    """
    Create a team
    inputs: JSON with one field "name"
    """
    data = JSONParser().parse(request)
    new_team = Team.objects.create(name=data['name'])
    return JsonResponse(serializers.serialize("json", new_team), status=201)
 
@api_view(['POST'])
def leaveTeam(request):
    data = JSONParser().parse(request)
    User.objects.get(data["user_id"])

@api_view(['POST'])
def joinTeam(request):
    data = JSONParser().parse(request)
    team_to_join = Team.objects.get(join_code=data["join_code"])

class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer

