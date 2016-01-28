package integration

import infrastructure.CarCheckSpec
import org.scalatestplus.play.OneServerPerTest
import play.api.Application
import play.api.http.Status._
import play.api.libs.ws._

class CarCheckIntegrationSpec extends CarCheckSpec with OneServerPerTest {

  val localhost = s"http://localhost:$port"

  "Checking car" should {
    "return existing car " in {
      val result = callApi(testRegNumber, testMake, Some(testV5c))
      result.status shouldBe OK
      val jsResult = result.json
      (jsResult \ "regNumber").as[String] shouldBe testRegNumber
      (jsResult \ "make").as[String] shouldBe testMake
      (jsResult \ "v5c").as[Long] shouldBe testV5c

    }

    "return NOT FOUND for non existing car" in {
      val result = callApi("someNubmer", testMake)
      result.status shouldBe NOT_FOUND
    }
  }

  private def callApi(regNumber: String, make: String, v5c: Option[Long] = None)(implicit app: Application): WSResponse = {
    val v5cPart = v5c.fold("")(num => s"?v5c=$num")
    WS.url(localhost + s"/check/car/$regNumber/$make$v5cPart").get().futureValue
  }

}
