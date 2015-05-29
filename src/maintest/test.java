package maintest;

import org.apache.log4j.Logger;

import recommend.RecommederInformation;
import recommend.RecommenderIntro;
import recommend.recommendGet;
import filesProduce.ifExistid;
import filesProduce.impalaOperation;

public class test 
{
	public static void main(String[] args) throws Exception
	{
		//String[] columnNamesPeople = { "id", "peopleInformation" };
		//String[] columnNameBook={"id","bookInoformation"};

		
		
		String sorcefile="F:/books/sorce.txt";//源打分文件
		String outputfile="F:/score.txt";//用来保存所有能推荐读者的推荐信息
		/*Object outcome[][];
		Object outcome1[][];
		
		
//		impalaOperation im=new impalaOperation();
//		im.sorceWrite(sorcefile);//这个是用来生成打分表的，只需要没过一段时间定期运行一次就行
		
		//一下所有输入参数都是String类型的
		
		
		
		recommendGet rg=new recommendGet(sorcefile);
	   
		Object  p[][]=rg.returnPeopleID_gonghao("1",5);
		if(p==null)
			System.out.println("no such information");
		else
		{
		System.out.print(p[0][0].toString()+" ");
		System.out.print(p[0][1].toString()+" ");
		System.out.print(p[0][2].toString()+" ");
		System.out.println(p[0][2].toString());
		}
		
		Object  r[][]=rg.returnPeopleID_kahao("0092000030002", 5);
		System.out.print(r[0][0].toString()+" ");
		System.out.print(r[0][1].toString()+" ");
		System.out.print(r[0][2].toString()+" ");
		System.out.println(r[0][3].toString());
		
		
		Object  o[][]=rg.returnSimilarId("420909083148",10);
		System.out.print(o[0][0].toString()+" ");
		System.out.print(o[0][1].toString()+" ");
		System.out.print(o[0][2].toString()+" ");
		System.out.println(o[0][3].toString());
		
		outcome=rg.returnGidgetoutcome("481037340708");//根据工号 查询
		if(outcome == null){
			System.out.println("null");
		}else{
			System.out.print(outcome[0][0].toString()+" ");
			System.out.print(outcome[0][1].toString()+" ");
			System.out.print(outcome[0][2].toString()+" ");
			System.out.println(outcome[0][3].toString());
		}

		outcome1=rg.returnRidGetoutcome("21144179");//根据图书卡号查询
		System.out.print(outcome1[0][0].toString()+" ");
		System.out.print(outcome1[0][1].toString()+" ");
		System.out.print(outcome1[0][2].toString()+" ");
		System.out.println(outcome1[0][3].toString());*/
		long startTime = System.currentTimeMillis();
		RecommederInformation ri=new RecommederInformation();
		ri.showInformation(sorcefile, outputfile);//用来显示所有能推荐的读者与所推荐图书的相关信息
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间："+(endTime-startTime)+"ms");
	}
}
                                    