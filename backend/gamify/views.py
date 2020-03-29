from django.shortcuts import render
from django.http import HttpResponse
from django.core import serializers

from rest_framework.views import APIView 
from gamify.models import User
# Create your views here.

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
		u =	User(email=email, name=request.POST['name'])
		u.save()
		user_serialized = serializers.serialize("json", [u])
		response.content = user_serialized
		response.status_code = 201
		return response
