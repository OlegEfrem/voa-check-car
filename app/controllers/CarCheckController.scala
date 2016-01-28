package controllers

import play.api.libs.json._
import play.api.mvc._
import repositories.CarRepository

import scala.concurrent.ExecutionContext.Implicits.global


object CarCheckController extends Controller {

  def carRepository = CarRepository

  //TODO: if spaces are required in input data, move it to body/json object, done this way to illustrate URL param parsing, json back
  def findCar(regNumber: String, make: String, v5c: Option[Long]) = Action.async {
    request =>
      carRepository.findCar(regNumber, make, v5c) map {
        case Some(x) => Ok(Json.toJson(x))
        case None => NotFound
      }
  }
}
