package perf.load.data

import java.util.UUID
import scala.util.Random

object DataGen {

  private val emailDomains = Vector(
    "gmail.com", "outlook.com", "yahoo.com", "icloud.com", "proton.me"
  )

  def randomEmail(): String = {
    val local = "user_" + UUID.randomUUID().toString.replace("-", "").take(12)
    val domain = emailDomains(Random.nextInt(emailDomains.length))
    s"$local@$domain"
  }

  def randomPassword(len: Int = 12): String =
    Random.alphanumeric.filter(_.isLetterOrDigit).take(len).mkString

  def randomAppId(): Int = 1
}
