

object PartialFunction
{
    def sum(a:Int,b:Int,c:Int)=a+b+c;
    
    /**下面是一个闭包函数，闭包相当于声明了一个函数模板，用不同的值代入模板参数中，就可以产生不同的函数对象实例。
           这里，addMore就是一个闭包函数，相当于一个函数模板，more是模板参数。
    * */
    def addMore(more:Int)=(x:Int)=>{x+more}
    /**当函数的参数为类型相同（都是T），但个数未知的重复参数时，可以用T*表示多个，相当于传入了一个Array[T] 。
     * 如下所示：
    * */
    def sumInt(intVals:Int*):Int=
    {
      var sum:Int=0;
      for(x<-intVals)
      {
         println(x);
         sum+=x;
      }
      sum;
    }
    
    def main(args:Array[String])
    {
      //部分应用函数是指函数的部分参数被设定为默认值后，可创建新的函数对象。
      val sum1=sum(3,_:Int,5);
      val sum2=sum(_:Int,_:Int,6);
      println(sum1(6));
      println(sum2(2,8));
      //用将5代入闭包函数模板，产生一个新的函数对象实例。
      val addFive=addMore(5);
      println(addFive(6));
      //用将6代入闭包函数模板，产生一个新的函数对象实例。
      val addSix=addMore(6);
      println(addSix(8));
      println(sumInt(1,2,3,4,5));
      println("result is: "+ testReturn);
    }
    def testReturn():Int=
    {
       val i:Int=3;
       println("it is "+i);
      return i;
       5;
    }
    
    
}