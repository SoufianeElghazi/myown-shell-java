import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MyShell {

    public static void main(String[] args) {
        MyShell shell = new MyShell();
        shell.run();
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        System.out.println("Welcome to MyShell  (Soufiane). Type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            try {
                command = reader.readLine();
                if (command.equals("exit")) {
                    break;
                }
                executeCommand(command);
            } catch (IOException e) {
                System.out.println("Error reading command: " + e.getMessage());
            }
        }
    }

    private void executeCommand(String command) {
        String[] tokens = command.split(" ");
        switch (tokens[0]) {
            case "greet":
                if (tokens.length > 1) {
                    System.out.println("Hello, " + tokens[1] + "!");
                } else {
                    System.out.println("Hello!");
                }
                break;
            case "add":
                if (tokens.length > 2) {
                    try {
                        int a = Integer.parseInt(tokens[1]);
                        int b = Integer.parseInt(tokens[2]);
                        System.out.println("Result: " + (a + b));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numbers: " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: add <num1> <num2>");
                }
                break;
            case "subtract":
                if (tokens.length > 2) {
                    try {
                        int a = Integer.parseInt(tokens[1]);
                        int b = Integer.parseInt(tokens[2]);
                        System.out.println("Result: " + (a - b));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numbers: " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: subtract <num1> <num2>");
                }
                break;
            case "multiply":
                if (tokens.length > 2) {
                    try {
                        int a = Integer.parseInt(tokens[1]);
                        int b = Integer.parseInt(tokens[2]);
                        System.out.println("Result: " + (a * b));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numbers: " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: multiply <num1> <num2>");
                }
                break;
            case "divide":
                if (tokens.length > 2) {
                    try {
                        int a = Integer.parseInt(tokens[1]);
                        int b = Integer.parseInt(tokens[2]);
                        if (b != 0) {
                            System.out.println("Result: " + (a / (double) b));
                        } else {
                            System.out.println("Cannot divide by zero.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numbers: " + e.getMessage());
                    }
                } else {
                    System.out.println("Usage: divide <num1> <num2>");
                }
                break;
            default:
                try {
                    ProcessBuilder builder;
                    if (isWindows()) {
                        builder = new ProcessBuilder("cmd.exe", "/c", command);
                    } else {
                        builder = new ProcessBuilder("sh", "-c", command);
                    }
                    builder.redirectErrorStream(true);
                    Process process = builder.start();
                    BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = processReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    System.out.println("Error executing command: " + e.getMessage());
                }
        }
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

}

