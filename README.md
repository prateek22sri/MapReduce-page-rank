# MapReduce_pageRank

### Goal
This assignment provides an illustration of PageRank algorithms and Hadoop. You will then blend these
applications by implementing a parallel version of PageRank using the programming interfaces of the Hadoop
MapReduce framework.

### Deliverables
You are required to turn in the following items in a zip file (username HadoopPageRank.zip) in this assignment:
+ The source code of Hadoop PageRank you implemented.
+ Technical report (username HadoopPageRank report.docx) that contains:
+ The description of the main steps and data flow in your program.
+ The output file (username HadoopPageRank output.txt) which contains the first 10 urls along with
their ranks.

### What is PageRank?
The web search engine is a typical distributed system on the Internet. It is designed to search for information
on the World Wide Web. The search results are generally presented in a list of results and are often called
hits. PageRank is a well-known web graph ranking algorithm that helps Internet users sort hits by their
importance.
PageRank calculates a numerical value for each element of a hyperlinked set of webpages, which reflects
the probability that a random surfer will access that page. The process of PageRank can be understood as a
Markov Chain which requires iterative calculations to converge. An iteration of PageRank calculates the new
access probability for each webpage based on values calculated in the previous iteration. The process will
repeat until the number of current iterations is bigger than predefined maximum iterations, or the Euclidian
distance between rank values in two subsequent iterations is less than a predefined threshold that controls
the accuracy of the output results.

### Notes
You can implement a sequential PageRank that can run on desktops or laptops. But when processing
larger input data, like web graphs containing more than a million webpages, you need to run the PageRank
application in parallel so that it can aggregate the computing power of multiple compute nodes. Currently, in
both industry and academia, the study of large-scale web or social graphs has become increasingly popular.
In one published paper, the job execution engines that claim to support large-scale PageRank include: MPI,
Hadoop, Dryad, Twister, Pregel.

For more details, [click here](https://github.com/prateek22sri/MapReduce-page-rank/blob/master/project2.pdf)
