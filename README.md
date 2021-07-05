# Overview

This is a Firefox extention that will block out the recommended videos on the right hand side of the windom. There is no UI, which was the stretch challenge. 
The way it works is use WebRequestBlocking API to intercept the data before it appears on screen. We can add various attributes into the main html tag which will determine what content is visible or invisible. To test it, type "about:debugging" in the main search bar of Firefox, click on "load temporary add-on", and select the manifest.json file. Go on youtube and click on a video. 

[Software Demo Video](https://youtu.be/3yrLIL4EMsA)

# Development Environment

* Firefox
* Visual Studio
* JSON
* Javascript
* CSS

# Useful Websites

A website that I referenced often.
* [Firefox Developer Resources](https://developer.mozilla.org/en-US/)

# Future Work

Future improvements
* Add UI
* Block elements from youtube app
* Make it remove more than one thing