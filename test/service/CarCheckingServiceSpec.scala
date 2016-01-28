package service

import infrastructure.CarCheckSpec
import org.scalatestplus.play.OneAppPerSuite
import services.CarCheckService

class CarCheckingServiceSpec extends CarCheckSpec with OneAppPerSuite {
  /*
  * This is trivial now, in place for test coverage, and as a place holder for future business rules thatn might come up
  * */
  "findCar" should {
    "find an existing car" in {
      CarCheckService.findCar(testRegNumber, testMake, Some(testV5c)).futureValue shouldBe testCar
    }
  }

  "return NONE for non existing car" in {
    CarCheckService.findCar("SomeNumber", "SomeMake", Some(124l)).futureValue shouldBe None
  }

}
