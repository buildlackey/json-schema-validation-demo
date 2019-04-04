
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.JsonNode
import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.main.{JsonSchema, JsonSchemaFactory}
import com.fasterxml.jackson.databind._
import com.github.fge.jsonschema.core.report.ProcessingReport

object SchemaValidator {
  lazy val mapper: ObjectMapper = new ObjectMapper
  lazy val jsonSchemaFactory: JsonSchemaFactory = JsonSchemaFactory.byDefault
  lazy val schemaNode: JsonNode = JsonLoader.fromResource("/schema.json")
  lazy val schema: JsonSchema = jsonSchemaFactory.getJsonSchema(schemaNode)

  def validateWithReport(json: String): Boolean = {
    val bytes: Array[Byte] = json.getBytes("utf-8")
    val parser: JsonParser = mapper.getFactory.createParser(bytes)
    val node: JsonNode = mapper. readTree( parser)
    val validationResult: ProcessingReport = schema.validate(node)
    if (validationResult.isSuccess) {
      true
    } else {
      val errMsg = s"Validation error. Instance=$json, msg=$validationResult"
      System.out.println("errMsg:" + errMsg)
      false
    }
  }
}

object FakeGoodWebService {
  def getJsonResponse =   """{ "foo": 100 }"""
}

object FakeBadWebService {
  def getJsonResponse =   """{ "zoo": 100 }"""
}


object JsonSchemaValidationDemo extends App {
  import SchemaValidator._

  val goodResult =
    validateWithReport(
      FakeGoodWebService.getJsonResponse)
  System.out.println("result:" + goodResult);

  val badResult =
    validateWithReport(
      FakeBadWebService.getJsonResponse)
  System.out.println("result:" + badResult);
}


