import java.io.IOException;
import java.net.Socket;


public class SocketClient {
	public Socket connect(String host, int port) throws IOException {
		Socket s = new Socket(host, port);
		return s;
	}
}
