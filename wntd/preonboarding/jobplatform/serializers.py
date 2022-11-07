from rest_framework.serializers import ModelSerializer
from .models import Company, JobPost, EndUser

class CompanySerializer(ModelSerializer):
    class Meta:
        model = Company
        fields = '__all__'

class JobPostSerializer(ModelSerializer):
    class Meta:
        model = JobPost
        fields = '__all__'

class EndUserSerializer(ModelSerializer):
    class Meta:
        model = EndUser
        fields = '__all__'