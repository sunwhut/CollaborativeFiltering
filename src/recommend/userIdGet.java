package recommend;

import filesProduce.impalaOperation;
/*
 *��������Ϊͨ�����Ż�ͼ�鿨�Ų��������Ƽ��㷨��ID�� 
 */
public class userIdGet 
{
	private int persion_id;//���߱��
	impalaOperation im=new impalaOperation();
	
	public userIdGet()
	{
		
	}
	/*
	 *����:ͨ��ͼ�鿨��Ѱ��id
	 *������ͼ�鿨�� 
	 */
	public void idGetR(String rid)
	{
		String sql="select id from information where kahao='"+rid+"'";
		persion_id=im.useridFind(sql);
	}
	/*
	 *���ܣ�ͨ����������ID
	 *���������� 
	 */
	public void idGetG(String gid)
	{
		String sql="select id from information where peopleid="+gid;
		persion_id=im.useridFind(sql);
	}
	/*
	 *���ܣ��������ڲ�ѯ��Id 
	 */
	public int getId()
	{
		return persion_id;
	}
}
