package indiana.cgl.hadoop.pagerank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.StringBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;

public class PageRankReduce extends
		Reducer<LongWritable, Text, LongWritable, Text> {
	public void reduce(LongWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		double sumOfRankValues = 0.0;
		String targetUrlsList = "";

		int sourceUrl = (int)key.get();
		int numUrls = context.getConfiguration().getInt("numUrls", 1);
		
		//hint : each tuple may include rank value tuple or link relation tuple 
		for (Text val : values){
			if(val.toString().contains("#")){
				targetUrlsList = val.toString();
			} else {
				sumOfRankValues += Double.parseDouble(val.toString());
			}
		}	

		sumOfRankValues = 0.85 * sumOfRankValues + 0.15 * (1.0) / (double)numUrls;
		
		System.out.println("key is " + key);
		System.out.println("value is " + Double.toString(sumOfRankValues) + targetUrlsList);
		
		context.write(key, new Text(Double.toString(sumOfRankValues) + targetUrlsList));

	}
}
