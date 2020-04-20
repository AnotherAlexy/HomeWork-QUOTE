package quotePerfTest

import scenarios.Quote
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class QuotePerfTest extends Simulation {
  val httpConf = http.baseUrl("http://localhost:8080")

  setUp(Quote.quote.inject(
    incrementConcurrentUsers(40)
      .times(1)
      .eachLevelLasting(3 minutes)
      .separatedByRampsLasting(20 seconds))
  ).protocols(httpConf)
    .throttle(
      reachRps(125) in (10 seconds), holdFor(180 second)
    )
}