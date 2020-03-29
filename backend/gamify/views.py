import random
import string

from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.parsers import JSONParser
from rest_framework.viewsets import ModelViewSet
from rest_framework import permissions

from gamify.models import (
    Team, User, ChallengeInstance,
    TaskInstance, MasterChallenge, MasterTask
)
from gamify.serializers import (
    UserSerializer, ChallengeInstanceSerializer, MasterChallengeSerializer, 
    MasterTaskSerializer, TaskInstanceSerializer, TeamSerializer
)
from django.http import HttpResponse

from rest_framework.views import APIView 
from gamify.models import User

from drf_yasg.views import get_schema_view
from drf_yasg import openapi


@api_view(['POST'])
def login(request, format=None):
	email = request.POST['email']

	try:
		u = User.objects.get(email=email)
		user_serialized = UserSerializer(u).data
		return JsonResponse(user_serialized, status=200, safe=False)
	except User.DoesNotExist:
		u = User(email=email, name=request.POST['name'])
		u.save()
		user_serialized = UserSerializer(u).data
		return JsonResponse(user_serialized, status=200, safe=False)

@api_view(['POST'])
def deleteUser(request):
    """
    Delete a user. mainly for debugging/testing.
    inputs: JSON with one field "user_id"
    """
    data = JSONParser().parse(request)
    user_id = data['user_id']

    User.objects.get(id=data["user_id"]).delete()

    return HttpResponse(status=200)


@api_view(['POST'])
def uploadProfilePic(request):
    """
    Upload user profile pic
    inputs: JSON with two fields "user_id", "image"
    """
    data = JSONParser().parse(request)
    img = request.FILES["image"]

    user = User.objects.get(id=data["user_id"])
    user.profile_image = img
    user.save()

    data = serializers.serialize("json", User.objects.get(id=data["user_id"]))
    return JsonResponse(data, status=201, safe=False)


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

    data = TeamSerializer(Team.objects.get(id=new_team.id)).data
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
            UserSerializer(User.objects.get(id=user.id)).data,
            status=403, safe=False
        )
    user.team = None
    user.team_leader = False
    user.save()
    return JsonResponse(
        UserSerializer(User.objects.get(id=user.id)).data,
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
        UserSerializer(User.objects.get(id=user.id)).data,
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
        return HttpResponse("User not team leader", status=401)
    if (team in challenge.teams.all()):
        return HttpResponse("Team already joined!", status=200)
    if (challenge.is_in_progress() or challenge.has_ended()):
        return HttpResponse("Challenge has already started!", status=403)

    challenge.teams.add(team)
    challenge.save()
    return JsonResponse(
        ChallengeInstanceSerializer(ChallengeInstance.objects.get(id=challenge.id)).data,
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
        TaskInstanceSerializer(TaskInstance.objects.filter(user=user), many=True).data, 
        status=200, safe=False
    )

@api_view(['POST'])
def createUserTask(request):
    """
    Create a task for a user.
    inputs: JSON with one field "user_id", "date" ("2020-03-29"), "completed", "master_task_id", "challenge_id"
    """
    data = JSONParser().parse(request)
    task = TaskInstance.objects.create(user=User.objects.get(id=data["user_id"]), date=data["date"], completed=data["completed"], masterTask=MasterTask.objects.get(id=data["master_task_id"]), challenge=ChallengeInstance.objects.get(id=data["challenge_id"]))
    return JsonResponse(
        TaskInstanceSerializer(task).data, 
        status=200, safe=False
    )



class MasterChallengeViewSet(ModelViewSet):
    """
    API endpoint that allows master challenges to be viewed or edited.
    """
    queryset = MasterChallenge.objects.all()
    serializer_class = MasterChallengeSerializer

class MasterTaskViewSet(ModelViewSet):
    """
    API endpoint that allows master tasks to be viewed or edited.
    """
    queryset = MasterTask.objects.all()
    serializer_class = MasterTaskSerializer
    
class ChallengeInstanceViewSet(ModelViewSet):
    """
    API endpoint that allows challenge instances to be viewed or edited.
    """
    queryset = ChallengeInstance.objects.all()
    serializer_class = ChallengeInstanceSerializer

class TaskInstanceViewSet(ModelViewSet):
    """
    API endpoint that allows task instances to be viewed or edited.
    """
    queryset = TaskInstance.objects.all()
    serializer_class = TaskInstanceSerializer

class TeamViewSet(ModelViewSet):
    """
    API endpoint that allows teams to be viewed or edited.
    """
    queryset = Team.objects.all()
    serializer_class = TeamSerializer

schema_view = get_schema_view(
    openapi.Info(
        title="Gamify API",
        default_version='v1',
        description="Gamifying the quarantine",
    ),
    public=True,
    permission_classes=(permissions.AllowAny, ),
)
