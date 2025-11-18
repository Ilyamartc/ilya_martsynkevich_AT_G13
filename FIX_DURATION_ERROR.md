# РЕШЕНИЕ: Ошибка Duration в W3SchoolsGoogleTest

## Ошибка:

```
incompatible types: java.time.Duration cannot be converted to long
```

## Причина:

В методе `checkAndHandleRecaptcha()` неправильно использован `wait.wait()`:

```java
wait.wait(Duration.ofMillis(500));  // ❌ НЕПРАВИЛЬНО - Duration не поддерживается
```

## Решение: Используйте `Thread.sleep()` вместо `wait.wait()`

### Вариант 1: Исправить в IDE (БЫСТРО)

1. Откройте файл: `ProjectNew/src/test/java/tests/junit/booking/W3SchoolsGoogleTest.java`
2. Найдите строку: `wait.wait(Duration.ofMillis(500));`
3. Замените на:
   ```java
   try {
       Thread.sleep(500);
   } catch (InterruptedException e) {
       Thread.currentThread().interrupt();
   }
   ```
4. Ctrl+S - сохранить
5. Ctrl+Shift+F10 - запустить тест

### Вариант 2: Через командную строку

Используйте скрипт `fix-duration.bat`:

```cmd
cd "C:\Users\ILYA.MARTSYNKEVICH\IdeaProjects\ProjectNew"
fix-duration.bat
```

---

## После исправления:

```powershell
# Перезагрузитесь (если еще не перезагружались)
Restart-Computer

# После перезагрузки:
cd "C:\Users\ILYA.MARTSYNKEVICH\IdeaProjects\ProjectNew"
mvn test
```

Или просто используйте IntelliJ IDEA: правый клик на файле → Run Tests
