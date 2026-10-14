
public class Main {

    public static int countInBand(int[] scores, int low, int high) {
        int left = 0;
        int right = scores.length;

        // Find first position with score >= low
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] < low) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int first = left;

        left = 0;
        right = scores.length;

        // Find first position with score > high
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] <= high) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int last = left;

        return last - first;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        System.out.println(countInBand(scores, 42, 58));
        System.out.println(countInBand(scores, 90, 100));
    }
}

