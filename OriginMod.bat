@echo off

echo starting origin...
start C:/"Program Files (x86)"/Origin/Origin.exe
sleep 4
echo origin starting...
echo press enter to close origin completely.
pause >nul

echo closing origin...
taskkill /f /im origin.exe
sleep 3
echo clearing logs
IF EXIST debug.log (
    del debug.log
)
sleep 2