from django.shortcuts import render,redirect 
from SmartIndiaHack.models import models

def show(request):
    resultsdisplay=Dashboard.objects.all()
    return render(request,"dashboard.html",{'Dashboard':resultsdisplay})