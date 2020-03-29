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
    email = models.CharField(max_length=100, blank=False, null=False)
    team = models.ForeignKey(Team, on_delete=models.SET_NULL, blank=True, null=True)  # many-to-one
    team_leader = models.BooleanField(blank=True, null=True)
