import scala.collection.mutable.ListBuffer


object ListAndTuple {
  val id=0;
  val code="c001";
  val name="lan tian";
  val tuple2:Tuple2[Int,String]=Tuple2(id,code);
  val tuple2other:Tuple2[Int,String]=new Tuple2(id,code);
  val tuple3=(id,code,name);
  val tuple22=(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22); 
  //scala 元组方面最多支持22元组，下面语句是23元组则无法编译。
  //val tuple23=(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23); 
    def main(args: Array[String]) {
    val data = 1 to 200000
    val currntTime =System.currentTimeMillis()
    increase_ListBuffer(data.toList)
    println("used time=" + (System.currentTimeMillis() - currntTime))
    println(this.tuple2); 
    println(this.tuple2other);
    println(this.tuple3);
  }

  //listBuffer
  def increase_ListBuffer(list:List[Int]) :List[Int]={
    import scala.collection.mutable.ListBuffer
    var result = ListBuffer[Int]()
    for(element <- list){
      result += element+1
    }
    result.toList
  }

  
  
 
  
}