from django.contrib import admin
from django.urls import path
from django.contrib.auth import views as auth_views
from . import views

urlpatterns = [
    path('login/', auth_views.LoginView.as_view(template_name='login.html'), name='login'),
    path('logout/', auth_views.LogoutView.as_view(template_name='logout.html'), name='logout'),
    path('dashboard/',views.show,name='dashboard'),
    path('map/',views.map,name='map'),
    path('sort/<int:val>',views.sort,name="sort"),
    path('filter/',views.filter,name='filter'),
    path('contrassign/<mail>/<int:cid>',views.contrassign,name='contrassign'),
    path('delete/<int:id>',views.delete,name='delete'),

    
    #path('newcontract/',views.insert,name='newcontract'),
    #path('dashboard/', auth_views.dashboard, name='dashboard'),
    #path('password_change/', views.PasswordChangeView.as_view(), name='password_change'),
    #path('password_change/done/', views.PasswordChangeDoneView.as_view(), name='password_change_done'),

    #path('password_reset/', views.PasswordResetView.as_view(), name='password_reset'),
    #path('password_reset/done/', views.PasswordResetDoneView.as_view(), name='password_reset_done'),
    #path('reset/<uidb64>/<token>/', views.PasswordResetConfirmView.as_view(), name='password_reset_confirm'),
    #path('reset/done/', views.PasswordResetCompleteView.as_view(), name='password_reset_complete'),
]
