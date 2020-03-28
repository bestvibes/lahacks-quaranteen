from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from . import views



urlpatterns = [
	path('/login', views.login, name='login'),
]

urlpatterns = format_suffix_patterns(urlpatterns)
