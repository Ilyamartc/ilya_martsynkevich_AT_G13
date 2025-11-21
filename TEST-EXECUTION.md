# Запуск тестов в проекте

Проект настроен для работы с **JUnit 4**, **TestNG** и **Cucumber**. Используйте Maven профили для запуска нужных типов тестов.

## 🚀 Команды для запуска тестов

### Все тесты (JUnit + TestNG)
```bash
mvn test
```

### JUnit тесты
```bash
mvn test -Pjunit
```

### TestNG тесты
```bash
mvn test -Ptestng
```

### Cucumber тесты
```bash
mvn test -Pcucumber
```

### Selenium тесты
```bash
mvn test -Pselenium
```

### Все тесты включая Cucumber
```bash
mvn test -Pall-tests
```

## 📁 Структура тестов

```
src/test/java/com/example/
├── runners/                    # Cucumber runners
│   ├── JUnitCucumberRunner.java
│   └── TestNGCucumberRunner.java
├── stepdefinitions/            # Cucumber step definitions
│   └── ExampleSteps.java
└── tests/
    ├── junit/                  # JUnit тесты
    │   ├── booking/
    │   └── people/
    └── selenium/               # Selenium тесты
        ├── classwork/
        └── homework/
```

## 🔧 Конфигурация

### TestNG
- Конфигурация: `src/test/resources/testng.xml`
- Запуск через профиль: `mvn test -Ptestng`

### Cucumber
- Feature файлы: `src/test/resources/features/`
- Runners: `src/test/java/com/example/runners/`
- Отчеты: `target/cucumber-reports/`

## 📊 Версии зависимостей

- **Selenium**: 4.23.0
- **TestNG**: 7.10.2
- **JUnit**: 4.13.2
- **Cucumber**: 7.18.1
- **WebDriverManager**: 5.9.2

## 💡 Примеры

### Запуск только JUnit тестов для booking
```bash
mvn test -Pjunit -Dtest=**/booking/**
```

### Запуск TestNG с кастомным suite
```bash
mvn test -Ptestng -DsuiteXmlFile=src/test/resources/custom-suite.xml
```

### Запуск Cucumber с тегами
Отредактируйте runner и добавьте теги в `@CucumberOptions`:
```java
@CucumberOptions(
    tags = "@smoke"
)
```

## 🎯 Полезные команды

```bash
# Компиляция без тестов
mvn clean compile

# Компиляция с тестами
mvn clean test-compile

# Пропустить тесты при сборке
mvn clean install -DskipTests

# Запуск конкретного теста
mvn test -Dtest=BookingLondonTest
```
