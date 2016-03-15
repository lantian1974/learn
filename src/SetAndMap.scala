
import scala.collection.mutable.HashSet
object SetAndMap 
{
  
    /**���¶δ�����ʾ�����ɱ�ļ�������+����ʱ��ʵ����������һ���¶��󣬲����¶�������ø�ֵ����inmutableSet��
           ������inmuteableSetRef������ԭ���ã����Բ��ܹ۲쵽������������inmutableSet����Ϊval,
           ����䣬this.inmutableSet+="cde";��������
    *  
     */
  def testInmutableSet
   {
    //val inmutableSet=Set("abc","bcd");  
    var inmutableSet=Set("abc","bcd"); 
     
    //inmuteableSetRef��Զ����inmutableSet��������á�
      val inmuteableSetRef:AnyRef=inmutableSet;
      println(inmuteableSetRef);
      //����inmutableSet����Ϊval,�������������뱨��
      inmutableSet+="cde";
      println(inmutableSet);
      println(inmuteableSetRef);
      if( inmuteableSetRef==inmutableSet)
        println("���ڲ��ɱ�set��+����û�в����¶���")
      else
        println("���ڲ��ɱ�set��+�����������¶���")
     
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
        println("���ڿɱ�set��+����û�в����¶���")
      else
        println("���ڿɱ�set��+�����������¶���")
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