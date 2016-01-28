package repositories

import infrastructure.CarCheckSpec
import org.scalatestplus.play.OneAppPerTest
import play.api.db.DB


class CarRepositorySpec extends CarCheckSpec with OneAppPerTest {

  "Evolutions scripts check" should {

    "return an existing car" in {
      DB.withConnection { conn =>
        val st = conn.prepareStatement(s"select * from Car where reg_number = '$testRegNumber' ")
        val rs = st.executeQuery()
        if (rs.next()) {
          rs.getString(1) shouldBe testRegNumber
          rs.getString(2) shouldBe testMake
          rs.getString(3) shouldBe "Oleg Efrem"
          rs.getLong(4) shouldBe testV5c
        } else {
          fail("Car not found")
        }
      }
    }

    "return empty result set for non existing car" in {
      pending
    }
  }

  "findCar service method" should {
    "return an existing car" in {
      val car = CarRepository.findCar(testRegNumber, testMake, Some(testV5c)).futureValue
      car shouldBe testCar
    }

    "return NONE for not existing car" in {
      CarRepository.findCar("SomeNumber", "SomeMake", Some(123l)).futureValue shouldBe None
    }
  }
}
