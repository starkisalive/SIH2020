from django.shortcuts import render,redirect 
from django.contrib.auth.models import User
from django.contrib.auth.decorators import login_required
#from django.contrib.auth.view
from account.models import Dashboard
from django.contrib import messages
from assign.models import assignwork

# Create your views here.
#def login_view(request,*args, **kwargs):


#def dashboard(request):
 #   context = {}
  #  return render(request, "dashboard.html", context)
@login_required
def show(request): 
  if request.method=="POST":
    fromdate = request.POST.get('fromdate')
    todate = request.POST.get('todate')
    searchresult = Dashboard.objects.raw('select * from collect where date between "'+fromdate+'" and "'+todate+'"')
    #fixdate = request.POST.get('deadline')
    #record=Dashboard()
    #record.deadline = fixdate
    #record.save()
    return render(request,"dashboard.html",{'Dashboard':searchresult})
  else:
    #if request.method=="POST"
    resultsdisplay=Dashboard.objects.order_by("-id")
    return render(request,"dashboard.html",{'Dashboard':resultsdisplay})

def map(request):
  return render(request,"new3.html")

def sort(request,val):
  if val==1:
    resultsdisplay=Dashboard.objects.order_by("-lod")
    return render(request,"dashboard.html",{'Dashboard':resultsdisplay})
  elif val==2:
    resultsdisplay=Dashboard.objects.order_by("deadline")
    return render(request,"dashboard.html",{'Dashboard':resultsdisplay})
  else:
    resultsdisplay=Dashboard.objects.order_by("-id")
    return render(request,"dashboard.html",{'Dashboard':resultsdisplay})

def filter(request):
  result=Dashboard.objects.filter(status="accepted")
  return render(request,"dashboard.html",{'Dashboard':result})

def contrassign(request,mail,cid):
  if request.method=="POST":
    #print(request.POST.get('bug'))
    newrecord = Dashboard.objects.filter(id=cid)
    record = Dashboard(
      id=newrecord[0].id,
      user=newrecord[0].user,
      date=newrecord[0].date,
      lat=newrecord[0].lat,
      lon=newrecord[0].lon,
      land=newrecord[0].land,
      dist=newrecord[0].dist,
      sug=newrecord[0].sug,
      url=newrecord[0].url,
      lod=newrecord[0].lod,
      budget=request.POST.get('bug'),
      contr=mail,
      status='Assigned',
      deadline=request.POST.get('date'),)
    record.save()
  new = assignwork.objects.filter(cid=cid)
  new.delete()
  #return render(request,"asg_temp.html")
  return render(request,"message.html")


def delete(request,id):
  dell=Dashboard.objects.filter(id=id)
  dell.delete()
  return render(request,"dashboard.html")









