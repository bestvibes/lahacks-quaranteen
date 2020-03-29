from django.urls import include, path
from rest_framework.routers import DefaultRouter
from rest_framework.urlpatterns import format_suffix_patterns

from gamify.views import (
    createTeam, joinChallenge, joinTeam, leaveTeam, login, getUserTasks,
    ChallengeInstanceViewSet, UserViewSet, MasterChallengeViewSet, MasterTaskViewSet,
    TaskInstanceViewSet, TeamViewSet, schema_view, uploadProfilePic
)
from gamify.verification.views import verifyApple, verifyVideo, verifyBook, verifySunrise

router = DefaultRouter()
router.register('users', UserViewSet)
router.register('teams', TeamViewSet)
router.register('challenges', ChallengeInstanceViewSet)
router.register('masterchallenges', MasterChallengeViewSet)
router.register('mastertasks', MasterTaskViewSet)
router.register('tasks', TaskInstanceViewSet)

urlpatterns = [
    path('login/', login, name='login'),
    path('models/', include(router.urls)),
    path('models/users/pic/', uploadProfilePic, name='upload-profile-pic'),
    path('tasks/byuser/', getUserTasks, name='get-user-tasks'),
    path('team/create/', createTeam, name='team-create'),
    path('team/leave/', leaveTeam, name='team-leave'),
    path('team/join/', joinTeam, name='team-join'),
    path('challenge/join/', joinChallenge, name='challenge-join'),
    path('verify/apple/', verifyApple, name='verify-apple'),
    path('verify/video/', verifyVideo, name='verify-video'),
    path('verify/book/', verifyBook, name='verify-book'),
    path('verify/sunrise/', verifySunrise, name='verify-sunrise'),
    path('docs/',
         schema_view.with_ui('swagger', cache_timeout=0),
         name='schema-swagger-ui'),
]
