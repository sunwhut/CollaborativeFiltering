package recommend;

import filesProduce.impalaOperation;
/*
 *该类作用为通过工号或图书卡号查找用于推荐算法的ID号 
 */
public class userIdGet 
{
	private int persion_id;//读者编号
	impalaOperation im=new impalaOperation();
	
	public userIdGet()
	{
		
	}
	/*
	 *功能:通过图书卡号寻找id
	 *参数：图书卡号 
	 */
	public void idGetR(String rid)
	{
		String sql="select id from information where kahao='"+rid+"'";
		persion_id=im.useridFind(sql);
	}
	/*
	 *功能：通过工号需找ID
	 *参数：工号 
	 */
	public void idGetG(String gid)
	{
		String sql="select id from information where peopleid="+gid;
		persion_id=im.useridFind(sql);
	}
	/*
	 *功能：返回用于查询的Id 
	 */
	public int getId()
	{
		return persion_id;
	}
}
