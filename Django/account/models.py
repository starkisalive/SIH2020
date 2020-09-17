from django.db import models
from django.db import connections
from django.utils import timezone

# Create your models here.
class Dashboard(models.Model):
    user = models.CharField(max_length=30)
    date = models.DateField(max_length=20)
    lat = models.FloatField(max_length=20)
    lon = models.FloatField(max_length=20)
    land = models.CharField(max_length=50)
    dist = models.CharField(max_length=20)
    sug = models.CharField(max_length=50)
    url = models.CharField(max_length=225)
    lod = models.CharField(max_length=10)
    contr = models.CharField(max_length=20)
    status = models.CharField(max_length=20)
    budget = models.IntegerField(max_length=50)
    deadline = models.DateField(max_length=20)
    class Meta:
        db_table="citizen_data"

#CATEGORIES = (  
 #   ('SORT BY', '0'),
  #  ('LOD', '1'),
   # ('DATE', '2'),
#)


