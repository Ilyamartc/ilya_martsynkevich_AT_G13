package homework.day5;

public class StringStaticRunner {
    public static void main(String[] args) {

        Column.column(new String[]{"Hello world"});
        Duplicates.printDuplicates("hello world hello"); // 1.2
        Digits.printDigits("Test 123 number 456"); // 1.3

        String log = """
                access_log.2020.09.07 212.168.101.5 granted
                access_log.2020.09.07 212.168.101.6 denied
                access_log.2020.09.07 212.168.101.6 denied
                access_log.2020.09.07 212.168.122.6 denied
                access_log.2020.09.07 212.168.101.5 granted
                access_log.2020.09.07 212.168.101.5 denied""";
        LogAnalyzer.analyzeLog(log); // 1.4

        DateTimePrinter.printCurrentDateTime(); // 1.5
        DateTimeFormatterExample.formatDateTime("22.00 07.09.2020"); // 1.6
        VowelLetters.generateVowelDate("Привет, как дела?"); // 1.7
    }
}
