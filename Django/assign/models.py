from django.db import models
from django.db import connections

# Create your models here.
class assignwork(models.Model):
    cid = models.IntegerField(max_length=4)
    email = models.CharField(max_length=40)
    date = models.DateField(max_length=20)
    lat = models.FloatField(max_length=20)
    lon = models.FloatField(max_length=20)
    land = models.CharField(max_length=50)
    dist = models.CharField(max_length=20)
    url = models.CharField(max_length=255)
    lod = models.CharField(max_length=20)
    choice = models.CharField(max_length=20)
    status = models.CharField(max_length=20)
    deadline = models.DateField(max_length=20)
    budget = models.IntegerField(max_length=40)
    class Meta:
        db_table="assign_contr"
