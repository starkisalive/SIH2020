from django.shortcuts import render,HttpResponse,redirect
from backend.models import newcontract
from account.models import Dashboard
from django.contrib.auth.decorators import login_required
from django.core.mail import send_mail 
from django.conf import settings


from django.contrib.auth.models import User
from django.contrib.auth.forms import PasswordResetForm

#from django.core import mail
#connection = mail.get_connection()
#connection.open()

# Create your views here.
def home(request):
    return HttpResponse('home page')

def insert(request):
  if request.method=="POST":
    if request.POST.get('cname') and request.POST.get('cemail') and request.POST.get('cphn') and request.POST.get('cloc'):
      x=request.POST.get('cemail')
      saverecord=newcontract()
      saverecord.cname=request.POST.get('cname')
      saverecord.cemail=request.POST.get('cemail')
      saverecord.cphn=request.POST.get('cphn')
      saverecord.cloc=request.POST.get('cloc')
      saverecord.cpassword='password'
      saverecord.rate=0
      saverecord.count=0
      saverecord.save()
      subject = 'Welcome you to GADDHE as a contractor'
      message = 'Congratulations to be a part of "GADDHE". Your username='+x+',password=password. Click on the link http://vektor.soumit.tech/index.php?uname='+x+' and reset your password.' 
      email_from = settings.EMAIL_HOST_USER
      recipient_list = [x]
      send_mail(subject,message,email_from,recipient_list,fail_silently=False)
      return render(request,"message.html")
  else:
    return render(request,"newcontract.html")

@login_required
def displayList(request):
  resultsdisplay=newcontract.objects.all()
  return render(request,"clist.html",{'newcontract':resultsdisplay})

def showmessage(request):
  return render(request,"message.html")

def about(request):
  return render(request,"about.html")

def statistics(request):
  if request.method=="POST":
    fromdate = request.POST.get('fromdate')
    todate = request.POST.get('todate')
    searchresult = Dashboard.objects.raw('select * from collect where date between "'+fromdate+'" and "'+todate+'"')
    return render(request,"statistics.html",{'Dashboard':searchresult})
  else:
    resultsdisplay=Dashboard.objects.order_by("-id")
    return render(request,"statistics.html",{'Dashboard':resultsdisplay})
  return render(request,"statistics.html")

def rate(request,mail):
  dash = Dashboard.objects.filter(contr=mail).count()
  #c = len(dash)
  if request.method=="POST":
    mod = newcontract.objects.get(cemail=mail)
    x=request.POST.get('rate')
    mod.rate=(mod.rate+int(x))/dash
    mod.count = dash
    mod.save()
    #record = newcontract(
     # cname=mod[0].cname,
      #cemail=mod[0].cemail,
      #cphn=mod[0].cphn,
      #cloc=mod[0].cloc,
      #cpassword=mod[0].cpassword,
      #rate=request.POST.get('rate'),)
    #record.save()
  return render(request,"dashboard.html")

def edit(request,mail):
  return render(request,"edit.html")

def update(request):
  if request.method=="POST":
    #x = request.POST.get('Uname')
    y = request.POST.get('Uemail')
    #z = request.POST.get('Uphn')
    #a = request.POST.get('Uloc')
  obj = newcontract.objects.get(cemail=y)
  obj.cname=request.POST.get('Uname')
  obj.cphn=request.POST.get('Uphn')
  obj.cloc=request.POST.get('Uloc')
  obj.save()
  return render(request,"message.html")








