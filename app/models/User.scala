package models

import org.joda.time.DateTime

case class User(
  id: Option[Long],
  email: String,
  password: String,
  name: String,
  createdAt: DateTime = new DateTime
)

object User {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val UserFromJson: Reads[User] = (
    (__ \ "id").readNullable[Long] ~
    (__ \ "email").read(Reads.email) ~
    (__ \ "password").read[String] ~
    (__ \ "name").read[String] ~
    (__ \ "createdAt").read(new DateTime)
  )(User.apply _)

  implicit val UserToJson: Writes[User] = (
    (__ \ "id").writeNullable[Long] ~
    (__ \ "email").write[String] ~
    (__ \ "password").writeNullable[String] ~ // don't write the password
    (__ \ "name").write[String] ~
    (__ \ "createdAt").write[DateTime]
  )((user: User) => (
    user.id,
    user.email,
    None,
    user.name,
    user.createdAt
  ))


  def findOneById(id: Long): Option[User] = {
    // TODO: find the corresponding user
    //
    // For now return a fake user
    if (id == 3) {
      Some(User(Some(3L), "test@test.com", "mypassword", "John Smith"))
    } else {
      None
    }
  }

  def findByEmailAndPassword(email: String, password: String): Option[User] = {
    // TODO: find the corresponding user; don't forget to encrypt the password
    //
    // For now return a fake user
    Some(User(Some(3L), email, password, "John Smith"))
  }

}
