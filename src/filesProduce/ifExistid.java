package filesProduce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * ������������ж��û����������Ϣ�Ƿ���Ч���Ƿ�����ʷ��¼
 * ��Ϊ����ʷ��¼���ܽ�����һ���Ƽ�
 */
public class ifExistid 
{
	private String filename;
	
	public ifExistid(String filepath)
	{
		filename=filepath;
	}
	/*
	 *���ܣ��жϱ��Ϊid����Ŀ�Ƿ���Դ����ļ��г��֣�������񱻽ӹ���
	 *���� ��ID 
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
	 *���ܣ��ж�ĳ���˵�id�Ƿ������Դ��ֱ��У������Ƿ��н��ļ�¼��
	 *����������ID ��������Ƕ��߹���Ҳ���ǿ��ţ����ǹ��������ݱ��в�ѯ����� ������ID
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
