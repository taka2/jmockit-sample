import java.io.File;

import java.io.FileFilter;

import mockit.Capturing;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

// @Capturing/@Mockedの違いを調べるサンプル
@RunWith(JMockit.class)
public class CapturingTest2 {
	@Test
	public void testHelloMocked(@Mocked FileFilter filter) {
		// 記録フェーズ
		new Expectations() {{
			// filterはリプレイフェーズでロードされるので、ここではモックできない。
			filter.accept((java.io.File)any); returns(true, true, false);
		}};
		
		// リプレイフェーズ
		Hello hello = new Hello();
		File[] files = hello.getFiles();
		
		// 検証フェーズ
		for(File f : files) {
			System.out.println(f);
		}
		assertThat(files.length, is(2));
	}

	@Test
	public void testHelloCapturing(@Capturing FileFilter filter) {
		// 記録フェーズ
		new Expectations() {{
			// filterはリプレイフェーズでロードされるが、@Capturing指定しているのでここでモックできる。
			filter.accept((java.io.File)any); returns(true, true, false);
		}};
		
		// リプレイフェーズ
		Hello hello = new Hello();
		File[] files = hello.getFiles();
		
		// 検証フェーズ
		for(File f : files) {
			System.out.println(f);
		}
		assertThat(files.length, is(2));
	}
}
