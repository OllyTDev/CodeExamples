val input = "{\n    \"@odata.context\": \"https://example.code/RestApi/v1/$entity\",\n    \"JobId\": \"0x06fd4b81f6915dcc\",\n    \"Notes\": [\n        \"(215rgga84frfr)\\r\\nProcessing started\\r\\nUser ID: 1\\r\\nExtraction ID: 2000000130626996\\r\\nSchedule: 0x06fd4b81f6915dcc (ID = 0x0000000000000000)\\r\\nInput List (1 items):  (ID = 0x06fd4b81f6915dcc) Created: 02/20/2020 14:46:40 Last Modified: 02/20/2020 14:46:40\\r\\nInstrument <RIC,AED=> expanded to 1 RIC: AED=.\\r\\nTotal instruments after instrument expansion = 1\\r\\nReport Template (3 fields): _OnD_0x06fd4b81f6915dcc (ID = 0x06fd4b81f6e15dcc) Created: 02/20/2020 14:46:35 Last Modified: 02/20/2020 14:46:35\\r\\nSchedule dispatched via message queue (0x06fd4b81f6915dcc)\\r\\nSchedule Time: 02/20/2020 14:46:37\\r\\nProcessing completed successfully at 02/20/2020 14:46:41, taking 1.424 Secs.\\r\\nExtraction finished at 02/20/2020 14:46:41 UTC, with servers: x03q13, QSSHA1 (0.3 secs), TRTH (1.1 secs)\\r\\n\"\n    ],\n    \"IdentifierValidationErrors\": [\n        {\n            \"Identifier\": {\n                \"@odata.type\": \"#ThomsonReuters.Dss.Api.Content.InstrumentIdentifier\",\n                \"Identifier\": \"XXX=\",\n                \"IdentifierType\": \"Ric\",\n                \"Source\": \"\"\n            },\n            \"Message\": \"Not found\"\n        }\n    ]\n}"

val input2 = "{\n    \"@odata.context\": \"https://example.code/RestApi/v1/$entity\",\n    \"JobId\": \"0x06fd4b9505015dcc\",\n    \"Notes\": [\n        \"(215rgga84frfr)\\r\\nProcessing started\\r\\nUser ID: 1\\r\\nExtraction ID: 2000000130630775\\r\\nSchedule: 0x06fd4b9505015dcc (ID = 0x0000000000000000)\\r\\nInput List (1 items):  (ID = 0x06fd4b9505015dcc) Created: 02/20/2020 15:22:42 Last Modified: 02/20/2020 15:22:42\\r\\nInstrument <RIC,AED=> expanded to 1 RIC: AED=.\\r\\nTotal instruments after instrument expansion = 1\\r\\nReport Template (3 fields): _OnD_0x06fd4b9505015dcc (ID = 0x06fd4b9505e15dcc) Created: 02/20/2020 15:22:37 Last Modified: 02/20/2020 15:22:37\\r\\nSchedule dispatched via message queue (0x06fd4b9505015dcc)\\r\\nSchedule Time: 02/20/2020 15:22:39\\r\\nProcessing completed successfully at 02/20/2020 15:22:43, taking 1.411 Secs.\\r\\nExtraction finished at 02/20/2020 15:22:43 UTC, with servers: x03q13, QSSHA1 (0.0 secs), TRTH (1.3 secs)\\r\\n\"\n    ],\n    \"IdentifierValidationErrors\": [\n        {\n            \"Identifier\": {\n                \"@odata.type\": \"#ThomsonReuters.Dss.Api.Content.InstrumentIdentifier\",\n                \"Identifier\": \"XXX=\",\n                \"IdentifierType\": \"Ric\",\n                \"Source\": \"\"\n            },\n            \"Message\": \"Not found\"\n        },\n        {\n            \"Identifier\": {\n                \"@odata.type\": \"#ThomsonReuters.Dss.Api.Content.InstrumentIdentifier\",\n                \"Identifier\": \"ABC=\",\n                \"IdentifierType\": \"Ric\",\n                \"Source\": \"\"\n            },\n            \"Message\": \"Not found\"\n        }\n    ]\n}"
//code takes the body of a HTTP response
//extracts down to some specific parameters from the body which can the be used to process any response
def getIndentifier(indenties:String): Seq[String] = {
  val a = indenties.split("\"Identifier\": \"")
  val rics = a.tail.map(b => b.substring(0, 3)).toList
  rics
}

def getFullList(mainString:Array[String], rics: Seq[String]) : Seq[String] ={
  val split1 = mainString.last.split("\"IdentifierValidationErrors\"").last
  if (split1.contains("\"Identifier\": \"")){
    getIndentifier(split1)
  }
  else {
    rics
  }
}

getFullList(Array("invalid: input"), Seq(""))
getFullList(Array(input),Seq(""))
getFullList(Array(input2),Seq(""))


