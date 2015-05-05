import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

// Verification/FullVerificationの違いを調べるサンプル
@RunWith(JMockit.class)
public class VerificationTest {	
	@Test
	public void testHelloVerification(@Mocked Hello hello) {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new Expectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		Hello hello1 = new Hello();
		Hello hello2 = new Hello();
		String resultHelloInstance1 = hello1.helloInstance1();
		String resultHelloInstance2 = hello2.helloInstance2();
		resultHelloInstance2 = hello2.helloInstance2();
		
		// 検証フェーズ	
		new Verifications() {{
			hello.helloInstance1(); times = 1;
			hello.helloInstance2(); minTimes = 1; maxTimes = 2;
		}};

		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
	}

	@Test
	public void testHelloFullVerificationFail(@Mocked Hello hello) {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new Expectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		Hello hello1 = new Hello();
		Hello hello2 = new Hello();
		String resultHelloInstance1 = hello1.helloInstance1();
		String resultHelloInstance2 = hello2.helloInstance2();
		resultHelloInstance2 = hello2.helloInstance2();
		
		// 検証フェーズ	
		new FullVerifications() {{
			hello.helloInstance1(); times = 1;
			hello.helloInstance2(); minTimes = 1; maxTimes = 2;
		}};

		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
	}

	@Test
	public void testHelloFullVerification(@Mocked Hello hello) {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new Expectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		Hello hello1 = new Hello();
		Hello hello2 = new Hello();
		String resultHelloInstance1 = hello1.helloInstance1();
		String resultHelloInstance2 = hello2.helloInstance2();
		resultHelloInstance2 = hello2.helloInstance2();
		
		// 検証フェーズ	
		new FullVerifications() {{
			new Hello(); times = 2;
			hello.helloInstance1(); times = 1;
			hello.helloInstance2(); minTimes = 1; maxTimes = 2;
		}};

		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
	}
}
