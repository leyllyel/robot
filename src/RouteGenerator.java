import java.util.Random;

public  class RouteGenerator implements Runnable {
    private static final String letters = "RLRFR";
    private static final int length = 100;

    @Override
    public void run() {
        String route = generateRoute(letters, length);
        int count = countTurns(route);
        synchronized (Main.sizeToFreq) {
            if (Main.sizeToFreq.containsKey(count)) {
                Main.sizeToFreq.put(count, Main.sizeToFreq.get(count) + 1);
            } else {
                Main.sizeToFreq.put(count, 1);
            }
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static int countTurns(String route) {
        int count = 0;
        for (char c : route.toCharArray()) {
            if (c == 'R') {
                count++;
            }
        }
        return count;
    }
}
