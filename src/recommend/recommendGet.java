package recommend;

import java.util.List;

import filesProduce.ifExistid;
import filesProduce.impalaOperation;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
/*
 *该类的主要作用是，根据所推荐的书目ID 查找该图书的相关信息 
 */
public class recommendGet 
{
	private static final String[][] rowData = null;
	private int number;
	private String filename;
	userIdGet ug;
	ifExistid ie;
	public recommendGet(String name)
	{
		number=0;
		filename=name;
		ug=new userIdGet();
		ie=new ifExistid(name);
	}
	/*
	 *功能：通过图书卡号查找图书信息
	 *参数：图书卡号 
	 */
	public void RidGetoutcome(String readnumber) throws Exception
	{
		long num=0;
		ug.idGetR(readnumber);
		number=ug.getId();
		impalaOperation im=new impalaOperation();
		RecommenderIntro ri=new RecommenderIntro(filename);
		List<RecommendedItem> l=ri.recommendId(number, 5);//两参数一个为表用学生编号，这个可以通过输入图书卡号自动获得，一个为所需推荐图书的最大数目
		for(RecommendedItem ritem:l)
		{
			 num=ritem.getItemID();
			 String sql="select book_id,book_name,author_name from book_table where book_id="+num;
			 im.showOutcome(sql);
		}
	}
	/*
	 *功能：通过工号查找图书信息
	 *参数：工号 
	 */
	public void Gidgetoutcome(String userid) throws Exception
	{
		long num=0;
		ug.idGetG(userid);
		number=ug.getId();
		impalaOperation im=new impalaOperation();
		RecommenderIntro ri=new RecommenderIntro(filename);
		List<RecommendedItem> l=ri.recommendId(number, 5);//两参数一个为表用学生编号，这个可以通过输入图书卡号自动获得，一个为所需推荐图书的最大数目
		for(RecommendedItem ritem:l)
		{
			 num=ritem.getItemID();
			 String sql="select book_id,book_name,author_name from book_table where book_id="+num;
			 im.showOutcome(sql);
		}
	}
	
	public Object[][] returnGidgetoutcome(String userid) throws Exception
	{
		int i=0;
		String outcome[]={"","","","",""};
		long num=0;
		ug.idGetG(userid);
		number=ug.getId();
		if(ie.idFind(number))
		{
		impalaOperation im=new impalaOperation();
		RecommenderIntro ri=new RecommenderIntro(filename);
		List<RecommendedItem> l=ri.recommendId(number, 5);//两参数一个为表用学生编号，这个可以通过输入图书卡号自动获得，一个为所需推荐图书的最大数目
		if(l.isEmpty()){
			return null;
		}
		for(RecommendedItem ritem:l)
		{
			 num=ritem.getItemID();
			 String sql="select ta.book_id,ty.type,ta.book_name,ta.author_name from book_type ty,book_table ta where ty.bookid=ta.book_id and book_id="+num;
			 outcome[i]=im.getOutcome(sql);
			 i=i+1;
		}
		
		Object[][] rowData = new Object[i][4];
		for(int j=0;j<i;j++)
		{
			String context[]=outcome[j].split(";");
			rowData[j][0]=context[0];
			rowData[j][1]=context[1];
			rowData[j][2]=context[2];
			rowData[j][3]=context[3];
		}
		return rowData;
		}
		else
			return null;
		
	}
	
	public Object[][] returnRidGetoutcome(String readnumber) throws Exception
	{
		int i=0;
		String outcome[]={"","","","",""};
		long num=0;
		ug.idGetR(readnumber);
		number=ug.getId();
		if(ie.idFind(number))
		{
		impalaOperation im=new impalaOperation();
		RecommenderIntro ri=new RecommenderIntro(filename);
		List<RecommendedItem> l=ri.recommendId(number, 5);//两参数一个为表用学生编号，这个可以通过输入图书卡号自动获得，一个为所需推荐图书的最大数目
		if(l.isEmpty()){
			return null;
		}
		for(RecommendedItem ritem:l)
		{
			 num=ritem.getItemID();
			 String sql="select ta.book_id,ty.type,ta.book_name,ta.author_name from book_type ty,book_table ta where ty.bookid=ta.book_id and book_id="+num;
			 outcome[i]=im.getOutcome(sql);
			 i=i+1;
		}

		Object[][] rowData = new Object[i][4];
		for(int j=0;j<i;j++)
		{
			String context[]=outcome[j].split(";");
			rowData[j][0]=context[0];
			rowData[j][1]=context[1];
			rowData[j][2]=context[2];
			rowData[j][3]=context[3];
		}
		return rowData;		
		}
		else
			return null;
	}
	
