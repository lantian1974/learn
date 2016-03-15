
import scala.collection.mutable.HashSet
object SetAndMap 
{
  
    /**由下段代码所示：不可变的集合在做+操作时，实际上生成了一个新对象，并将新对象的引用赋值给了inmutableSet。
           这里用inmuteableSetRef保持了原引用，所以才能观察到这种现象。若将inmutableSet定义为val,
           则语句，this.inmutableSet+="cde";会编译出错。
    *  
     */
  def testInmutableSet
   {
    //val inmutableSet=Set("abc","bcd");  
    var inmutableSet=Set("abc","bcd"); 
     
    //inmuteableSetRef永远持有inmutableSet最初的引用。
      val inmuteableSetRef:AnyRef=inmutableSet;
      println(inmuteableSetRef);
      //若将inmutableSet定义为val,则下面的语句会编译报错。
      inmutableSet+="cde";
      println(inmutableSet);
      println(inmuteableSetRef);
      if( inmuteableSetRef==inmutableSet)
        println("对于不可变set，+操作没有产生新对象")
      else
        println("对于不可变set，+操作产生了新对象")
     
   }
   def main(args:Array[String])
   {
     
      testInmutableSet;
      testMutableSet();
      testMap;
   }
   def testMutableSet()
   {
     val mutableSet=HashSet("adc_mutable","bcd_mutable");
     val mutableSetRef:Any  =mutableSet;
     println(mutableSetRef);
     mutableSet+="cde_mutable";
     println(mutableSet);
     println(mutableSetRef);
      if( mutableSetRef==mutableSet)
        println("对于可变set，+操作没有产生新对象")
      else
        println("对于可变set，+操作产生了新对象")
   //  var mutableSet2:HashSet[String]=
       if (mutableSetRef.isInstanceOf[HashSet[String]])
          println("HashSet")
   }
   def testMap
   {
     var testMap=Map(1->"lantian",2->"qupengdong");
     println(testMap);
     println(testMap(2));
     println(1->2->3->4)
     
   }
}