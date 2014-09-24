@echo off

REM Startup/shutdown script for IS-202 servers

if not exist server_env.bat goto :setup

setlocal
call server_env.bat
if "%1" == "" goto :usage

:check
    echo Checking environment and parameters...
    echo JAVA_HOME = "%JAVA_HOME%"
    if exist "%JAVA_HOME%\bin\java.exe" goto catalina
    echo There is no java runtime in %JAVA_HOME%
    goto end

:catalina
    echo CATALINA_HOME = "%CATALINA_HOME%"
    if exist "%CATALINA_HOME%\bin\catalina.bat" goto instance
    echo Can't find TomEE installation in %CATALINA_HOME%
    goto end

:instance
    echo CATALINA_BASE = "%CATALINA_BASE%"
    echo Looking for "%CATALINA_BASE%\conf\tomee.xml"
    if exist "%CATALINA_BASE%\conf\tomee.xml" goto derby
    echo Can't find TomEE instance in %CATALINA_BASE%
    goto end

:derby
    echo derby.system.home="%DERBY_SYSTEM%"
:main
    if "%1" == "start" goto start
    if "%1" == "stop" goto stop
    if "%1" == "test" goto test
    goto usage

:test
    Echo testing, testing....
    echo Seems to work :-)
    goto end

:start
    REM Delete old logs
    del /F "%CATALINA_BASE%\logs\*.txt" "%CATALINA_BASE%\logs\*.log"

    REM TomEE
    echo Starting TomEE...
    start %CATALINA_HOME%\bin\catalina run

    REM Derby
    echo Starting derby...
    start java "-Dderby.system.home=%DERBY_SYSTEM%" -jar "%DERBYRUN_JAR%" server start
    echo Done!
goto end

:stop
    echo Stopping derby...
    java -jar "%DERBYRUN_JAR%" server shutdown
    echo Stopping TomEE...
    %CATALINA_HOME%\bin\catalina stop
    echo Done!
    goto end

:usage
    @echo Usage:
    @echo    "server start" to start TomEE and Derby
    @echo    "server stop" to stop TomEE and Derby
    @goto end


@echo This is not the end....


:setup
    copy server_env.tmpl server_env.bat

    @echo Server configuration needed
    @echo A file named server_env.bat has been created for you
    @echo in the same folder as this file.
    @echo Please open it in a text editor (notepad or similar)
    @echo and give proper values to the environment variables.
    @echo.
    @echo NOTE: Running this file again without completing the
    @echo setup will not work, even though you will not see
    @echo this warning any more.
    @goto end



:end
endlocal
echo This is the end.....
echo on
