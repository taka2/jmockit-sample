import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.StrictExpectations;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class HelloTest {
	@Mocked
	private Hello hello;
	
	@Test
	public void testHelloExpectations() {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new Expectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		String resultHelloInstance1 = hello.helloInstance1();
		String resultHelloInstance2 = hello.helloInstance2();
		
		// 検証フェーズ
		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
	}

	@Test
	public void testHelloExpectationsFail() {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new Expectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		String resultHelloInstance1 = hello.helloInstance1();
		//String resultHelloInstance2 = hello.helloInstance2();
		
		// 検証フェーズ
		assertEquals(expected1, resultHelloInstance1);
		//assertEquals(expected2, resultHelloInstance2);
	}
	
	@Test
	public void testHelloNonStrictExpectations() {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new NonStrictExpectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		String resultHelloInstance1 = hello.helloInstance1();
		//String resultHelloInstance2 = hello.helloInstance2();
		
		// 検証フェーズ
		assertEquals(expected1, resultHelloInstance1);
		//assertEquals(expected2, resultHelloInstance2);
	}

	@Test
	public void testHelloStrictExpectationsFail() {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new StrictExpectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		String resultHelloInstance2 = hello.helloInstance2();
		String resultHelloInstance1 = hello.helloInstance1();
		
		// 検証フェーズ
		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
	}

	@Test
	public void testHelloStrictExpectations() {
		final String expected1 = "mockedHelloInstance1";
		final String expected2 = "mockedHelloInstance2";
		// 記録フェーズ
		new StrictExpectations() {{
			hello.helloInstance1(); result = expected1;
			hello.helloInstance2(); result = expected2;
		}};
		
		// リプレイフェーズ
		String resultHelloInstance1 = hello.helloInstance1();
		String resultHelloInstance2 = hello.helloInstance2();
		
		// 検証フェーズ
		assertEquals(expected1, resultHelloInstance1);
		assertEquals(expected2, resultHelloInstance2);
	}
}
