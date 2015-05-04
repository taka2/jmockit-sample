public final class TestedUnit
{
   private final Service service1 = new ServiceImpl();
   private final Service service2 = new Service() { public int doSomething() { return 2; } };

   public int businessOperation()
   {
      return service1.doSomething() + service2.doSomething();
   }
}