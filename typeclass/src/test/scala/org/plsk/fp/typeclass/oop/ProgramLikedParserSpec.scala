package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.ProgramLiked
import org.scalatest.{Matchers, WordSpec}

class ProgramLikedParserSpec extends WordSpec with Matchers {

  "ProgramLikedParser" should {

    "read a valid json payload" in {
      val msg =
        """
          |{
          |  "userID": "cce78afc-980d-44f2-8fd8-aa40974ab717",
          |  "programID": "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          |  "createdAt": 1576270435000,
          |  "likedAt": 1576270435321
          |}
          |""".stripMargin

      val expectedEvent =
        ProgramLiked(
          "cce78afc-980d-44f2-8fd8-aa40974ab717",
          "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          1576270435000L,
          1576270435321L
        )

      new ProgramLikedParser().read(msg) shouldBe expectedEvent
    }

  }

}
