package quotePerfTest.scenarios

import akka.actor.FSM.->
import io.gatling.core.Predef._
import io.gatling.core.feeder.Feeder
import io.gatling.http.Predef._

import scala.io.Source;

object Quote {
  val IDs = List(
    "100000000001",
    "100000000002",
    "100000000003",
    "100000000004",
    "100000000005",
    "100000000006",
    "100000000007",
    "100000000008",
    "100000000009",
    "100000000000",
    "100000000011",
    "100000000012",
    "100000000013",
    "100000000014",
    "100000000015",
    "100000000016",
    "100000000017",
    "100000000018",
    "100000000019",
    "100000000020",
    "100000000021",
    "100000000022",
    "100000000023",
    "100000000024",
    "100000000025",
    "100000000026",
    "100000000027",
    "100000000028",
    "100000000029",
    "100000000030",
  )

  val noBidProbability = 33
  val noAskProbability = 33
  val maxBid = 1000
  val maxAsk = 1000

  val feeder = new Feeder[String] {
    val random = scala.util.Random

    def next = {
      val randomIsin = IDs(random.nextInt(IDs.length))
      var randomBid = 1 + random.nextInt(maxBid)
      var randomAsk = randomBid + 1 + random.nextInt(maxAsk)

      var map = Map(
        "isin" -> randomIsin
      )

      if (random.nextInt(100) < noBidProbability) {  map += ("bid" -> "null") } else { map += ("bid" -> randomBid.toString) }

      if (random.nextInt(100) < noAskProbability) {  map += ("ask" -> "null") } else { map += ("ask" -> randomAsk.toString) }

      map
    }

    override def hasNext = true
  }

  val JSON =
    """
	{
	   "isin": "${isin}",
	   "bid": ${bid},
	   "ask": ${ask}
	}
  """

  val quoteHTTP = http("HTTP setting: Quote")
    .post("/api/v1/stock_quote/quote")
    .body(StringBody(JSON)).asJson
    .check(status.in(200, 400))

  val quote = scenario("Scenario: Quote")
    .feed(feeder)
    .exec(quoteHTTP)
}
