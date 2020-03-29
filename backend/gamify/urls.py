from django.urls import include, path
from rest_framework.routers import DefaultRouter
from rest_framework.urlpatterns import format_suffix_patterns

from gamify.views import createTeam, joinTeam, leaveTeam, login, UserViewSet
from gamify.verification.views import verifyApple, verifyVideo, verifyBook, verifySunrise

router = DefaultRouter()
router.register('users', UserViewSet)

urlpatterns = [
    path('login/', login, name='login'),
    path('models/', include(router.urls)),
    path('team/create/', createTeam, name='team-create'),
    path('team/leave/', leaveTeam, name='team-leave'),
    path('team/join/', joinTeam, name='team-join'),
    path('verify/apple/', verifyApple, name='verify-apple'),
    path('verify/video/', verifyVideo, name='verify-video'),
    path('verify/book/', verifyBook, name='verify-book'),
    path('verify/sunrise/', verifySunrise, name='verify-sunrise'),
]
