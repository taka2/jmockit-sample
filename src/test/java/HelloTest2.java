import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

// @Mocked/@Injectableの違いを調べるサンプル
@RunWith(JMockit.class)
public class HelloTest2 {	
	@Test
	public void testHelloMocked(@Mocked Hello hello) {
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
		
		// 検証フェーズ
		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
	}

	@Test
	public void testHelloInjectable(@Injectable Hello hello) {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		final String expectedReal1 = "helloInstance1";
		final String expectedReal2 = "helloInstance2";
		// 記録フェーズ
		new Expectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		Hello hello1 = new Hello();
		Hello hello2 = new Hello();
		String resultHelloInstance1 = hello.helloInstance1();
		String resultHelloInstance2 = hello.helloInstance2();
		String resultHello1Instance1 = hello1.helloInstance1();
		String resultHello2Instance2 = hello2.helloInstance2();
		
		// 検証フェーズ
		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
		assertEquals(expectedReal1, resultHello1Instance1);
		assertEquals(expectedReal2, resultHello2Instance2);
	}
}
