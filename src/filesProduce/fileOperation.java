package filesProduce;

import java.io.*;

public class fileOperation 
{
	File file;
	/*
	 *���ܣ����캯��
	 *�������ļ�ȫ·�������ļ����ڻ���ɾ�����ڴ����µģ�
	 */
	public fileOperation(String filename)
	{
		file=new File(filename);
		if(file.exists())
			file.delete();
		try 
		{
			file.createNewFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/*
	 *���ܣ���ָ���ļ���д�����ݣ���׷�ӵķ�ʽ
	 *��������д������� 
	 */
	public void writeFile(String context)
	{
		if(file.exists())
		{
			try 
			{
				FileWriter fw=new FileWriter(file,true);
				fw.write(context);
				fw.flush();
				fw.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
}
