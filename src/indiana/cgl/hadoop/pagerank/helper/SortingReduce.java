package indiana.cgl.hadoop.pagerank.helper;

import indiana.cgl.hadoop.pagerank.RankRecord;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.Reducer;

public class SortingReduce extends
		Reducer<LongWritable, Text, LongWritable, Text> {
	public void reduce(LongWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		Map<Double, String> out = new TreeMap(Collections.reverseOrder());

		for (Text val : values) {
			String[] line = val.toString().split("=");
			out.put(Double.parseDouble(line[0]), line[1]);
		}

		key = null;
		for (Map.Entry m : out.entrySet()) {
			context.write(key,
					new Text(m.getValue().toString()));
		}
	}
}