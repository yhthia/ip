public class MyChatBot {
    private static void greet() {
        System.out.println("Hello! I'm MyChatBot.");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {
        MyChatBot.greet();
        MyChatBot.exit();
    }
}
