from django.urls import include, path
from rest_framework.routers import DefaultRouter
from . import views

router = DefaultRouter()
router.register('company', views.CompanyViewSet)
router.register('jobpost', views.JobPostViewSet)
router.register('enduser', views.EndUserViewSet)

urlpatterns = [
    path('', include(router.urls))
]
