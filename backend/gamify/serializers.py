from gamify.models import User, ChallengeInstance, MasterChallenge, MasterTask, TaskInstance, Team
from rest_framework import serializers


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = "__all__"

class TeamSerializer(serializers.ModelSerializer):
    members = UserSerializer(many=True, read_only=True)
    class Meta:
        model = Team
        fields = "__all__"
        
class MasterTaskSerializer(serializers.ModelSerializer):
    class Meta:
        model = MasterTask
        fields = "__all__"
        
class MasterChallengeSerializer(serializers.ModelSerializer):
    master_tasks = MasterTaskSerializer(many=True, read_only=True)
    
    class Meta:
        model = MasterChallenge
        fields = "__all__"

class TaskInstanceSerializer(serializers.ModelSerializer):
    masterTask = MasterTaskSerializer(read_only=True)
    class Meta:
        model = TaskInstance
        fields = "__all__"
        
class ChallengeInstanceSerializer(serializers.ModelSerializer):
    masterChallenge = MasterChallengeSerializer(read_only=True)
    teams = TeamSerializer(many=True, read_only=True)
    tasks = TaskInstanceSerializer(many=True, read_only=True)
    class Meta:
        model = ChallengeInstance
        fields = "__all__"

