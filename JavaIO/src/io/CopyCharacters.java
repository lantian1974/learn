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
 *��ΪJava���ַ���16λ��2���ֽڣ�����C++�в�ͬ,C++��һ���ַ���1���ֽ�8λ��,����������ר�Ŷ�ȡ�ַ��ģ��������
 *int c �����ĵ�16λ���洢��ȡ�����ݣ����ڶ�ȡ�ֽڵ����У�int c�����ĵ�8λ���洢��ȡ�����ݣ������ ��д����Ҳ�����մ˹���
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
