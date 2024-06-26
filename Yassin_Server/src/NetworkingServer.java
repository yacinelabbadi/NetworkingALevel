import java.io.*;
import java.net.*;

public class NetworkingServer {
    public static void main(String[] args) throws Exception {

        // default port number we are going to use
        int portNumber = 4711;

        // The if-statement for args > 0 is not necessary
        // since I won't be sending in command line arguments

        // Creating the server socket
        ServerSocket server = new ServerSocket(portNumber);
        System.out.println("Server socket has been created.");

        // Listens and waits until a connection is made
        System.out.println("Waiting for a request...");
        Socket client = server.accept();

        // Prints out that a connect request has been accepted and prints out the clients host address and port
        System.out.println("Connection has been made.");
        String clientHost = client.getInetAddress().getHostAddress();
        System.out.println("Clients host: " + clientHost + "\nClient port: " + client.getPort());

        // Create InputStream and BufferedReader to read data from client
        InputStream clientInput = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(clientInput));

        // Create OutputStream and PrintWriter to send data to client
        OutputStream clientOutput = client.getOutputStream();
        PrintWriter pw = new PrintWriter(clientOutput, true);


        // Wait for data from client and respond
        while (true) {

            String clientMessage = br.readLine();
            String response = responseMessage(clientMessage);

            // Exit and close if client sends message "bye"
            if (clientMessage != null && clientMessage.equalsIgnoreCase("bye")) {
                pw.println("Closing server, goodbye");
                br.close();
                pw.close();
                server.close();
                client.close();
                break;
            } else if (clientMessage != null) {
                pw.println(response);
            }
        }
    }

    // Method for making calculating and making a response to the clients message
    // Uses indexOf to find the operation sign position and takes the numbers before and after it
    // and uses corresponding operation to calculate the answer and make a response
    public static String responseMessage(String clientMessage) {
        double answer;
        double firstNumber;
        double secondNumber;
        String response = "";

        try {
            int operationPosition;
            if (clientMessage != null) {
                clientMessage = clientMessage.replaceAll(" ", "");
                if (clientMessage.contains("+")) {
                    operationPosition = clientMessage.indexOf("+");
                    firstNumber = Double.parseDouble(clientMessage.substring(0, operationPosition));
                    secondNumber = Double.parseDouble(clientMessage.substring(operationPosition+1));
                    answer = firstNumber + secondNumber;

                    response = "The sum of " + clientMessage + " is " + answer;
                } else if (clientMessage.contains("*")) {
                    operationPosition = clientMessage.indexOf("*");
                    firstNumber = Double.parseDouble(clientMessage.substring(0, operationPosition));
                    secondNumber = Double.parseDouble(clientMessage.substring(operationPosition+1));
                    answer = firstNumber * secondNumber;

                    response = "The product of " + clientMessage + " is " + answer;
                } else if (clientMessage.contains("/")) {
                    operationPosition = clientMessage.indexOf("/");
                    firstNumber = Double.parseDouble(clientMessage.substring(0, operationPosition));
                    secondNumber = Double.parseDouble(clientMessage.substring(operationPosition+1));
                    answer = firstNumber / secondNumber;

                    response = "The quotient of " + clientMessage + " is " + answer;
                } else if (clientMessage.contains("-")) {
                    operationPosition = clientMessage.indexOf("-");
                    firstNumber = Double.parseDouble(clientMessage.substring(0, operationPosition));
                    secondNumber = Double.parseDouble(clientMessage.substring(operationPosition+1));
                    answer = firstNumber - secondNumber;

                    response = "The difference of " + clientMessage + " is " + answer;
                } else {
                    response = "Instructions were not followed, please try again.";
                }
            }
        } catch (Exception e) {
            response = "Instructions were not followed, please try again";
        }
        return response;
    }
}