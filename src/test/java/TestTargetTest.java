import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class TestTargetTest {
	/**
	 * private/static/finalメソッドをモックしない場合
	 */
	@Test
	public void testMethodWithoutMock() {
		// 記録フェーズ
		int expectedPrimitiveTypeMethodResult = 10;
		int expectedReferenceTypeMethodResult = 20;
		int expectedStaticPrimitiveTypeMethodResult = 30;
		int expectedStaticReferenceTypeMethodResult = 40;
		
		// リプレイフェーズ
		TestTarget tested = Deencapsulation.newInstance(TestTarget.class);
		int primitiveTypeMethodResult = Deencapsulation.invoke(tested, "primitiveTypeMethod");
		int referenceTypeMethodResult = Deencapsulation.invoke(tested, "referenceTypeMethod");
		int staticPrimitiveTypeMethodResult = Deencapsulation.invoke(TestTarget.class, "staticPrimitiveTypeMethod");
		int staticReferenceTypeMethodResult = Deencapsulation.invoke(TestTarget.class, "staticReferenceTypeMethod");
		
		// 検証フェーズ
		assertThat(primitiveTypeMethodResult, is(expectedPrimitiveTypeMethodResult));
		assertThat(referenceTypeMethodResult, is(expectedReferenceTypeMethodResult));
		assertThat(staticPrimitiveTypeMethodResult, is(expectedStaticPrimitiveTypeMethodResult));
		assertThat(staticReferenceTypeMethodResult, is(expectedStaticReferenceTypeMethodResult));
	}
	/**
	 * ExpecationsAPIを使ってprivate/static/finalメソッドをモックする
	 * @param target
	 */
	@Test
	public void testMethodWithExpectationsAPI(@Mocked TestTarget target) {
		// 記録フェーズ
		int expectedPrimitiveTypeMethodResult = 11;
		int expectedReferenceTypeMethodResult = 21;
		int expectedStaticPrimitiveTypeMethodResult = 31;
		int expectedStaticReferenceTypeMethodResult = 41;
		new Expectations() {{
			Deencapsulation.invoke(target, "primitiveTypeMethod"); result = expectedPrimitiveTypeMethodResult;
			Deencapsulation.invoke(target, "referenceTypeMethod"); result = expectedReferenceTypeMethodResult;
			Deencapsulation.invoke(TestTarget.class, "staticPrimitiveTypeMethod"); result = expectedStaticPrimitiveTypeMethodResult;
			Deencapsulation.invoke(TestTarget.class, "staticReferenceTypeMethod"); result = expectedStaticReferenceTypeMethodResult;
		}};
		
		// リプレイフェーズ
		TestTarget tested = Deencapsulation.newInstance(TestTarget.class);
		int primitiveTypeMethodResult = Deencapsulation.invoke(tested, "primitiveTypeMethod");
		int referenceTypeMethodResult = Deencapsulation.invoke(tested, "referenceTypeMethod");
		int staticPrimitiveTypeMethodResult = Deencapsulation.invoke(TestTarget.class, "staticPrimitiveTypeMethod");
		int staticReferenceTypeMethodResult = Deencapsulation.invoke(TestTarget.class, "staticReferenceTypeMethod");
		
		// 検証フェーズ
		assertThat(primitiveTypeMethodResult, is(expectedPrimitiveTypeMethodResult));
		assertThat(referenceTypeMethodResult, is(expectedReferenceTypeMethodResult));
		assertThat(staticPrimitiveTypeMethodResult, is(expectedStaticPrimitiveTypeMethodResult));
		assertThat(staticReferenceTypeMethodResult, is(expectedStaticReferenceTypeMethodResult));
	}

	/**
	 * MockupsAPIを使ってprivate/static/finalメソッドをモックする
	 */
	@Test
	public void testMethodWithMockupsAPI() {
		// 記録フェーズ
		int expectedPrimitiveTypeMethodResult = 11;
		int expectedReferenceTypeMethodResult = 21;
		int expectedStaticPrimitiveTypeMethodResult = 31;
		int expectedStaticReferenceTypeMethodResult = 41;
		new MockUp<TestTarget>() {
			@Mock
			int primitiveTypeMethod() {
				return expectedPrimitiveTypeMethodResult;
			}
			@Mock
			Integer referenceTypeMethod() {
				return expectedReferenceTypeMethodResult;
			}
			@Mock
			int staticPrimitiveTypeMethod() {
				return expectedStaticPrimitiveTypeMethodResult;
			}
			@Mock
			Integer staticReferenceTypeMethod() {
				return expectedStaticReferenceTypeMethodResult;
			}
		};
		
		// リプレイフェーズ
		TestTarget tested = Deencapsulation.newInstance(TestTarget.class);
		int primitiveTypeMethodResult = Deencapsulation.invoke(tested, "primitiveTypeMethod");
		int referenceTypeMethodResult = Deencapsulation.invoke(tested, "referenceTypeMethod");
		int staticPrimitiveTypeMethodResult = Deencapsulation.invoke(TestTarget.class, "staticPrimitiveTypeMethod");
		int staticReferenceTypeMethodResult = Deencapsulation.invoke(TestTarget.class, "staticReferenceTypeMethod");
		
		// 検証フェーズ
		assertThat(primitiveTypeMethodResult, is(expectedPrimitiveTypeMethodResult));
		assertThat(referenceTypeMethodResult, is(expectedReferenceTypeMethodResult));
		assertThat(staticPrimitiveTypeMethodResult, is(expectedStaticPrimitiveTypeMethodResult));
		assertThat(staticReferenceTypeMethodResult, is(expectedStaticReferenceTypeMethodResult));
	}
	
	/**
	 * private/static/finalメンバを置換しない場合
	 */
	@Test
	public void testMembersWithoutReplacement() {
		// 記録フェーズ
		int expectedPrimitiveTypeMember = 1;
		int expectedReferenceTypeMember = 2;
		int expectedStaticPrimitiveTypeMember = 3;
		int expectedStaticReferenceTypeMember = 4;
		int expectedStaticFinalPrimitiveTypeMember = 5;
		int expectedStaticFinalReferenceTypeMember = 6;
		
		// リプレイフェーズ
		TestTarget tested = Deencapsulation.newInstance(TestTarget.class);
		int primitiveTypeMemberResult = Deencapsulation.getField(tested, "primitiveTypeMember");
		int referenceTypeMemberResult = Deencapsulation.getField(tested, "referenceTypeMember");
		int staticPrimitiveTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticPrimitiveTypeMember");
		int staticReferenceTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticReferenceTypeMember");
		int staticFinalPrimitiveTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticFinalPrimitiveTypeMember");;
		int staticFinalReferenceTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticFinalReferenceTypeMember");
		
		// 検証フェーズ
		assertThat(primitiveTypeMemberResult, is(expectedPrimitiveTypeMember));
		assertThat(referenceTypeMemberResult, is(expectedReferenceTypeMember));
		assertThat(staticPrimitiveTypeMemberResult, is(expectedStaticPrimitiveTypeMember));
		assertThat(staticReferenceTypeMemberResult, is(expectedStaticReferenceTypeMember));
		assertThat(staticFinalPrimitiveTypeMemberResult, is(expectedStaticFinalPrimitiveTypeMember));
		assertThat(staticFinalReferenceTypeMemberResult, is(expectedStaticFinalReferenceTypeMember));
	}

	/**
	 * private/static/finalメンバを任意の値に置換する
	 */
	@Test
	public void testMembersWithReplacement() {
		// 記録フェーズ
		int expectedPrimitiveTypeMember = 101;
		int expectedReferenceTypeMember = 102;
		int expectedStaticPrimitiveTypeMember = 103;
		int expectedStaticReferenceTypeMember = 104;
		int expectedStaticFinalPrimitiveTypeMember = 105;
		int expectedStaticFinalReferenceTypeMember = 106;

		new MockUp<TestTarget>() {
			@Mock
			void $init() {
				Deencapsulation.setField(this.getMockInstance(), "primitiveTypeMember", expectedPrimitiveTypeMember);
				Deencapsulation.setField(this.getMockInstance(), "referenceTypeMember", expectedReferenceTypeMember);
			}
		};
		Deencapsulation.setField(TestTarget.class, "staticPrimitiveTypeMember", expectedStaticPrimitiveTypeMember);
		Deencapsulation.setField(TestTarget.class, "staticReferenceTypeMember", expectedStaticReferenceTypeMember);
		Deencapsulation.setField(TestTarget.class, "staticFinalPrimitiveTypeMember", expectedStaticFinalPrimitiveTypeMember);
		Deencapsulation.setField(TestTarget.class, "staticFinalReferenceTypeMember", expectedStaticFinalReferenceTypeMember);

		// リプレイフェーズ
		TestTarget tested = Deencapsulation.newInstance(TestTarget.class);
		int primitiveTypeMemberResult = Deencapsulation.getField(tested, "primitiveTypeMember");
		int referenceTypeMemberResult = Deencapsulation.getField(tested, "referenceTypeMember");
		int staticPrimitiveTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticPrimitiveTypeMember");
		int staticReferenceTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticReferenceTypeMember");
		int staticFinalPrimitiveTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticFinalPrimitiveTypeMember");;
		int staticFinalReferenceTypeMemberResult = Deencapsulation.getField(TestTarget.class, "staticFinalReferenceTypeMember");
		
		// 検証フェーズ
		assertThat(primitiveTypeMemberResult, is(expectedPrimitiveTypeMember));
		assertThat(referenceTypeMemberResult, is(expectedReferenceTypeMember));
		assertThat(staticPrimitiveTypeMemberResult, is(expectedStaticPrimitiveTypeMember));
		assertThat(staticReferenceTypeMemberResult, is(expectedStaticReferenceTypeMember));
		assertThat(staticFinalPrimitiveTypeMemberResult, is(expectedStaticFinalPrimitiveTypeMember));
		assertThat(staticFinalReferenceTypeMemberResult, is(expectedStaticFinalReferenceTypeMember));
	}
}
