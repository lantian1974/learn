package chapter4and5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DesktopSearch 
{
   
	 
	private static class Crawl implements Runnable
	{
        private final File root;
        private final BlockingQueue<File> fileQueue;
		Crawl(File _root,BlockingQueue<File> _fileQueue)
        {
        	this.root=_root;
        	this.fileQueue=_fileQueue;
        }
		@Override
		public void run() 
		{
			try
			{
				crawlFile(root);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
		private void crawlFile(File _root) throws InterruptedException
		{
			File[] entries=_root.listFiles();
			if (entries!=null)
			{
				for(File entry:entries)
				{
					if (entry.isDirectory())
					{
						crawlFile(entry);
					}
					else 
					{
						this.fileQueue.put(entry);
					}
				}
			}
		}
		
	}
	private static class Indexer implements Runnable
	{
        private final File indexFile;
        private final BlockingQueue<File> taskQueue;
        Indexer(File _indexFile,BlockingQueue<File> _taskQueue)
        {
          	this.indexFile=_indexFile;
          	this.taskQueue= _taskQueue;
        }
		@Override
		public void run()
		{
			 while(true)
			 {
				 try 
				 {
					File file=this.taskQueue.take();
				
					try {
						this.indexFile(file);
						if(Thread.currentThread().isInterrupted())
						{
							return;
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				 } catch (InterruptedException e) {
	
					e.printStackTrace();
					return;
				}	
			 }
			
		}
		private void indexFile(File file) throws IOException
		{
			String endLine = System.getProperty("line.separator");  //获取换行符
			String fileIndex=file.getName()+" : "+file.getPath()+endLine;
			writeIndexInfo(fileIndex);
			System.out.println(fileIndex);
		}
		private void  writeIndexInfo(String index) throws IOException
		{
		   FileWriter writer= new FileWriter(this.indexFile,true);
		   writer.append(index);
		   writer.close();
		}
	}
	public static void main(String[] args) throws IOException, InterruptedException
	{
		File rootFile1=new File("Z:\\projects");
		File rootFile2=new File("Z:\\客户资料");
		
		String indexFilePath1="d:\\index1.txt";
		String indexFilePath2="d:\\index2.txt";
		File indexFile1=new File(indexFilePath1);
		if (!indexFile1.exists())
		{
			indexFile1.createNewFile();
		}
		File indexFile2=new File(indexFilePath2);
		if (!indexFile2.exists())
		{
			indexFile2.createNewFile();
		}
		
		BlockingQueue<File> fileQueue=new LinkedBlockingQueue<File>();
		Thread crawlThread1=new Thread(new Crawl(rootFile1,fileQueue));	
		Thread crawlThread2=new Thread(new Crawl(rootFile2,fileQueue));	
		Thread indexThread1=new Thread(new Indexer(indexFile1,fileQueue));
		Thread indexThread2=new Thread(new Indexer(indexFile2,fileQueue));
		crawlThread1.start();
		crawlThread2.start();
		indexThread1.start();
		indexThread2.start();
		Thread.sleep(100);
        while(true)
        {
        	if((crawlThread1.isAlive()==false)
    				&& (crawlThread2.isAlive()==false)
    				&&(fileQueue.size()==0))
        	{
        		indexThread1.interrupt();
    			indexThread2.interrupt();
    			return;
        	}
        }
	}	
}
