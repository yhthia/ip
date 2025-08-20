import java.util.Scanner;
import java.util.ArrayList;


public class MyChatBot {
    private final Scanner input = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();


    private void greet() {
        System.out.println("Hello! I'm MyChatBot.");
        System.out.println("What can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void add(String userInput) {
        list.add(userInput);
        System.out.println("added: " + userInput);
    }

    private void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + list.get(i));
        }
    }

    private void echo() {
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                exit();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else {
                add(userInput);
            }
        }
    }


    public static void main(String[] args) {
        MyChatBot chatbot = new MyChatBot();
        chatbot.greet();
        chatbot.echo();
    }
}
