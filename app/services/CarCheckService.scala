package services

import model.Car
import repositories.CarRepository
import scala.concurrent.Future


trait CarCheckService  {

  def carRepository: CarRepository

  def findCar(regNumber: String, make: String, v5c: Option[Long]): Future[Option[Car]] =
    carRepository.findCar(regNumber, make, v5c)
}

object CarCheckService extends CarCheckService {
  def carRepository = CarRepository
}
