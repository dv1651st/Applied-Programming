from django.http import HttpResponse, HttpResponseRedirect
from .forms import CreateNewList
from django.shortcuts import render
# Create your views here.

def home(response):
    return render(response, "main/home.html", {}) 
def logout(response):
    return render(response, "main/logout_user.html", {})