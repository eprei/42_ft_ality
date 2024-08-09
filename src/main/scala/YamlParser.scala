import cats.syntax.either.*
import io.circe.generic.auto.*
import io.circe.yaml
import io.circe.*

object YamlParser:
  def parseYaml(content: String): Either[Throwable, Grammar] = 
    def processYaml(yamlContent: Either[ParsingFailure, Json]): Either[Error, Grammar] =
      yamlContent.leftMap(err => err: Error).flatMap(_.as[Grammar])

    processYaml(yaml.parser.parse(content)).leftMap(err => err: Throwable)
