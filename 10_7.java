public class 10_7 {
public class Main {

    public static int secondHighest(int[] scores) {
        int highest = -1;
        int second = -1;

        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > highest) {
                second = highest;
                highest = scores[i];
            } else if (scores[i] < highest && scores[i] > second) {
                second = scores[i];
            }
        }

        return second;
    }

    public static void main(String[] args) {
        int[] scores = {45, 78, 92, 78, 60};

        System.out.println(secondHighest(scores));
    }
}
}
