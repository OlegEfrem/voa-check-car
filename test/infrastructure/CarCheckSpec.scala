package infrastructure

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import org.scalatest.{Matchers, WordSpec}

trait CarCheckSpec extends WordSpec with Matchers with ScalaFutures with TestData {
  implicit val timeout = PatienceConfig(Span(60, Seconds))
}
