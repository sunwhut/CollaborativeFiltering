package exercise;

import java.io.File;
import java.text.DecimalFormat;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class TestRecommender {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String filepath = "data/sorce.txt";
		DataModel model = new FileDataModel(new File(filepath));
		RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
		RecommenderBuilder builder = new RecommenderBuilder(){
			@Override
			public Recommender buildRecommender(DataModel model)
					throws TasteException {
				// TODO Auto-generated method stub
				UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
				UserNeighborhood neighbor = new NearestNUserNeighborhood(80, similarity, model);
				return 
				new GenericUserBasedRecommender(model, neighbor, similarity);
//				new SlopeOneRecommender(model);
				}
			};
			long startTime = System.currentTimeMillis();
			IRStatistics stats = evaluator.evaluate(builder, null, model, null, 20,GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD,1.0);
			long endTime = System.currentTimeMillis();
			double precision = stats.getPrecision();
			double recall = stats.getRecall();
			double accuracy = 2*precision*recall/(precision+recall);
			System.out.println(precision+"---"+recall+"---"+new DecimalFormat("0.000000").format(accuracy));
			System.out.println("程序运行时间："+(endTime-startTime)+"ms");
		}
}
