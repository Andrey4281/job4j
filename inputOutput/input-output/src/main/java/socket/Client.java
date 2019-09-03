package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private InputStream in;

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("localhost"), 8080)) {
            Client client = new Client(socket, System.in);
            client.begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client(Socket socket, InputStream in) {
        this.socket = socket;
        this.in = in;
    }

    public void begin() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(this.in);
        System.out.println("Enter questions for Oracle");
        String question;
        do {
            question = console.nextLine();
            out.println(question);
            String str = in.readLine();
            while (!str.isEmpty()) {
                System.out.println(str);
                str = in.readLine();
            }
        } while (!question.equals("exit"));
    }
}
