package exercise;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.distance.CosineDistanceMeasure;

public class MyRecommender {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String filepath = "data/test.txt";
		DataModel model = new FileDataModel(new File(filepath));
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new RecommenderBuilder() {		
			@Override
			public Recommender buildRecommender(DataModel model)
					throws TasteException {
				// TODO Auto-generated method stub
				UserSimilarity similarity =  new EuclideanDistanceSimilarity(model);
				UserNeighborhood neighborhoood = new NearestNUserNeighborhood(
						3, similarity, model);
				return 
				new GenericUserBasedRecommender(model,neighborhoood,similarity);
			}
		};
		
//		List<RecommendedItem> recommendations = recommender.recommend(1, 2);
//		System.out.println(recommendations.size());
//		for(RecommendedItem recommendation:recommendations){
//			System.out.println(recommendation);
//		}
//		System.out.println(similarity.userSimilarity(1, 2));		 
		double score = evaluator.evaluate(builder, null, model, 0.7, 1.0);
		System.out.println(score);
	}

}
