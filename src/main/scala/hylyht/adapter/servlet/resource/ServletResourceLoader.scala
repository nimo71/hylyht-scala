package hylyht.adapter.servlet.resource

import hylyht.adapter.servlet.ResourceLoader
import javax.servlet.ServletContext


object ServletResourceLoader {
  private val DEFAULT_FILE_EXTENSION = "html"
}

class ServletResourceLoader(
        private val servletContext: ServletContext) extends ResourceLoader
{

    def load(pathInfo: String): Option[Resource] = {
        val mime = new ExtensionToMimeType().apply(parseFileExtension(pathInfo))
        val in = servletContext.getResourceAsStream(pathInfo)
        if (in == null)
            None
        else
            Some(new StaticResource(in, mime))
    }

    private def parseFileExtension(pathInfo: String): String  = {
        val parts = pathInfo.split("\\.");
        if (parts.length > 1)
            parts(parts.length - 1)
        else
            ServletResourceLoader.DEFAULT_FILE_EXTENSION;
    }

}