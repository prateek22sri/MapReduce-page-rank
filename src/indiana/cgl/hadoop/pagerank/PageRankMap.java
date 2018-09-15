package indiana.cgl.hadoop.pagerank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class PageRankMap extends Mapper<LongWritable, Text, LongWritable, Text> {

	// each map task handles one line within an adjacency matrix file
	// key: file offset
	// value: <sourceUrl PageRank#targetUrls>
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		int numUrls = context.getConfiguration().getInt("numUrls", 1);
		String line = value.toString();

		StringBuffer sb = new StringBuffer();
		// instance an object that records the information for one webpage
		RankRecord rrd = new RankRecord(line);
		
		// double rankValueOfSrcUrl;
		if (rrd.targetUrlsList.size() <= 0) {
			// there is no out degree for this webpage;
			// scatter its rank value to all other urls
			double rankValuePerUrl = rrd.rankValue / (double) numUrls;
			for (int i = 0; i < numUrls; i++) {
				context.write(new LongWritable(i),
						new Text(String.valueOf(rankValuePerUrl)));
			}
		} else {
			/* Write your code here */
			
			System.out.println("this is the key output " + rrd.sourceUrl);
			System.out.println("this is the value output " + line.substring(line.indexOf("#")));
			
			context.write(new LongWritable(rrd.sourceUrl), new Text(line.substring(line.indexOf("#"))));
			
			int targetUrl_num = rrd.targetUrlsList.size();
			double rankValuePerUrl = rrd.rankValue/targetUrl_num;
			for (long targetUrl : rrd.targetUrlsList){
				
				System.out.println("this is the key output " + targetUrl);
				System.out.println("this is the value output " + rankValuePerUrl);
				
				context.write(new LongWritable(targetUrl), new Text(Double.toString(rankValuePerUrl)));
			}
		}
	} // end map

}
