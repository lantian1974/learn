package java8;

public interface Java8Interface 
{
  default void sayHello(String name)
  {
	  System.out.println("Hello "+name+" !  ");
  }
}
