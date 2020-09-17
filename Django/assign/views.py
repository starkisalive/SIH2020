from django.shortcuts import render,HttpResponse,redirect
from backend.models import newcontract
from django.contrib.auth.decorators import login_required
from django.core.mail import send_mail 
from django.conf import settings
from account.models import Dashboard 
from assign.models import assignwork


from django.contrib.auth.models import User
from django.contrib.auth.forms import PasswordResetForm

# Create your views here.
def assignment(request,getdata_id):
    #contrecord=newcontract.objects.filter(id=1)
    contrecord=newcontract.objects.all()
    count=len(contrecord)
    newrecord=Dashboard.objects.filter(id=getdata_id)
    for i in range(count):
        assignrecord=assignwork(email=contrecord[i].cemail,
            cid=newrecord[0].id,
            date=newrecord[0].date,
            lat=newrecord[0].lat,
            lon=newrecord[0].lon,
            land=newrecord[0].land,
            dist=newrecord[0].dist,
            url=newrecord[0].url,
            lod=newrecord[0].lod,
            choice='choice',
            status='not assigned',
            deadline=newrecord[0].date,
            budget=0)
        assignrecord.save()
    return render(request,"message.html") 

def interest(request):
    record=assignwork.objects.filter(choice='not interested')
    record.delete()
    result=assignwork.objects.filter(choice='intrested')
    return render(request,"show_int.html",{'assignwork':result})

def budget(request):
    if request.method=="POST":
        dash = Dashboard()
        dash.budget=request.POST.get('bug')
        dash.deadline=request.POST.get('deadline')
        dash.save()
    return render(request,"dashboard.html")
   



    