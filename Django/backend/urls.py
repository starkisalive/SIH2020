from django.contrib import admin
from django.urls import path
from django.contrib.auth import views as auth_views
from . import views

urlpatterns = [
    path('home/', views.home, name="home"),
    path('newcontract/',views.insert,name='newcontract'),
    path('clist/',views.displayList,name='clist'),
    path('message/',views.showmessage,name='message'),
    path('about/',views.about,name='about'),
    path('statistics/',views.statistics,name='statistics'),
    path('rate/<mail>',views.rate,name='rate'),
    path('edit/<mail>',views.edit,name='edit'),
    path('update/',views.update,name='update'),
]
