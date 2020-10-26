package com.wanari.webpush.testutil

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.scalatest.BeforeAndAfterEach
import org.scalatest.wordspec.AnyWordSpecLike

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

trait TestBase extends AnyWordSpecLike with ScalatestRouteTest with BeforeAndAfterEach {
  def hostName: String
  def port: Int

  lazy val mockServer: WireMockServer = new WireMockServer(wireMockConfig().bindAddress(hostName).port(port))

  override def beforeEach(): Unit = {
    mockServer.start()
  }

  override def afterEach(): Unit = {
    mockServer.stop()
  }

  def await[T](f: Future[T]): T = {
    Await.result(f, 5.seconds)
  }
}
