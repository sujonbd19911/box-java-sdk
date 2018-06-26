//  This file was automatically generated and should not be edited.


object GetAllTemplatesQuery extends com.apollographql.scalajs.GraphQLQuery {
  val operationString =
    "query getAllTemplates {" +
    "  templates(first: 3) {" +
    "    items {" +
    "      id" +
    "      name" +
    "    }" +
    "  }" +
    "}"
  val operation = com.apollographql.scalajs.gql(operationString)

  type Variables = Unit

  case class Data(templates: Option[Data.Template]) {
  }

  object Data {
    val possibleTypes = scala.collection.Set("RootQueryType")

    case class Template(items: Option[Seq[Option[Template.Item]]]) {
    }

    object Template {
      val possibleTypes = scala.collection.Set("TemplateCollection")

      case class Item(id: Option[String], name: Option[String]) {
      }

      object Item {
        val possibleTypes = scala.collection.Set("Template")
      }
    }
  }
}