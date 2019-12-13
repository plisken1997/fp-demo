package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.model.ProgramStarted
import org.scalatest.{Matchers, WordSpec}

class ProgramStartedParserSpec extends WordSpec with Matchers {

  "ProgramStartedParser" should {

    "read a valid json payload" in {
      val msg =
        """
          |{
          |  "userID": "cce78afc-980d-44f2-8fd8-aa40974ab717",
          |  "programID": "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          |  "createdAt": 1576270435000,
          |  "startedAt": 1576270435321
          |}
          |""".stripMargin

      val expectedEvent =
        ProgramStarted(
          "cce78afc-980d-44f2-8fd8-aa40974ab717",
          "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          1576270435000L,
          1576270435321L
        )

      new ProgramStartedParser().read(msg) shouldBe expectedEvent
    }

  }

}

