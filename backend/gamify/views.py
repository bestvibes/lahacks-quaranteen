from django.shortcuts import render
from django.http import HttpResponse
from django.core import serializers
# Create your views here.

@api_view(['POST'])
def login(request, format=None):
	email = request.POST['email']
	response = HttpResponse()
	if not User.get(email=email):
		# Need to create a user
		u =	User.create(email=email, name=request.POST['name'])
		user_serialized = serializers.serialize("json", u)
		response.content = user_serialized
		response.status_code = 201
		return response
	else:
		user_serialized = serializers.serialize("json", User.get(email=email))
		response.status_code = 200
		response.content = user_serialized
		return response
