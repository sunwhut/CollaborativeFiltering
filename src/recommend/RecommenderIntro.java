package recommend;

import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
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
 *�Ƽ��㷨�������� 
 */

public class RecommenderIntro 
{
	
	DataModel model;
	UserSimilarity user;
	NearestNUserNeighborhood neighbor;
	Recommender r;
	LongPrimitiveIterator iter;
	
	public RecommenderIntro()
	{
		
	}
	/*
	 *�Ƽ��㷨��ǰ��ģ�͵Ľ���
	 *����Ϊһ�������߱�ţ����ǹ��ţ���ͼ���ţ�ͼ���� ���ļ���ȫ·�� ��ʽ�ɼ� "E:/shujuku/test.txt" 
	 */
	public RecommenderIntro(String filepath) throws Exception
	{
		model=new FileDataModel(new File(filepath));
		user=new EuclideanDistanceSimilarity(model);
		//user=new PearsonCorrelationSimilarity(model);
		//NearestNUserNeighborhood neighbor=new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
		neighbor=new NearestNUserNeighborhood(10000000, user, model);
		r=new GenericUserBasedRecommender(model,neighbor,user);
		iter=model.getUserIDs();
	}
	
	/*
	 *�Ƽ��㷨
	 *����Ϊ ���߱�ţ��������ͼ�鿨���ɣ��������Ƽ��������Ŀ
	 */
	
	public List recommendId(long num,int sum) throws Exception
	{
		List<RecommendedItem> list=r.recommend(num, sum);//�ú���������������һ�������Ƽ��ߵı��uid��һ������Ҫ����Ƽ�������
		//System.out.printf("uid:%s",num);
		/*for(RecommendedItem ritem:list)
		{
			System.out.printf("(%s,%f)",ritem.getItemID(),ritem.getValue());
		}*/
		//System.out.println();
		return list;
	}
	
	/*
	 *�Ƽ�����ͼ��(���������recommendGet�����Զ�����)
	 *���� ͼ���ID 
	 */
	public long[] similarItemId(long itemId) throws TasteException{
		long[] SimilarItemIds = ((ItemSimilarity) user).allSimilarItemIDs(itemId);
		return SimilarItemIds;
	}
	
	/*
	 *�Ƽ����ƶ���(���������recommendGet�����Զ�����)
	 *�������� ID(���ǹ�����ͼ�鿨��) 
	 */
	public long[] similaryPeople(long userid)throws TasteException
	{
		long[] SimilaryPeopleids=neighbor.getUserNeighborhood(userid) ;
		return SimilaryPeopleids;
	}

}
