package homework.day5;

public class LogAnalyzer {

    public static void analyzeLog(String logText) {
        if (logText == null || logText.isEmpty()) {
            System.out.println("Empty Log Text");
            return;
        }

        String[] lines = logText.split("\n");

        String[] ips = new String[lines.length];
        int[] okCounts = new int[lines.length];
        int[] failedCounts = new int[lines.length];
        int uniqueCount = 0;

        for (String s : lines) {
            String line = s.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");
            if (parts.length < 3) continue;

            String ip = parts[1];
            String status = parts[2];

            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (ips[j].equals(ip)) {
                    index = j;
                    break;
                }
            }

            if (index == -1) {
                ips[uniqueCount] = ip;
                if (status.equals("granted")) okCounts[uniqueCount] = 1;
                else if (status.equals("denied")) failedCounts[uniqueCount] = 1;
                uniqueCount++;
            } else {
                if (status.equals("granted")) okCounts[index]++;
                else if (status.equals("denied")) failedCounts[index]++;
            }
        }

        for (int i = 0; i < uniqueCount; i++) {
            System.out.println("ip " + ips[i] + ": ok - " + okCounts[i] + ", failed - " + failedCounts[i]);
        }
    }
}
