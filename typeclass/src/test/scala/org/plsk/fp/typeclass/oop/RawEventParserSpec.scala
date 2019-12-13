package org.plsk.fp.typeclass.oop

import org.plsk.fp.typeclass.model.RawEvent
import org.scalatest.{Matchers, WordSpec}

class RawEventParserSpec extends WordSpec with Matchers {

  "RawEventParser" should {

    "read a valid json payload" in {
      val msg =
        """
          |{
          |  "eventType": "program_started",
          |  "payload": {
          |    "userID": "cce78afc-980d-44f2-8fd8-aa40974ab717",
          |    "programID": "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          |    "createdAt": 1576270435000,
          |    "startedAt": 1576270435321
          |  }
          |}
          |""".stripMargin

      val expectedEvent =
        RawEvent(
          "program_started",
          """{
          |  "userID" : "cce78afc-980d-44f2-8fd8-aa40974ab717",
          |  "programID" : "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
          |  "createdAt" : 1576270435000,
          |  "startedAt" : 1576270435321
          |}""".stripMargin.stripLineEnd
        )

      new RawEventParser().read(msg) shouldBe expectedEvent
    }

  }

}


