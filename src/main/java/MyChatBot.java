import java.util.Scanner;

public class MyChatBot {
    private final Scanner input = new Scanner(System.in);

    private void greet() {
        System.out.println("Hello! I'm MyChatBot.");
        System.out.println("What can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void echo() {
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                exit();
                break;
            }
            System.out.println(userInput);
        }
    }


    public static void main(String[] args) {
        MyChatBot chatbot = new MyChatBot();
        chatbot.greet();
        chatbot.echo();
    }
}
