from django.db import models
from django.db import models
from django.db import connections

# Create your models here.
class newcontract(models.Model):
    cname = models.CharField(max_length=40)
    cemail = models.CharField(max_length=50)
    cphn = models.CharField(max_length=20)
    cloc = models.CharField(max_length=50)
    cpassword = models.CharField(max_length=50)
    rate = models.IntegerField(max_length=4)
    count = models.IntegerField(max_length=4)
    class Meta:
        db_table="contractor"

