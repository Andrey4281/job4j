package socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenSendExitShouldSaveExitForServer() throws IOException {
        testClient("exit", Joiner.on(LN).join("exit", ""));
    }

    private void testClient(String phrasesFromClient,
                            String expectedForServer) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream forServer = new ByteArrayOutputStream();
        ByteArrayInputStream fromServer = new
                ByteArrayInputStream(Joiner.on(LN).join("Hello", "", "").getBytes());
        when(socket.getInputStream()).thenReturn(fromServer);
        when(socket.getOutputStream()).thenReturn(forServer);
        Client client = new Client(socket, new ByteArrayInputStream(phrasesFromClient.getBytes()));

        client.begin();

        assertThat(forServer.toString(), is(expectedForServer));
    }
}