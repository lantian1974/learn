package io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters 
{
	public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("outagain2.txt");
/**
 *因为Java中字符是16位，2个字节（这与C++中不同,C++中一个字符是1个字节8位）,本例的流是专门读取字符的，所以这个
 *int c 用它的低16位来存储读取的数据，而在读取字节的流中，int c用它的低8位来存储读取的数据，输出流 回写数据也是遵照此规则。
 */
            int c;
            while ((c = inputStream.read()) != -1) {
               outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
