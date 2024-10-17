@echo off

javac -d . JsfTemplateDownloader.java classes/Menu.java

if %errorlevel% neq 0 (
    echo Erro durante a compilacao!
    pause
    exit /b %errorlevel%
)

jar cfm jsfapp.jar MANIFEST.MF JsfTemplateDownloader.class classes/Menu.class
if %errorlevel% neq 0 (
    echo Erro durante a criacao do JAR!
    pause
    exit /b %errorlevel%
)

pause