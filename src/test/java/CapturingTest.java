import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

public final class CapturingTest
{
   @Mocked Service anyService;

   @Test
   public void mockingImplementationClassesFromAGivenBaseType()
   {
      new Expectations() {{ anyService.doSomething(); returns(3, 4); }};

      int result = new TestedUnit().businessOperation();

      assertEquals(7, result);
   }
}