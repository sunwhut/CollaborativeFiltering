package recommend;

import java.util.List;
import java.io.File;
import java.io.IOException;


//import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import filesProduce.fileOperation;
/*
 *�����һ�����������ݴ��ѡ���ļ���д��һ���Ƽ��ļ� 
 */
public class RecommederInformation 
{
	final static int neg=1000000;
	final static int rec=10;
	public static int number=0;
	
	
	public RecommederInformation()
	{
		
	}
	/*
	 *������
	 *����scorefile Դ����ļ�,����outputfile �����ɵ��ļ� 
	 */
	public void showInformation(String scorefile,String outputfile) throws Exception
	{
		fileOperation fi=new fileOperation(outputfile);		
		DataModel model=new FileDataModel(new File(scorefile));
		UserSimilarity user=new EuclideanDistanceSimilarity(model);
		NearestNUserNeighborhood neighbor=new NearestNUserNeighborhood(80,user,model);
		Recommender r=new GenericUserBasedRecommender(model,neighbor,user);
		LongPrimitiveIterator iter=model.getUserIDs();		
		while(iter.hasNext())
		{
			int flag=0;
			int f=0;
			long uid=iter.nextLong();
			List<RecommendedItem> list=r.recommend(uid, 10);
			for(RecommendedItem ritem:list)
			{
				if(ritem.getItemID()!=0)
				{
					if(f==0)
					{
						fi.writeFile(uid+" : ");
						f=1;
					}
					flag=1;
					fi.writeFile("("+ritem.getItemID()+" "+ritem.getValue()+")");
				}
			}
			if(flag==1)
			{
				fi.writeFile("\r\n");
			}
		}
	}
}
