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

		
		
		String sorcefile="F:/books/sorce.txt";//Դ����ļ�
		String outputfile="F:/score.txt";//���������������Ƽ����ߵ��Ƽ���Ϣ
		/*Object outcome[][];
		Object outcome1[][];
		
		
//		impalaOperation im=new impalaOperation();
//		im.sorceWrite(sorcefile);//������������ɴ�ֱ�ģ�ֻ��Ҫû��һ��ʱ�䶨������һ�ξ���
		
		//һ�����������������String���͵�
		
		
		
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
		
		outcome=rg.returnGidgetoutcome("481037340708");//���ݹ��� ��ѯ
		if(outcome == null){
			System.out.println("null");
		}else{
			System.out.print(outcome[0][0].toString()+" ");
			System.out.print(outcome[0][1].toString()+" ");
			System.out.print(outcome[0][2].toString()+" ");
			System.out.println(outcome[0][3].toString());
		}

		outcome1=rg.returnRidGetoutcome("21144179");//����ͼ�鿨�Ų�ѯ
		System.out.print(outcome1[0][0].toString()+" ");
		System.out.print(outcome1[0][1].toString()+" ");
		System.out.print(outcome1[0][2].toString()+" ");
		System.out.println(outcome1[0][3].toString());*/
		long startTime = System.currentTimeMillis();
		RecommederInformation ri=new RecommederInformation();
		ri.showInformation(sorcefile, outputfile);//������ʾ�������Ƽ��Ķ��������Ƽ�ͼ��������Ϣ
		long endTime = System.currentTimeMillis();
		System.out.println("��������ʱ�䣺"+(endTime-startTime)+"ms");
	}
}
                                    