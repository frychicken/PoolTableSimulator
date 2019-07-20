# libBezierFiveLines -- WIP

Generate quadratic curve and pool table simulation

using Bezier Curve and slope line equation

```About```

**The window is not resizable**

Anything below 400 will result in unwanted movements of the ball

Origin: (400, 400) 

green is the color of the ball; pink is the curve; magenta is the reflection path; red is the tangent line to the curve; blue is the normal line to the tangent line; dark grey is the perpendicular line to the projected line of the ball (black)

```Usage```

Uncheck "mousechange" if you want to use the simulation mode; otherwise, check it.

Use arrow keys (left/right) or mouse (up/down) (in mousechange mode) to change its width and (up/down) to change the accuracy of its height; type "a" to add more lines, "s" to remove lines and "c" to close the window

click near the blue text to add/remove lines (add: 0-50; remove: 50-100) (in debug mode)

type "t" or click once to toggle changing height (up/down)

type "g" or click at the origin to start animate the ball in simulation mode

type "d" to toggle debug mode

type "m" to toggle mousechange on demand

type "n" to toggle nightmode

```TODO```

Actually calculate the velocity of the ball (green) 

```Compile to jar```

Run Compile.bat for windows

Run Compile.sh for mac/linux

```.exe``` file is outdated