# Справка по запуску тестов

## TestNG Тесты

### Запуск всех TestNG тестов
```bash
mvn test -Ptestng
```

### Запуск только people тестов через testng-people.xml
```bash
mvn test -Ptestng -DsuiteXmlFile=src/test/resources/testng-people.xml
```

### Результаты TestNG
- **Всего тестов**: 18
- **AutomatedEngineerTest**: 9 тестов (3 test methods × 3 data sets)
- **ManualEngineerTest**: 9 тестов (3 test methods × 3 data sets)
- **EngineerSuiteTest**: дополнительные тесты с группами smoke/regression

### Группы тестов в testng.xml
- `People Tests` - все тесты классов AutomatedEngineerTest, ManualEngineerTest, EngineerSuiteTest
- `Smoke Tests` - тесты с аннотацией groups = "smoke"
- `Regression Tests` - тесты с аннотацией groups = "regression"

---

## Cucumber Тесты

### Запуск всех Cucumber тестов
```bash
mvn test -Pcucumber
```

### Результаты Cucumber
- **Всего сценариев**: 17
- **automated-engineer.feature**: 7 сценариев
- **manual-engineer.feature**: 7 сценариев
- **engineer-comparison.feature**: 2 сценария
- **example.feature**: 1 сценарий

### Feature файлы
- `src/test/resources/features/automated-engineer.feature` - тесты для AutomatedEngineer
- `src/test/resources/features/manual-engineer.feature` - тесты для ManualEngineer
- `src/test/resources/features/engineer-comparison.feature` - сравнение типов инженеров

### Step Definitions
- `com.example.stepdefinitions.EngineerSteps` - шаги для работы с Engineer классами
- `com.example.stepdefinitions.ExampleSteps` - примеры шагов

### Cucumber Runner
- `com.example.runners.PeopleTestRunner` - TestNG runner для запуска Cucumber сценариев

---

## JUnit Тесты

### Запуск всех JUnit тестов
```bash
mvn test -Pjunit
```

### Тесты JUnit
- `com.example.tests.junit.people.AutomatedEngineerTest` - параметризованные JUnit тесты
- `com.example.tests.junit.people.ManualEngineerTest` - параметризованные JUnit тесты

---

## Selenium Тесты

### Запуск всех Selenium тестов
```bash
mvn test -Pselenium
```

---

## Запуск всех тестов сразу

```bash
mvn test -Pall-tests
```

---

## Отчеты

### TestNG отчеты
- `target/surefire-reports/` - XML отчеты
- `target/surefire-reports/index.html` - HTML отчет TestNG

### Cucumber отчеты
- `target/cucumber-reports/cucumber.html` - HTML отчет
- `target/cucumber-reports/cucumber.json` - JSON отчет
- `target/cucumber-reports/cucumber.xml` - XML отчет

---

## Структура тестов

```
src/test/
├── java/com/example/
│   ├── runners/
│   │   └── PeopleTestRunner.java          # Cucumber TestNG runner
│   ├── stepdefinitions/
│   │   └── EngineerSteps.java             # Cucumber step definitions
│   └── tests/
│       ├── junit/people/                   # JUnit тесты
│       ├── testng/people/                  # TestNG тесты
│       ├── selenium/                       # Selenium тесты
│       └── cucumber/                       # (зарезервировано)
└── resources/
    ├── features/                           # Cucumber feature файлы
    ├── testng.xml                          # Основная конфигурация TestNG
    └── testng-people.xml                   # Конфигурация для people тестов
```
