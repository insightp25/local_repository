from rest_framework.viewsets import ModelViewSet
from .serializers import CompanySerializer, JobPostSerializer, EndUserSerializer
from .models import Company, JobPost, EndUser

class CompanyViewSet(ModelViewSet):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer
    
class JobPostViewSet(ModelViewSet):
    queryset = JobPost.objects.all()
    serializer_class = JobPostSerializer

class EndUserViewSet(ModelViewSet):
    queryset = EndUser.objects.all()
    serializer_class = EndUserSerializer

