package integration

import infrastructure.CarCheckSpec
import org.scalatestplus.play.OneServerPerTest
import play.api.http.Status._
import play.api.libs.ws._

class CarCheckIntegrationSpec extends CarCheckSpec with OneServerPerTest {

  val localhost = s"http://localhost:$port"

  "Checking car" should {
    "return existing car " in {
      val result = WS.url(localhost + s"/check/car/$testRegNumber/$testMake?v5c=$testV5c").get().futureValue
      result.status shouldBe OK
      val jsResult = result.json
      (jsResult \ "regNumber").as[String] shouldBe testRegNumber
      (jsResult \ "make").as[String] shouldBe testMake
      (jsResult \ "v5c").as[String] shouldBe testV5c

    }

    "return NOT FOUND for non existing car" in {
      val result = WS.url(localhost + s"/check/car/someNumber/$testMake").get().futureValue
      result.status shouldBe NOT_FOUND
    }
  }
}
