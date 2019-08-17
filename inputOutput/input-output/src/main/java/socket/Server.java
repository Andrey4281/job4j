package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Socket socket;
    private Map<String, String> answersOfOracle;
    private static final  String EXIT = "exit";

    public static void main(String[] args) {
        System.out.println("We are waiting of connection of client");
        try (Socket socket = new ServerSocket(8080).accept()) {
            System.out.println("There are connection");
            Server server = new Server(socket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server(final Socket socket) {
        this.socket = socket;
        this.answersOfOracle = new HashMap<>();
        this.answersOfOracle.put("hello", "Hello, dear friend, I'm a oracle.");
        this.answersOfOracle.put("how are you?", "I am happy, and you?");
        this.answersOfOracle.put("Can I became programmer?", "Of course!");
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if (answersOfOracle.get(ask) != null) {
                out.println(answersOfOracle.get((ask)));
                out.println();
            } else if (!ask.equals(EXIT)) {
                out.println("It is difficult question for me");
                out.println();
            }

        } while (!"exit".equals(ask));
    }
}
