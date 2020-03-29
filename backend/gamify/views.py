import random
import string

from django.core import serializers
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from rest_framework.viewsets import ModelViewSet

from gamify.models import Team, User, ChallengeInstance, TaskInstance
from gamify.serializers import UserSerializer, ChallengeInstanceSerializer
from django.http import HttpResponse
from django.core import serializers

from rest_framework.views import APIView 
from gamify.models import User


@api_view(['POST'])
def login(request, format=None):
	email = request.POST['email']
	response = HttpResponse()
	try:
		u = User.objects.get(email=email)
		user_serialized = serializers.serialize("json", [u])
		response.status_code = 200
		response.content = user_serialized
		return response
	except User.DoesNotExist:
		u = User(email=email, name=request.POST['name'])
		u.save()
		user_serialized = serializers.serialize("json", [u])
		response.content = user_serialized
		response.status_code = 201
		return response


@api_view(['POST'])
def createTeam(request):
    """
    Create a team
    inputs: JSON with two field "name", "user_id"
    """
    data = JSONParser().parse(request)
    new_team = Team(
        name=data['name'],
        join_code=''.join(random.choices(string.ascii_uppercase + string.digits, k=5))
    )
    new_team.save()

    user = User.objects.get(id=data["user_id"])
    user.team = new_team
    user.team_leader = True
    user.save()

    data = serializers.serialize("json", Team.objects.filter(id=new_team.id))
    return JsonResponse(data, status=201, safe=False)
 

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
        return JsonResponse(
            serializers.serialize("json", User.objects.filter(id=user.id)),
            status=403, safe=False
        )
    user.team = None
    user.team_leader = False
    user.save()
    return JsonResponse(
        serializers.serialize("json", User.objects.filter(id=user.id)),
        status=201, safe=False
    )


@api_view(['POST'])
def joinTeam(request):
    """
    Join a team
    inputs: JSON with two fields "user_id", "join_code"
    """
    data = JSONParser().parse(request)
    team_to_join = Team.objects.get(join_code=data["join_code"])
    user = User.objects.get(id=data["user_id"])
    user.team = team_to_join
    user.team_leader = False
    user.save()
    return JsonResponse(
        serializers.serialize("json", User.objects.filter(id=user.id)),
        status=201, safe=False
    )


class UserViewSet(ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer


@api_view(['POST'])
def joinChallenge(request):
    """
    Join a challenge
    inputs: JSON with three fields "challenge_id", "team_id", "user_id"
    """
    data = JSONParser().parse(request)
    challenge = ChallengeInstance.objects.get(id=data["challenge_id"])
    user = User.objects.get(id=data["user_id"])
    team = Team.objects.get(id=data["team_id"])

    if (not user.team_leader):
        return JsonResponse("User not team leader", status=401)
    if (team in challenge.teams):
        return JsonResponse("Team already joined!", status=200)
    if (challenge.is_in_progress() or challenge.has_ended()):
        return JsonResponse("Challenge has already started!", status=403)

    challenge.teams.add(team)
    challenge.save()
    return JsonResponse(
        serializers.serialize("json", ChallengeInstance.objects.filter(id=challenge.id)),
        status=200, safe=False
    )


@api_view(['POST'])
def getUserTasks(request):
    """
    Get all the tasks for a user.
    inputs: JSON with one field "user_id"
    """
    data = JSONParser().parse(request)
    user = User.objects.get(id=data["user_id"])
    return JsonResponse(
        serializers.serialize("json", TaskInstance.objects.filter(user=user)), 
        status=200, safe=False
    )


class ChallengeInstanceViewSet(ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = ChallengeInstance.objects.all()
    serializer_class = ChallengeInstanceSerializer
