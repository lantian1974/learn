

object PartialFunction
{
    def sum(a:Int,b:Int,c:Int)=a+b+c;
    
    /**������һ���հ��������հ��൱��������һ������ģ�壬�ò�ͬ��ֵ����ģ������У��Ϳ��Բ�����ͬ�ĺ�������ʵ����
           ���addMore����һ���հ��������൱��һ������ģ�壬more��ģ�������
    * */
    def addMore(more:Int)=(x:Int)=>{x+more}
    /**�������Ĳ���Ϊ������ͬ������T����������δ֪���ظ�����ʱ��������T*��ʾ������൱�ڴ�����һ��Array[T] ��
     * ������ʾ��
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
      //����Ӧ�ú�����ָ�����Ĳ��ֲ������趨ΪĬ��ֵ�󣬿ɴ����µĺ�������
      val sum1=sum(3,_:Int,5);
      val sum2=sum(_:Int,_:Int,6);
      println(sum1(6));
      println(sum2(2,8));
      //�ý�5����հ�����ģ�壬����һ���µĺ�������ʵ����
      val addFive=addMore(5);
      println(addFive(6));
      //�ý�6����հ�����ģ�壬����һ���µĺ�������ʵ����
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