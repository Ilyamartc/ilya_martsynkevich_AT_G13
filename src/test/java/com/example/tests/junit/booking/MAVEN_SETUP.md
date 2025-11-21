# Установка Maven и запуск тестов

## Быстрый способ: Использовать IntelliJ IDEA

1. Откройте проект в IntelliJ IDEA
2. Справа нажмите на "Maven" вкладку
3. Разверните: ProjectNew → Lifecycle
4. Двойной клик на "test"

**ИЛИ**

1. Правый клик на тест-классе в редакторе
2. Select "Run 'BookingCurrencyExtractTest'" или другой класс
3. IDEA автоматически запустит тест

---

## Установка Maven вручную (если нужен CLI):

### Вариант 1: Через Chocolatey (если установлен):

```powershell
choco install maven
```

### Вариант 2: Скачать и установить вручную:

1. **Скачайте Maven 3.9.6:**

   - Перейдите на https://maven.apache.org/download.cgi
   - Скачайте "apache-maven-3.9.6-bin.zip"

2. **Распакуйте в C:\Maven:**

   ```
   C:\Maven\apache-maven-3.9.6\bin\mvn.cmd
   ```

3. **Добавьте в PATH (Windows):**

   - Нажмите Win+X → System
   - Нажмите "Environment Variables" внизу
   - Нажмите "Path" → Edit
   - Добавьте новую строку: `C:\Maven\apache-maven-3.9.6\bin`
   - OK → OK → OK
   - Перезагрузитесь

4. **Проверьте установку:**
   ```powershell
   mvn --version
   ```

---

## Запуск тестов из командной строки (после установки Maven):

```powershell
cd C:\Users\ILYA.MARTSYNKEVICH\IdeaProjects\ProjectNew

# Запустить ВСЕ тесты
mvn test

# Запустить конкретный тест
mvn test -Dtest=BookingCurrencyExtractTest

# Запустить все тесты пакета booking
mvn test -Dtest=tests.junit.booking.**

# Запустить с чистой сборкой
mvn clean test
```

---

## Альтернатива: Запуск через Gradle (если в проекте есть Gradle)

```powershell
.\gradlew test
```

---

## Проверка настроек проекта:

Убедитесь, что `pom.xml` содержит JUnit 4:

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.23.0</version>
</dependency>
```

---

## Если Maven установлен, но не работает:

1. Проверьте переменную окружения JAVA_HOME:

   ```powershell
   $env:JAVA_HOME
   ```

   Должна вывести путь к Java, например: `C:\Program Files\Java\jdk-23`

2. Если не установлена, добавьте:

   ```powershell
   [Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-23", "User")
   ```

3. Перезагрузитесь и попробуйте снова:
   ```powershell
   mvn --version
   ```

---

## Рекомендуемый подход:

✅ **Используйте IntelliJ IDEA** - это самый простой способ без установки Maven в систему. IDEA встроила Maven.
