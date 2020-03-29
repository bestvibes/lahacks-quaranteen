from django.core import serializers
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from rest_framework.viewsets import ModelViewSet

from gamify.models import Team, User
from gamify.serializers import UserSerializer


@api_view(['POST'])
def createTeam(request):
    """
    Create a team
    inputs: JSON with two field "name", "user_id"
    """
    data = JSONParser().parse(request)
    new_team = Team.objects.create(name=data['name'])
    new_team.save()

    user = User.objects.get(id=data["user_id"])
    user.team = new_team
    user.team_leader = True
    user.save()

    return JsonResponse(serializers.serialize("json", new_team), status=201)
 

@api_view(['POST'])
def leaveTeam(request):
    """
    Leave a team
    inputs: JSON with one field "user_id"
    """
    data = JSONParser().parse(request)
    user = User.objects.get(id=data["user_id"])
    # TODO: Hacky, easier to deal with data consistency by not letting team leaders leave
    if user.team_leader:
        return JsonResponse(serializers.serialize("json", user), status=403)
    user.team = None
    user.team_leader = False
    user.save()
    return JsonResponse(serializers.serialize("json", user), status=201)


@api_view(['POST'])
def joinTeam(request):
    """
    Join a team
    inputs: JSON with three field "user_id", "join_code"
    """
    data = JSONParser().parse(request)
    team_to_join = Team.objects.get(join_code=data["join_code"])
    user = User.objects.get(id=data["user_id"])
    user.team = team_to_join
    user.team_leader = False
    user.save()
    return JsonResponse(serializers.serialize("json", user), status=201)


class UserViewSet(ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer
