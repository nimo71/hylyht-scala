package hylyht.adapter.ws.resource

import com.sun.jersey.api.Responses
import javax.servlet.ServletContext
import javax.ws.rs._
import javax.ws.rs.core.{UriInfo, MediaType, Response, Context}
import java.net.URI
import hylyht.adapter.io.ServletContentLoader

@Path("")
class StaticResourceEndpoint {

    @Context
    var servletContext: ServletContext = _

    private lazy val contentLoader = new ServletContentLoader(servletContext)

    @GET
    @Produces(Array(MediaType.TEXT_HTML))
    def getRoot(@Context ui : UriInfo): Response =
        Response.seeOther(ui.getBaseUriBuilder().path("index.html").build()).build()

    @GET
    @Path("{pathInfo:.+\\.html$}")
    @Produces(Array(MediaType.TEXT_HTML))
    def getHtml(@PathParam("pathInfo") pathInfo: String): Response = loadStatic(pathInfo)

    @GET
    @Path("{pathInfo:.+\\.css$}")
    @Produces(Array("text/css"))
    def getCss(@PathParam("pathInfo") pathInfo: String): Response = loadStatic(pathInfo)

    @GET
    @Path("{pathInfo:.+\\.js$}")
    @Produces(Array("application/javascript"))
    def getJs(@PathParam("pathInfo") pathInfo: String): Response = loadStatic(pathInfo)

    private def loadStatic(pathInfo: String): Response = {
        val filePath = "/" + pathInfo
        contentLoader.load(filePath).map { content =>
            Response.ok(content.getContentString).build
        }
        .getOrElse {
            Responses.notFound().build
        }
    }
}
