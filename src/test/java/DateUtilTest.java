import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

// new Objectをモックするテスト
@RunWith(JMockit.class)
public class DateUtilTest {	
	@Test
	public void testGetCurrentDate() {
		// 期待値は2015/05/05
		Calendar c = Calendar.getInstance();
		c.set(2015, 4, 5);
		Date expected = c.getTime();

		// 記録フェーズ
		new MockUp<Date>() {
			@Mock
			public void $init() {
				this.getMockInstance().setTime(expected.getTime());
			}
		};
		
		// リプレイフェーズ
		String strDate = DateUtil.getCurrentDate();
		
		// 検証フェーズ
		assertThat(strDate, is("2015/05/05"));
	}
}
