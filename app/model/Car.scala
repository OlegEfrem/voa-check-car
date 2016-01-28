package model

import play.api.libs.json.Json

case class Car(regNumber: String, make: String, owner: String, v5c: Option[Long])

object Car {
  implicit val format = Json.format[Car]
}