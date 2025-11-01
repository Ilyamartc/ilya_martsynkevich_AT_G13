package homework.day5;

public class StringObjectRunner {
    public static void main(String[] args) {

        Column column = new Column();
        column.column(new String[]{});

        Duplicates duplicates = new Duplicates();
        duplicates.printDuplicates("hello world hello");

        Digits digits = new Digits();
        digits.printDigits("Test 123 number 456");

        LogAnalyzer logAnalyzer = new LogAnalyzer();
        String log = """
                access_log.2020.09.07 212.168.101.5 granted
                access_log.2020.09.07 212.168.101.6 denied
                access_log.2020.09.07 212.168.101.6 denied
                access_log.2020.09.07 212.168.122.6 denied
                access_log.2020.09.07 212.168.101.5 granted
                access_log.2020.09.07 212.168.101.5 denied""";
        logAnalyzer.analyzeLog(log);

        DateTimePrinter.printCurrentDateTime();

        DateTimeFormatterExample.formatDateTime("22.00 07.09.2020"); // 1.6 - форматирование даты

        VowelLetters.generateVowelDate("Привет, как дела?"); // 1.7 - гласная дата
    }
}
