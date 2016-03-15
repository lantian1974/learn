

object LeranArray 
{
     private val greetStrings=new Array[String](3);
     private val byebyStrings:Array[String]=Array[String]("byeby"," , ","every one!");
     private def  iniGreetStrings()=
     {
       this.greetStrings(0)="hello";
       this.greetStrings(1)=" , ";
       this.greetStrings(2)="every one!";
     }
     private def  printGreet1()=
     {
       
        this.greetStrings.foreach(println);
     }
     private def printGreet2()
     {
       var greets:String=""; 
       for(s<-this.greetStrings)
        {
          greets+=s;
        }
       println(greets);
     }
     private def printByeby()
     {
       this.byebyStrings.foreach(print);
       print("\n");
     }
     private def printByeby2()
     {
        for( i:Int<-0 to 2)
        {
           print(this.byebyStrings(i));
        }
     }
    
     def main(args:Array[String])
     {
       this.iniGreetStrings();
       this.printGreet1();
       this.printGreet2();
       this.printByeby();
      
       this.printByeby2();
     }
}