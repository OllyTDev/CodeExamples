@echo off

:: Script I used to create the folders and files needed to work with ROA Stream overlay 
:: See: https://github.com/Readek/RoA-Stream-Tool
:: Takes my pre-existing pool of images, named appropriately, and copies them into individually named folders. 
:: It then copies the template json file, creating new ones with the appropriate name



set cDir=C:\Users\Olive\Pictures\Smash\Characters
for /f %%f in ('dir /b %cDir%') DO (
call:createFolder %%~nf
call:copyFiles %%f, %%~nf
)
pause

:createFolder
echo **** creating folder %~1 ****
cd ROAStreamOverlay
mkdir %~1
cd ..
EXIT /B 0

:copyFiles
chdir
cd %cDir%
echo **** copying %~1 to ROAStreamOverlay\%~2 ****
copy %~1 ..\ROAStreamOverlay\%~2
ren ..\ROAStreamOverlay\%~2\%~1 Normal.png
chdir
echo **** copying character info to ROAStreamOverlay\%~2 ****
copy ..\CharacterInfoTemplate.json ..\ROAStreamOverlay
ren ..\ROAStreamOverlay\CharacterInfoTemplate.json %~2.json
cd ..
EXIT /B 0