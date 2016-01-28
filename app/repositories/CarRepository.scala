package repositories

import anorm.SqlParser._
import anorm._
import model.Car
import play.api.db.DB
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.Play.current

trait CarRepository {

  val parser =
    str("reg_number") ~ str("make") ~ str("owner") ~ get[Option[Long]]("v5c") map {
      case n ~ md ~ o ~ v => Car(n, md, o, v)
    }

  def findCar(regNumber: String, make: String, v5c: Option[Long]): Future[Option[Car]] = {
    Future {
      DB.withConnection { implicit conn =>
        SQL("select * from car where reg_number = {reg_number} and make = {make} and v5c = {v5c}")
          .on(
            'reg_number -> regNumber,
            "make" -> make,
            "v5c" -> v5c
          )
          .as(parser.singleOpt)
      }
    }
  }
}

object CarRepository extends CarRepository
