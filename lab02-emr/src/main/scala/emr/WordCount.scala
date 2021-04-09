package emr

import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object WordCount {
    /**
     * @param args arguments
     */
    def main(args: Array[String]): Unit = {
        Logger.getLogger("org").setLevel(Level.OFF)
        Logger.getLogger("akka").setLevel(Level.OFF)
        val sparkSession = SparkSession.builder()
            .master(if (args.isEmpty) { "local" } else { "client" })
            .appName("WordCount")
            .getOrCreate()
        val sc = sparkSession.sparkContext
        val initRDD: RDD[String] = if (args.isEmpty) { sc.parallelize(Seq("tre tigri contro tre tigri")) } else { sc.textFile(args(0)) }
        initRDD
            .flatMap(r => r.split(' '))
            .filter(_.nonEmpty)
            .map(r => (r.toLowerCase, 1))
            .reduceByKey((a, b) => a + b)
            .sortBy(-_._2)
            .take(100)
            .foreach {
                println(_)
            }
    }
}
