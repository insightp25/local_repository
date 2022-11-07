from django.db import models

class Company(models.Model):
    company_name = models.CharField(max_length=100)
    country = models.CharField(max_length=50)
    city = models.CharField(max_length=50)

class JobPost(models.Model):
    company = models.ForeignKey(Company, on_delete=models.CASCADE)
    opening_position = models.CharField(max_length=50)
    job_description = models.TextField()
    required_skill = models.CharField(max_length=50)
    contract_reward = models.IntegerField(default=0)

class EndUser(models.Model):
    nickname = models.CharField(max_length=50, unique=True)


    jobpost_set = models.ManyToManyField(JobPost, blank=True)
