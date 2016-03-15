trait Animal{
   def sound;
}
trait Furry extends  Animal//extends Animal
{
    def changeFurry()=println("changed furry!");
     override abstract def sound={println("bu bu bu");super.sound}
}
trait HasLeg extends Animal
{
   def walk()=println("i can walk");
   override abstract def sound={println("wu wu wu");super.sound}
}
abstract class Plant{
  def sound=println("wang wang wang");
}
class Dog   extends Plant with HasLeg with Furry
{
   //override def sound=println("wang wang wang");
}
object TestTrait 
{
   def main(args:Array[String])
   {
       println((new Dog()).sound);
   }
}