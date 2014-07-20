package nio2;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
public class LeranJavaFileSyste 
{
   public static void main(String[] args)
   {
	   FileSystem fs=FileSystems.getDefault();
	   System.out.println(fs.getSeparator());
   }
}
