abstract class Expr
  case class Var(name:String) extends Expr
  case class Number(num:Double) extends Expr
  case class UnOp(operator:String,arg:Expr) extends Expr
  case class BinOp(operator:String,left:Expr,right:Expr) extends Expr
object LearnMatch
{
     def main(args:Array[String])
     {
      
       // 构造函数匹配验证 以及 绑定变量匹配验证，注：x,y是绑定的变量
        def simplifyTop(expr:Expr)=expr match
        {
          case UnOp("-",UnOp("-",e))=>e;
          case BinOp("+",e,Number(0))=>e;
          case BinOp("+",Number(0),e)=>e;
          case BinOp("-",Number(x),Number(y))=>Number(x-y);
         // case BinOp("+",Number(x),Number(y))=>Number(x+y);
          case BinOp("*",Number(x),Number(y))=>Number(x*y);
          case BinOp("/",Number(x),Number(y))=>{y match
          {
            case 0=>Number(0);
            case _ =>Number(x/y);
          }
          }
          case _ =>{println("no match");Number(-1);}
        }
        simplifyTop(BinOp("+",Number(0),Number(10)));
        //类型匹配验证
       val result:Expr= simplifyTop(BinOp("-",Number(4),Number(1)));
       result match
       {
          case result:Number=>println(result.num);
          case _ =>println("i do not know what you are!");
       }
       //类型匹配验证
       def tupleMatch(obj:Any)= obj match
       {
         case  (a:String,b:String,c:String)=>println(a+b+c);
         case _ =>println("i do not know what you are!");
       }
       tupleMatch((1,2,3));
       tupleMatch(("a","b","c"));
       //常量匹配
       val firstArg=if(!args.isEmpty) args(0) else "";
       val friend= firstArg match {
         case "salt"=>{ 
               val info="i like eat";
                println(info);
                "pepper";
         }
         case "chips"=>"salsa";
         case "eggs"=>"bacon";
         case _ =>"huh?" 
       } 
      //序列匹配 和 绑定匹配
       def listMathDemo(l:List[Int])=l match
       {
          case List(a,b,c)=>println(a+b+c);
          case List(a,b,_)=>println(a+b);
          case List(1,_,_,_)=>println("1 begin");
          case List(0,_*)=>
            {
              for(i<-  1 to l.length-1)
              {
                println(l(i));
              }
            }
       }
       listMathDemo(List(1,2,3));
       listMathDemo(List(1,2,3,4));
       listMathDemo(List(0,2,3,4,5));
     }
}