package com.wanari.webpush

import java.security.Security
import java.security.interfaces.{ECPrivateKey, ECPublicKey}

import com.github.tomakehurst.wiremock.client.WireMock._
import com.wanari.webpush.testutil.TestBase
import org.bouncycastle.jce.provider.BouncyCastleProvider

class PushServiceSpec extends TestBase {
  val hostName: String = "localhost"
  val port: Int        = 9000

  trait TestScope {
    Security.addProvider(new BouncyCastleProvider())

    val pubKey: String  = "BEmOvl1rng1WyOafMUiSBSIuO3LIVvrfmbBXn02vLqJBe27zKqB5L0EnJm78go-MJP2oxJowhW1ODKX5QhXb2Bo"
    val privKey: String = "cmSKEnl9pW3ofN9xd0HdBE-tExtWSle4K5XWWIum5oo"
    val service: PushService = PushService(
      Utils.loadPublicKey(pubKey).asInstanceOf[ECPublicKey],
      Utils.loadPrivateKey(privKey).asInstanceOf[ECPrivateKey],
      "Subject",
    )
  }

  "PushService" should {
    "#send(subscription: Subscription)" should {
      "send a data free push notification with the default TTL setting" in new TestScope {
        mockServer.stubFor(
          post(urlEqualTo("/send"))
            .willReturn(okJson("{}")),
        )

        await(service.send(Subscription("http://localhost:9000/send", pubKey, "userAuth")))

        mockServer.verify(postRequestedFor(urlEqualTo("/send")))
      }
    }

    "#send(subscription: Subscription, ttl: Int)" should {
      "send a data free push notification with custom TTL setting" in new TestScope {
        mockServer.stubFor(
          post(urlEqualTo("/send"))
            .willReturn(okJson("{}")),
        )

        await(service.send(Subscription("http://localhost:9000/send", pubKey, "userAuth"), 1))

        mockServer.verify(postRequestedFor(urlEqualTo("/send")))
      }
    }

    "#send(subscription: Subscription, payload: String, ttl: Int)" should {
      "send a data bearing push notification with custom TTL setting" in new TestScope {
        mockServer.stubFor(
          post(urlEqualTo("/send"))
            .willReturn(okJson("{}")),
        )

        await(service.send(Subscription("http://localhost:9000/send", pubKey, "userAuth"), "payload", 1))

        mockServer.verify(postRequestedFor(urlEqualTo("/send")))
      }
    }

    "#send(subscription: Subscription, payload: Array[Byte], ttl: Int = defaultTtl)" should {
      "send a data bearing push notification with custom TTL setting" in new TestScope {
        mockServer.stubFor(
          post(urlEqualTo("/send"))
            .willReturn(okJson("{}")),
        )

        await(service.send(Subscription("http://localhost:9000/send", pubKey, "userAuth"), "payload".getBytes, 1))

        mockServer.verify(postRequestedFor(urlEqualTo("/send")))
      }
    }
  }
}
