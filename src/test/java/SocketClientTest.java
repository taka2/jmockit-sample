import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.Socket;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;

public class SocketClientTest {
	@Test
	public void testConnect() {
		// 記録フェーズ
		String expectedHost = "test.example.com";
		int expectedPort = 1234;
		new MockUp<Socket>() {
			@Mock
			void $init(String host, int port) {
				assertThat(host, is(expectedHost));
				assertThat(port, is(expectedPort));
			}
		};
		
		// リプレイフェーズ
		try {
			Socket s = new SocketClient().connect(expectedHost, expectedPort);
		} catch(IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testConnectFail() {
		// 記録フェーズ
		String expectedHost = "test.example.com";
		int expectedPort = 1234;
		new MockUp<Socket>() {
			@Mock
			void $init(String host, int port) throws IOException {
				throw new IOException("host = " + host + ", port = " + port);
			}
		};
		
		// リプレイフェーズ
		try {
			Socket s = new SocketClient().connect(expectedHost, expectedPort);
			fail();
		} catch(IOException e) {
			// 例外がスローされることを期待
		}
	}
}
