from django.shortcuts import render
from django.http import HttpResponse
import json
# Create your views here.

@api_view(['POST'])
def login(request, format=None):
	email = request.POST['email']
	response = HttpResponse()
	if !User.get(email=email):
		# Need to create a user
		User.create(email=email, name=request.POST['name'])
		response.status_code = 201
		return response
	else:
		user_serialized = json.dumps(User.get(email=email), default=str)
		response.status_code = 200
		response.content = user_serialized
		return response
