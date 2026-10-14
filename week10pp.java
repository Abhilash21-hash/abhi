import java.util.Arrays;

public class Main {

    // Question 1: Vowel and Consonant Counter
    static void question1() {
        String text = "Programming";
        int vowels = 0, consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));

            if (ch >= 'a' && ch <= 'z') {
                if ("aeiou".indexOf(ch) != -1)
                    vowels++;
                else
                    consonants++;
            }
        }

        System.out.println("Question 1: Vowel and Consonant Counter");
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println();
    }

    // Question 2: Word Reverser and Palindrome Checker
    static void question2() {
        String word = "level";
        String reversed = new StringBuilder(word).reverse().toString();

        System.out.println("Question 2: Word Reverser and Palindrome Checker");
        System.out.println("Reversed word: " + reversed);

        if (word.equalsIgnoreCase(reversed))
            System.out.println("Palindrome");
        else
            System.out.println("Not a palindrome");

        System.out.println();
    }

    // Question 3: Even and Odd Numbers in a List
    static void question3() {
        int[] numbers = {3, 8, 12, 5, 7, 10};
        int even = 0, odd = 0;

        for (int number : numbers) {
            if (number % 2 == 0)
                even++;
            else
                odd++;
        }

        System.out.println("Question 3: Even and Odd Numbers in a List");
        System.out.println("Even numbers: " + even);
        System.out.println("Odd numbers: " + odd);
        System.out.println();
    }

    // Question 4: In-Place Array Reversal
    static void question4() {
        int[] numbers = {11, 22, 33, 44};
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
            left++;
            right--;
        }

        System.out.println("Question 4: In-Place Array Reversal");
        System.out.println("Reversed array: " + Arrays.toString(numbers));
        System.out.println();
    }

    // Question 5: Digit Sum and Number Reversal
    static void question5() {
        int number = 4721;
        int temp = number, sum = 0, reversed = 0;

        while (temp > 0) {
            int digit = temp % 10;
            sum += digit;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }

        System.out.println("Question 5: Digit Sum and Number Reversal");
        System.out.println("Digit sum: " + sum);
        System.out.println("Reversed number: " + reversed);
        System.out.println();
    }

    // Question 6: Prime Number Checker
    static void question6() {
        int number = 29;
        boolean isPrime = number > 1;

        for (int i = 2; i <= number / i && isPrime; i++) {
            if (number % i == 0)
                isPrime = false;
        }

        System.out.println("Question 6: Prime Number Checker");

        if (isPrime)
            System.out.println(number + " is a prime number");
        else
            System.out.println(number + " is not a prime number");

        System.out.println();
    }

    // Question 7: Factorial Calculation
    static void question7() {
        int number = 5;
        long factorial = 1;

        for (int i = 1; i <= number; i++)
            factorial *= i;

        System.out.println("Question 7: Factorial Calculation");
        System.out.println("Factorial: " + factorial);
        System.out.println();
    }

    // Question 8: Student Grade Assignment
    static void question8() {
        int marks = 72;
        char grade;

        if (marks >= 90)
            grade = 'A';
        else if (marks >= 75)
            grade = 'B';
        else if (marks >= 60)
            grade = 'C';
        else if (marks >= 40)
            grade = 'D';
        else
            grade = 'F';

        System.out.println("Question 8: Student Grade Assignment");
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        System.out.println();
    }

    // Question 9: Leap Year Detector
    static void question9() {
        int year = 2024;

        System.out.println("Question 9: Leap Year Detector");

        if (year % 400 == 0 ||
            (year % 4 == 0 && year % 100 != 0))
            System.out.println(year + " is a leap year");
        else
            System.out.println(year + " is not a leap year");

        System.out.println();
    }

    // Question 10: Student Result Card Generator
    static class Student {
        String name;
        int[] marks;

        Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        void displayResult() {
            int total = 0;

            for (int mark : marks)
                total += mark;

            double average = (double) total / marks.length;
            char grade;

            if (average >= 90)
                grade = 'A';
            else if (average >= 75)
                grade = 'B';
            else if (average >= 60)
                grade = 'C';
            else if (average >= 40)
                grade = 'D';
            else
                grade = 'F';

            System.out.println("Student Name: " + name);
            System.out.println("Total Marks: " + total);
            System.out.printf("Average: %.1f%n", average);
            System.out.println("Grade: " + grade);
            System.out.println();
        }
    }

    static void question10() {
        System.out.println("Question 10: Student Result Card Generator");

        Student student1 = new Student("Asha", new int[]{80, 90, 70});
        Student student2 = new Student("Ravi", new int[]{60, 55, 50});

        student1.displayResult();
        student2.displayResult();
    }

    public static void main(String[] args) {
        //question1();
       // question2();
       // question3();
       // question4();
      //  question5();
       // question6();
       // question7();
       // question8();
       // question9(); 
        question10();
    }
}
