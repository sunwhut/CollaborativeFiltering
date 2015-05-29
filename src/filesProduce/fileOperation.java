package filesProduce;

import java.io.*;

public class fileOperation 
{
	File file;
	/*
	 *功能：构造函数
	 *参数：文件全路径（若文件存在会先删除，在创建新的）
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
	 *功能：项指定文件中写入内容，以追加的方式
	 *参数：所写入的内容 
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
