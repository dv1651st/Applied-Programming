# Overview

Welcome to the new and improved edition of the Rock Paper Scissors (RPS) android app! 

Here is a list of changes that occured since the first version:
* Firebase successfully integrated. 
* UI for registration and login page
* Page for "find player" incorporated.


[Software Demo Video](https://youtu.be/KrzE9aadNPs)

# Network Communication

So far it is only client-server. Peer to peer will be incorporated once arcitecture for obtaining the required data from each user is successfully implemented.

Firebase uses websockets to communicate between client and server, which keeps it open versus the standard method of creating the connection, sending info, and then terminating it.

# Development Environment

* Kotlin 1.5.0 
* Android Studio 4.2 
* Windows 10 Home 64 bit

# Useful Websites

{Make a list of websites that you found helpful in this project}
* [Android Studio Developer](https://developer.android.com/)
* [Kotlin Docs](https://kotlinlang.org/docs/home.html)
* [Firebase](https://firebase.google.com/)


# Future Work

{Make a list of things that you need to fix, improve, and add in the future.}
* Acquire required information from user. Presently only email and password is acquired, but not username, age, and other info.
* Display info from database back into the app in "Find player" page.
* Have the game switch to player 2 once player 1 has selected an option and confirmed it.