@echo off
REM Скрипт для запуска всех тестов из ProjectNew

cd /d "C:\Users\ILYA.MARTSYNKEVICH\IdeaProjects\ProjectNew"

echo.
echo ===============================================
echo   Запуск JUnit4 тестов из пакета: tests.junit.booking
echo ===============================================
echo.

REM Запуск всех тестов
mvn test -Dtest=tests.junit.booking.**

echo.
echo ===============================================
echo   Завершено
echo ===============================================
echo.

pause
