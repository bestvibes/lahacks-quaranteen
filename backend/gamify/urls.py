from django.urls import include, path
from rest_framework.routers import DefaultRouter
from rest_framework.urlpatterns import format_suffix_patterns

from . import views

router = DefaultRouter()
router.register('users', views.UserViewSet)

urlpatterns = [
    path('login/', views.login, name='login'),
    path('models/', include(router.urls)),
    path('team/create/', views.createTeam, name='team-create'),
    path('team/leave/', views.leaveTeam, name='team-leave'),
    path('team/join/', views.joinTeam, name='team-join'),
]
