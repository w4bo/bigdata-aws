package it.unibo.big

import org.apache.spark.sql.SparkSession


object WordCount {

  /**
   * @param args arguments
   */
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
        .appName("WordCount")
        .getOrCreate()
    val sc = sparkSession.sparkContext
    sc.textFile(args(0))
        .flatMap(r => r.split(' '))
        .filter(_.nonEmpty)
        .map(r => (r.toLowerCase, 1))
        .reduceByKey((a, b) => a + b)
        .sortBy(-_._2)
        .take(100)
        .foreach { println(_) }
  }
}
