# PoolTableSimulator -- WIP

Generate quadratic curve and pool table simulation

using Bezier Curve and slope line equation

#### ```Table of contents``` 

| About           | Usage         | TODO    | Compile to jar  | Changing the algorithm
| :-------------: |:-------------:| :-----:|:------:|:----:|
| [here](#About)  | [here](#Usage) | [here](#TODO)| [here](#Compile-to-jar) | [here](#Changing-the-algorithm) |


## About

_Powered by [libBezierFiveLines](https://github.com/bobdinh139/libBezierFiveLines)_

**The window is not resizable**

Anything below 400 will result in unwanted movements of the ball

Origin: (400, 400) 

green is the color of the ball; pink is the curve; magenta is the reflection path; red is the tangent line to the curve; blue is the normal line to the tangent line; dark grey is the perpendicular line to the projected line of the ball (black)

## Usage

Uncheck "mousechange" if you want to use the simulation mode; otherwise, check it.

Use arrow keys (on keyboard/on-screen) or mouse (in mousechange mode -- drag) to change its width and its height; type "a" to add more lines, "s" to remove lines and "c" to close the window

click near the blue text to add/remove lines (add: 0-50; remove: 50-100) 

type "t" / click once (mousechange mode) / click the blue-oval button to toggle changing height (from accuracy to up and down)

type "g" / click at the origin / click the circle gray button to start animate the ball (in simulation mode)

type "d" / click the green-oval button to toggle debug mode

type "m" to toggle mousechange on demand

type "n" / click the orange-oval button to toggle night-mode

type "r" / click the red-oval button to reset curve

type "h" / click the pink-oval button to hide help box

## TODO

Actually calculate the velocity of the ball (green) 

## Compile to jar

Run Compile.bat for windows

Run Compile.sh for mac/linux

## Changing the algorithm

Download [Customizer.jar](Customize/Customizer.jar)

[Follow these instructions](https://github.com/frychicken/PoolTableSimulator/wiki/Changing-Algorithm)

