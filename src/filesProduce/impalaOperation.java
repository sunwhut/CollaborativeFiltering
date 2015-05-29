package filesProduce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import recommend.recommendGet;


public class impalaOperation 
{
	
	private static final String IMPALAD_HOST="192.1.2.201";
	private static final String IMPALAD_JDBC_PORT="21050";
	private static final String CONNECTION_URL="jdbc:hive2://"+IMPALAD_HOST+':'+IMPALAD_JDBC_PORT+"/;auth=noSasl";  
	private static final String JDBC_DRIVER_NAME="org.apache.hive.jdbc.HiveDriver";
	private Connection con;
	private ResultSet rs;
	private Statement stmt;
	
	sorceGet sg;
	
	public impalaOperation()
	{
		con=null;
		rs=null;
		stmt=null;
		sg=new sorceGet();
	}
	/*
	 *���ܣ���ָ��Ŀ¼����һ�������û������ֱ�
	 *�������ļ�·�� 
	 */
	public void sorceWrite(String filepath)
	{
		fileOperation fi=new fileOperation(filepath);
		//String sql="select people_table.id,book_table.book_id,book_about.num from people_table,book_table,jieyue_table,book_about where jieyue_table.user_id=people_table.people_id and jieyue_table.book_id=book_table.book_id and jieyue_table.book_id=book_about.bib_record_id";
		String sql="select people_table.id,book_table.book_id,a.num from (select bookid,count(*) num from (select * from big_table where type='o') b group by bookid) a,people_table,book_table,(select * from big_table where type='o') c where people_table.people_id=c.readerid and c.bookid=book_table.book_id and c.bookid=a.bookid order by people_table.id";
		try
		{
			Class.forName(JDBC_DRIVER_NAME);
			con=DriverManager.getConnection(CONNECTION_URL);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					if(i<rs.getMetaData().getColumnCount())
						fi.writeFile(rs.getString(i)+",");    
					if(i==rs.getMetaData().getColumnCount())
					{
						fi.writeFile(String.valueOf(sg.getsorce(rs.getString(i))));
					}
				}
				fi.writeFile("\r\n");
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				
				rs.close();
				stmt.close();
				con.close();
				System.out.println("");
			}
			catch(Exception e)
			{}
		}
	}
	/*
	 *���ܣ���ѯ����ʾ��ѯ���
	 *������sql��� 
	 */
	public void showOutcome(String sql)
	{
		int j=0;
		String outcome[]={"","","","",""};
		try
		{
			Class.forName(JDBC_DRIVER_NAME);
			con=DriverManager.getConnection(CONNECTION_URL);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.print(rs.getRow()+"\t");
				outcome[j]=outcome[j]+rs.getRow()+"\t";
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					System.out.print(rs.getString(i)+"\t\t");
					outcome[j]=outcome[j]+rs.getString(i)+"\t\t";
				}
				System.out.println();
				j=j+1;
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				
				rs.close();
				stmt.close();
				con.close();
				System.out.println("");
			}
			catch(Exception e)
			{}
		}
	}
	/*
	 *ע���÷���һ�㲻�õ��ã���idinformationGer ���в���ʱ���Զ�����
	 *���ܣ����������Ƽ����õ�id
	 *������sql��䣩 
	 */
	public int useridFind(String sql)
	{
		try
		{
			Class.forName(JDBC_DRIVER_NAME);
			con=DriverManager.getConnection(CONNECTION_URL);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next())
			{
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					return Integer.parseInt(rs.getString(i));
				}
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			try
			{
				
				rs.close();
				stmt.close();
				con.close();
				System.out.println("");
			}
			catch(Exception e)
			{}
		}
		return 0;
	}
	
	/*
	 *�÷������������ز�ѯ�Ľ�� 
	 */
	public String getOutcome(String sql)
	{
		int j=0;
		String outcome = null;
		try
		{
			Class.forName(JDBC_DRIVER_NAME);
			con=DriverManager.getConnection(CONNECTION_URL);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				outcome="";
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					outcome=outcome+rs.getString(i)+";";
				}
				j=j+1;
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				
				rs.close();
				stmt.close();
				con.close();
				System.out.println("");
			}
			catch(Exception e)
			{}
		}
		return outcome;
	}
	/*
	 *������������ID
	 *���� string ���� 
	 */
	public String[] bookIdget(String sorcefile,String bookname)
	{
		int number=0;
		ifExistid ie=new ifExistid(sorcefile);
		String sql="select book_id from book_table where book_name like '"+bookname+"%'";
		List<String> bookid=new ArrayList<String>();
		try
		{
			Class.forName(JDBC_DRIVER_NAME);
			con=DriverManager.getConnection(CONNECTION_URL);
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					if(ie.stringFind(rs.getString(i)))
					{
						number=number+1;
						bookid.add(rs.getString(i));
					}
				}
				if(number>=3)
					break;
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				
				rs.close();
				stmt.close();
				con.close();
				System.out.println("");
			}
			catch(Exception e)
			{}
		}
		String book[]=bookid.toArray(new String[bookid.size()]);
		return book;
	}
}
