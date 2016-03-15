
import scala.collection.mutable.HashMap
object SingletonScalaObject 
{
  val properties= HashMap[Symbol,String]();
  def printString=println("hello everyone");
  
  def updateProperty0(p:Symbol,v:Any):Unit=this.properties+=(p->v.toString());
 
  def updateProperty1(p:Symbol,v:Any)=
  {
      this.properties+=(p->v.toString());
  }
  def updateProperty2(p:Symbol,v:Any):Unit=
  {
      this.properties+=(p->v.toString());
  }
    def max(x:Int,y:Int):Int=
     {
        if(x>y) 
           x;
         else
           y;
     }
     def max2(x:Int,y:Int):Int=if(x>y) x else y;
  def main(args:Array[String])
  {
     println("this is my first scala program!");
     val code1:String="c001"
     val code2:String="c002"
     val s1:Symbol=Symbol(code1);
     val s2:Symbol=Symbol(code2);
     this.properties+=(s1->"lantian");
     this.properties+=(s2->"qupengdong");
     this.properties.foreach(println);
     this.updateProperty0('c001,"lantian2");
     this.updateProperty1('c002, "qupengdong2");
     this.properties.foreach(println);
     println(max(1,2));
     println(max(3,4));
     
     for(s<-this.properties)
     {
       println(s);
       println(s._1);
       println(s._2);
     }
  }  
   
}