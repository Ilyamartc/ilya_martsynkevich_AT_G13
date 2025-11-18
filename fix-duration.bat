@echo off
REM Исправляет ошибку Duration в W3SchoolsGoogleTest.java

setlocal enabledelayedexpansion

set "file=C:\Users\ILYA.MARTSYNKEVICH\IdeaProjects\ProjectNew\src\test\java\tests\junit\booking\W3SchoolsGoogleTest.java"

REM Создаем временный файл
set "temp_file=%file%.tmp"

REM Читаем файл и заменяем строку
for /f "delims=" %%A in ('findstr /n "." "%file%"') do (
    set "line=%%A"
    set "line=!line:*:=!"
    
    if "!line!"=="                    wait.wait(Duration.ofMillis(500));" (
        echo                    try {
        echo                        Thread.sleep(500^);
        echo                    } catch (InterruptedException e) {
        echo                        Thread.currentThread(^).interrupt(^);
        echo                    }
    ) else (
        echo !line!
    )
) > "%temp_file%"

REM Заменяем оригинальный файл
move /y "%temp_file%" "%file%" >nul

echo Файл исправлен: %file%
pause
