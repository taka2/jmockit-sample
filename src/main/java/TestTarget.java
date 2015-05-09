public class TestTarget {
	private int primitiveTypeMember;
	private Integer referenceTypeMember;
	private static int staticPrimitiveTypeMember;
	private static Integer staticReferenceTypeMember;
	private static final int staticFinalPrimitiveTypeMember = 5;
	private static final Integer staticFinalReferenceTypeMember = 6;

	private TestTarget() {
		this.primitiveTypeMember = 1;
		this.referenceTypeMember = 2;
		TestTarget.staticPrimitiveTypeMember = 3;
		TestTarget.staticReferenceTypeMember = 4;
	}

	private final int primitiveTypeMethod() {
		return 10;
	}
	private final Integer referenceTypeMethod() {
		return 20;
	}
	private static final int staticPrimitiveTypeMethod() {
		return 30;
	}
	private static final Integer staticReferenceTypeMethod() {
		return 40;
	}
}
