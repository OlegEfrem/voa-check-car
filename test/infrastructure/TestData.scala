package infrastructure

import model.Car

trait TestData {
  val testRegNumber = "WDZ54DMB"
  val testMake = "Audi"
  val testV5c = 12344553456l
  val testCar = Some(Car(testRegNumber, testMake, "Oleg Efrem", Some(testV5c)))
}
