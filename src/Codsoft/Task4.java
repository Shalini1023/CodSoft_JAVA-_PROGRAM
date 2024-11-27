package Codsoft;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Task4 {
    static class Question {
        String question;
        String[] options;
        String correctAnswer;

        Question(String question, String[] options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
    static Question[] quizData = {
        new Question("What is the default value of a boolean variable in Java?", 
                     new String[] {"true", "false", "null", "undefined"}, "false"),
        new Question("Which of the following is NOT a primitive data type in Java?", 
                     new String[] {"int", "double", "char", "String"}, "String"),
        new Question("What is the purpose of the finally block in a try-catch statement?", 
                     new String[] {"To catch exceptions", "To handle errors", 
                    		 "To execute code regardless of exceptions", 
                    		 "To skip code execution"}, "To execute code regardless of exceptions"),
        new Question("What is the purpose of the this keyword in Java?", 
                     new String[] {"To refer to the current object", 
                    		 "To refer to the parent class", 
                    		 "To refer to the child class", 
                    		 "To refer to a static method"}, "To refer to the current object"),
        new Question("What is the purpose of the super keyword in Java?", 
                     new String[] {"To refer to the parent class", 
                    		 "To refer to the child class", 
                    		 "To refer to the current object", 
                    		 "To refer to a static method"}, "To refer to the parent class")
    };
    static class TimerHandler {
        boolean timeUp = false;

        public void startTimer(int seconds) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    timeUp = true;
                }
            }, seconds * 1000);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        String[] userAnswers = new String[quizData.length];
        TimerHandler timerHandler = new TimerHandler();

        System.out.println("<<Welcome to the quiz! Let's check the Java knowledge!>>");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < quizData.length; i++) {
            Question currentQuestion = quizData[i];
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.question);

            for (int j = 0; j < currentQuestion.options.length; j++) {
                System.out.println((j + 1) + ". " + currentQuestion.options[j]);
            }

            timerHandler.timeUp = false;
            timerHandler.startTimer(20);

            System.out.println("\nYou have 20 seconds to answer."
            		+ " Please enter the number of your choice.");
            String userAnswer = null;
            long startTime = System.currentTimeMillis();

            while (!timerHandler.timeUp && (System.currentTimeMillis() - startTime) < 20000) {
                if (scanner.hasNextLine()) {
                    userAnswer = scanner.nextLine();
                    break;
                }
            }

            if (userAnswer == null || timerHandler.timeUp) {
                System.out.println("Time's up! No answer provided.");
                userAnswers[i] = null;
            } else {
                userAnswers[i] = currentQuestion.options[Integer.parseInt(userAnswer) - 1];
            }

            if (userAnswers[i] != null && userAnswers[i].equals(currentQuestion.correctAnswer)) {
                score++;
            }
        }

        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score: " + score + "/" + quizData.length);
        
        for (int i = 0; i < quizData.length; i++) {
            String correctAnswer = quizData[i].correctAnswer;
            String userAnswer = userAnswers[i];
            String result = (userAnswer != null && userAnswer.equals(correctAnswer)) ? 
            		"Correct" : "Incorrect";
            if (userAnswer == null) {
                result = "No Answer (Time's up)";
            }
            System.out.println("Question " + (i + 1) + ": " + result + 
            		" (Your answer: " + (userAnswer != null ? userAnswer : "No Answer") + ")");
        }

        scanner.close();
    }
}
