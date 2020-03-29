import datetime
import os

from django.db import models


def get_image_path(instance, filename):
    return os.path.join('photos', filename)


class Team(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    join_code = models.CharField(max_length=100, blank=False, null=False)


class User(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    email = models.CharField(max_length=100, blank=False, null=False)
    team = models.ForeignKey(Team, related_name="members", on_delete=models.SET_NULL, blank=True, null=True)  # many-to-one
    team_leader = models.BooleanField(blank=False, null=False, default=False)
    profile_image = models.ImageField(upload_to=get_image_path, blank=True, null=True)


class TeamScore(models.Model):
    team = models.ForeignKey(Team, on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    score = models.IntegerField(blank=False, null=False)


class MasterChallenge(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    description = models.CharField(max_length=512, blank=False, null=False)
    prize = models.CharField(max_length=100, blank=False, null=False)
    daily_point_limit = models.IntegerField(blank=False, null=False)
    team_size_limit = models.IntegerField(blank=False, null=False)
    number_of_days = models.IntegerField(blank=False, null=False)


class ChallengeInstance(models.Model):
    masterChallenge = models.ForeignKey(MasterChallenge, related_name="challenge_instances", on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    teams = models.ManyToManyField(Team, blank=True)  # many-to-many
    scores = models.ManyToManyField(TeamScore, blank=True)  # many-to-many
    start_time = models.DateTimeField(blank=False, null=False)

    def is_in_progress(self):
        time_now = datetime.datetime.now(self.start_time.tzinfo)
        return (time_now > self.start_time and time_now < self.start_time + datetime.timedelta(days=self.challenge.number_of_days))

    def has_ended(self):
        time_now = datetime.datetime.now(self.start_time.tzinfo)
        return (time_now >= self.start_time + datetime.timedelta(days=self.challenge.number_of_days))


class MasterTask(models.Model):
    name = models.CharField(max_length=100, blank=False, null=False)
    challenge = models.ForeignKey(MasterChallenge, related_name="master_tasks", on_delete=models.CASCADE, blank=False, null=False) # many-to-one
    description = models.CharField(max_length=512, blank=False, null=False)
    verification_url = models.CharField(max_length=512, blank=False, null=False)
    point_value = models.IntegerField(blank=False, null=False)

class TaskInstance(models.Model):
    challenge = models.ForeignKey(ChallengeInstance, related_name="tasks", on_delete=models.CASCADE, blank=False, null=False)
    masterTask = models.ForeignKey(MasterTask, related_name="instances", on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    user = models.ForeignKey(User, related_name="tasks", on_delete=models.CASCADE, blank=False, null=False)  # many-to-one
    date = models.DateField()
    completed = models.BooleanField(blank=False, null=False)
