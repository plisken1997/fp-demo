package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.domain.event.ProgramCancelled
import org.scalatest.{Matchers, WordSpec}

class ProgramCancelledParserSpec extends WordSpec with Matchers {

  "ProgramCancelledParser" should {

    "read a valid json payload" in {
      val msg =
        """
          |{
          |  "userID": "cce78afc-980d-44f2-8fd8-aa40974ab717",
          |  "programID": "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          |  "createdAt": 1576270435000,
          |  "cancelledAt": 1576270435321
          |}
          |""".stripMargin

      val expectedEvent =
        ProgramCancelled(
          "cce78afc-980d-44f2-8fd8-aa40974ab717",
          "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          1576270435000L,
          1576270435321L
        )

      new ProgramCancelledParser().read(msg) shouldBe expectedEvent
    }

  }

}
