package filesProduce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * 该类的作用是判断用户所输入的信息是否有效，是否有历史记录
 * 因为有历史记录才能进行下一步推荐
 */
public class ifExistid 
{
	private String filename;
	
	public ifExistid(String filepath)
	{
		filename=filepath;
	}
	/*
	 *功能：判断编号为id的书目是否在源打分文件中出现（该书书否被接过）
	 *参数 书ID 
	 */
	public boolean stringFind(String id) throws IOException
	{
		FileReader in=new FileReader(filename);
		BufferedReader fin=new BufferedReader(in);
		String linein=fin.readLine();
		while(linein!=null)
		{
			String e=","+id+",";
			if(linein.contains(e))
			{
				fin.close();
				return true;
			}
			linein=fin.readLine();
		}
		fin.close();
		return false;
	}
	/*
	 *功能：判断某个人的id是否出现在源打分表中（该人是否有借阅记录）
	 *参数：读者ID （这个不是读者工号也不是卡号），是工具在数据表中查询饿结果 给出的ID
	 */
	public boolean idFind(int id)throws IOException
	{
		FileReader in=new FileReader(filename);
		BufferedReader fin=new BufferedReader(in);
		String linein=fin.readLine();
		while(linein!=null)
		{
			String ll[]=linein.split(",");
			if(String.valueOf(id).equals(ll[0]))
			{
				fin.close();
				return true;
			}
			linein=fin.readLine();
		}
		fin.close();
		return false;
	}
}
