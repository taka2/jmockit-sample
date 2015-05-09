import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class XXLogicTest {
	@Test
	public void testGetXX(@Mocked XXDao dao) {
		// 記録フェーズ
		String expectedResult = "YY";
		new Expectations() {{
			dao.getXX(); result = expectedResult;
		}};
		
		// リプレイフェーズ
		XXLogic logic = new XXLogic();
		String result = logic.getXX();
		
		// 検証フェーズ
		new Verifications() {{
			// dao#getXX()は1回だけ呼ばれた
			dao.getXX(); minTimes = 1; maxTimes = 1;
		}};
		
		assertThat(result, is(expectedResult));
	}
}
