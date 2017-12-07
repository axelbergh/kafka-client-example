package com.despegar.p13n.kafka.producer

import java.util.Properties
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.KafkaProducer
import scala.io.StdIn
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.concurrent.TimeUnit

object ExampleProducer extends App {
  val props: Properties = new Properties()
  //  props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094")
  props.put("bootstrap.servers", "p13n-kafka-rc-01.servers.despegar.it:9092")
  //  props.put("acks", "all")
  //  props.put("retries", 0: java.lang.Integer)
  //  props.put("batch.size", 16384: java.lang.Integer)
  //  props.put("linger.ms", 1: java.lang.Integer)
  //  props.put("buffer.memory", 33554432: java.lang.Integer)
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer: Producer[String, String] = new KafkaProducer(props)

//  Iterator.continually(StdIn.readLine)
//    .takeWhile(_.nonEmpty).foreach { line =>
//      val response = producer.send(new ProducerRecord[Int, String]("kafka-example", line.hashCode(), line))
//      val metadata = response.get(10, TimeUnit.SECONDS)
//      println(s"offset: ${metadata.offset}, partition: ${metadata.partition}, topic: ${metadata.topic}")
//    }
  
  List("1", "2", "3", null, "4", null, "8").foreach{value =>
    producer.send(new ProducerRecord[String, String]("kafka-example", "2", value))
  }

  producer.close()
}