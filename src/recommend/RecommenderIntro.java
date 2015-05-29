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
 *推荐算法的主程序 
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
	 *推荐算法的前提模型的建立
	 *参数为一包含读者编号（不是工号），图书编号，图书打分 的文件的全路径 格式可见 "E:/shujuku/test.txt" 
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
	 *推荐算法
	 *参数为 读者编号（这个可由图书卡生成），所需推荐的最大数目
	 */
	
	public List recommendId(long num,int sum) throws Exception
	{
		List<RecommendedItem> list=r.recommend(num, sum);//该函数有两个参数，一个所需推荐者的编号uid，一个是需要最多推荐的数量
		//System.out.printf("uid:%s",num);
		/*for(RecommendedItem ritem:list)
		{
			System.out.printf("(%s,%f)",ritem.getItemID(),ritem.getValue());
		}*/
		//System.out.println();
		return list;
	}
	
	/*
	 *推荐相似图书(这个方法在recommendGet类中自动调用)
	 *参数 图书的ID 
	 */
	public long[] similarItemId(long itemId) throws TasteException{
		long[] SimilarItemIds = ((ItemSimilarity) user).allSimilarItemIDs(itemId);
		return SimilarItemIds;
	}
	
	/*
	 *推荐相似读者(这个方法在recommendGet类中自动调用)
	 *参数读者 ID(不是工号与图书卡号) 
	 */
	public long[] similaryPeople(long userid)throws TasteException
	{
		long[] SimilaryPeopleids=neighbor.getUserNeighborhood(userid) ;
		return SimilaryPeopleids;
	}

}
