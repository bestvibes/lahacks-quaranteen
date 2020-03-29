import hashlib
import time

from django.db import models


class Team(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    join_code = models.CharField(max_length=100, blank=False, null=False)

    # Factory method b/c messing with constructor might break other things
    def create(cls, name):
        token = name + str(time.time())
        # src: https://stackoverflow.com/questions/16008670/how-to-hash-a-string-into-8-digits
        return Team(name=name, join_code=hashlib.sha256(token.encode('utf-8')).hexdigest()[:5])


class User(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    team = models.ForeignKey(Team, on_delete=models.SET_NULL, blank=True, null=True)  # many-to-one
    team_leader = models.BooleanField(blank=False, null=False, default=False)


class MasterTask(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    description = models.CharField(max_length=512, blank=False, null=False)
    verification_url = models.CharField(max_length=512, blank=False, null=False)
    point_value = models.IntegerField(blank=False, null=False)


class TaskInstance(models.Model):
    task = models.ForeignKey(MasterTask, on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    user = models.ForeignKey(User, on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    date = models.DateField(auto_now_add=True)
    completed = models.BooleanField(blank=False, null=False)


class TeamScore(models.Model):
    team = models.ForeignKey(Team, on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    score = models.IntegerField(blank=False, null=False)


class MasterChallenge(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    description = models.CharField(max_length=512, blank=False, null=False)
    prize = models.CharField(max_length=100, blank=False, null=False)
    mastertasks = models.ManyToManyField(MasterTask)  # many-to-many
    daily_point_limit = models.IntegerField(blank=False, null=False)
    team_size_limit = models.IntegerField(blank=False, null=False)
    number_of_days = models.IntegerField(blank=False, null=False)


class ChallengeInstance(models.Model):
    challenge = models.ForeignKey(MasterChallenge, on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    teams = models.ManyToManyField(Team)  # many-to-many
    tasks = models.ManyToManyField(TaskInstance)  # many-to-many
    scores = models.ManyToManyField(TeamScore)  # many-to-many
