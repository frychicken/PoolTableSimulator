@echo off 
echo compile java files...
javac *java
echo make it to .jar...
jar cvfm PoolSimulator.jar manifest.txt *.class lolol.png
echo deleting *.class ..
del *class
echo done!
pause