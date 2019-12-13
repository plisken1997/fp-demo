package org.plsk.fp.typeclass

object Fixtures {

  val sourceInput = Seq(
    """{
      |  "eventType": "program_started",
      |  "payload":{
      |    "userID": "cce78afc-980d-44f2-8fd8-aa40974ab717",
      |    "programID": "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
      |    "createdAt": 1576270435000,
      |    "startedAt": 1576270435321
      |  }
      |}""".stripMargin,

    """{
      |  "eventType": "program_cancelled",
      |  "payload":{
      |    "userID": "cce78afc-980d-44f2-8fd8-aa40974ab717",
      |    "programID": "d9e08ecb-0dad-4439-bc43-ac3fd4163777",
      |    "createdAt": 1576270435000,
      |    "cancelledAt": 1576270435321
      |  }
      |}""".stripMargin
  )

}
