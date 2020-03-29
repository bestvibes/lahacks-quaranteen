from gamify.models import User, ChallengeInstance
from rest_framework import serializers


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = "__all__"

class ChallengeInstanceSerializer(serializers.ModelSerializer):
    class Meta:
        model = ChallengeInstance
        fields = "__all__"
