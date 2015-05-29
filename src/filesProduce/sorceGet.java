package filesProduce;
/*
 *该类的作用是根据该书被接的次数给叔打分，上限是5分，3分为基准 
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
