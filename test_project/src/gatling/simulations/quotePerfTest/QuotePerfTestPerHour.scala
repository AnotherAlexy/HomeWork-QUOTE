package quotePerfTest

import scenarios.Quote
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration._

class QuotePerfTestPerHour extends Simulation {
  val httpConf = http.baseUrl("http://localhost:8080")

  setUp(Quote.quote.inject(
    incrementConcurrentUsers(10)
      .times(1)
      .eachLevelLasting(1 hour)
      .separatedByRampsLasting(10 seconds))
  ).protocols(httpConf)
    .throttle(
      reachRps(10) in (10 seconds), holdFor(1 hour)
    )
}