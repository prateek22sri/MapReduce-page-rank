package indiana.cgl.hadoop.pagerank.helper;

/*
 * collect the page rank results from previous computation.
 */

import indiana.cgl.hadoop.pagerank.RankRecord;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class SortingMap extends Mapper<LongWritable, Text, LongWritable, Text> {

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		System.out.println("input key is " + key.get());
		System.out.println("input value is " + value.toString());

		
		String tmp = value.toString();
		RankRecord rrd = new RankRecord(tmp);
		tmp = Double.toString(rrd.rankValue) + "=" + tmp; 

		long new_key = 1;
		
		System.out.println(tmp);
		
		context.write(new LongWritable(new_key), new Text(tmp));
	}
}
