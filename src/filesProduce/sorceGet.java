package filesProduce;
/*
 *����������Ǹ��ݸ��鱻�ӵĴ��������֣�������5�֣�3��Ϊ��׼ 
 */
public class sorceGet 
{
	public double sorce=3;
	private double value;
	public sorceGet()
	{
		value=0.2;
	}
	public sorceGet(double s)
	{
		value=s;
	}
	public double getsorce(String num)
	{
		int s=Integer.parseInt(num);
		if(s*value>=2)
			return sorce+2;
		else
			return sorce+s*value;
	}
}