	/*
	 *这里面后面两个方法和前面的一样只不过不用来显示结果，而是给出返回值 
	 */
	
	/*
	 *推荐相关图书，调用mahou中的算法
	 *参数 图书ID,所需要的相似图书的最大数量number 
	 */
	public Object[][] returnSimilarId(String itemId,int number) throws Exception
	{
		impalaOperation im=new impalaOperation();
		if(ie.stringFind(itemId))
		{
		RecommenderIntro ri=new RecommenderIntro(filename);
		long l[]=ri.similarItemId(Long.parseLong(itemId));
		int len=l.length;
		String outcome[]=new String[number];
		if(number>len)
			number=len;
		for(int i=0;i<number;i++)
		{
			String sql="select ta.book_id,ty.type,ta.book_name,ta.author_name from book_type ty,book_table ta where ty.bookid=ta.book_id and ta.book_id="+l[i];
			outcome[i]=im.getOutcome(sql);
		}
		
		Object[][] rowData = new Object[number][4];
		for(int j=0;j<number;j++)
		{
			String context[]=outcome[j].split(";");
			rowData[j][0]=context[0];
			rowData[j][1]=context[1];
			rowData[j][2]=context[2];
			rowData[j][3]=context[3];
		}
		return rowData;
		}
		else
			return null;
	}
	
	/*
	 *推荐相似读者
	 *参数工号，所需推荐相似读者的最大数量 
	 */
	public Object[][] returnPeopleID_gonghao(String itemid,int number)throws Exception
	{
		int num;
		RecommenderIntro ri=new RecommenderIntro(filename);
		ug.idGetG(itemid);
		num=ug.getId();
		if(ie.idFind(num))
		{
		long l[]=ri.similaryPeople(Long.parseLong(String.valueOf(num)));
		int len=l.length;
		String outcome[]=new String[number];
		impalaOperation im=new impalaOperation();
		if(number>len)
			number=len;
		for(int i=0;i<number;i++)
		{
			String sql="select peopleid,name,typename,type from information_detail where id="+l[i];
			outcome[i]=im.getOutcome(sql);
		}
		Object[][] rowData = new Object[number][4];
		for(int j=0;j<number;j++)
		{
			String context[]=outcome[j].split(";");
			rowData[j][0]=context[0];
			rowData[j][1]=context[1];
			rowData[j][2]=context[2];
			rowData[j][3]=context[3];
		}
		return rowData;
		}
		else
			return null;
	}
	/*
	 *推荐相似读者
	 *参数图书馆卡号，所需推荐相似读者的最大数量 
	 */
	public Object[][] returnPeopleID_kahao(String itemid,int number)throws Exception
	{
		int num;
		RecommenderIntro ri=new RecommenderIntro(filename);
		ug.idGetR(itemid);
		num=ug.getId();
		if(ie.idFind(num))
		{
		long l[]=ri.similaryPeople(Long.parseLong(String.valueOf(num)));
		int len=l.length;
		String outcome[]=new String[number];
		impalaOperation im=new impalaOperation();
		if(number>len)
			number=len;
		for(int i=0;i<number;i++)
		{
			String sql="select peopleid,name,typename,type from information_detail where id="+l[i];
			outcome[i]=im.getOutcome(sql);
		}
		Object[][] rowData = new Object[number][4];
		for(int j=0;j<number;j++)
		{
			String context[]=outcome[j].split(";");
			rowData[j][0]=context[0];
			rowData[j][1]=context[1];
			rowData[j][2]=context[2];
			rowData[j][3]=context[3];
		}
		return rowData;
		}
		else
			return null;
	}
}
