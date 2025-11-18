Ы@echo off
REM Скрипт установки Maven 3.9.6

echo ========================================
echo   Установка Maven 3.9.6
echo ========================================
echo.

REM Проверяем Java
echo Проверяю Java...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java не установлена!
    echo Установите Java 8+ перед использованием Maven
    pause
    exit /b 1
)
echo OK - Java найдена

echo.
echo Скачиваю Maven...

REM Создаем папку
if not exist "C:\Maven" mkdir "C:\Maven"
cd /d "C:\Maven"

REM Скачиваем Maven
powershell -Command "^
$ProgressPreference = 'SilentlyContinue'; ^
Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip' ^
-OutFile 'maven.zip' -UseBasicParsing; ^
Write-Host 'OK - Maven загружен'"

echo.
echo Распаковываю архив...
powershell -Command "Expand-Archive -Path 'maven.zip' -DestinationPath 'C:\Maven' -Force"
echo OK - Архив распакован

echo.
echo Добавляю в PATH...
setx PATH "%PATH%;C:\Maven\apache-maven-3.9.6\bin"
echo OK - PATH обновлен

echo.
echo ========================================
echo   Установка завершена!
echo ========================================
echo.
echo Проверьте установку:
echo   mvn --version
echo.
echo Перезагрузитесь, затем используйте:
echo   mvn test
echo.
pause
