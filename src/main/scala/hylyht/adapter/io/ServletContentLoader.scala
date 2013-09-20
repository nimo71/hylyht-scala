package hylyht.adapter.io

import hylyht.adapter.ws.ContentLoader
import javax.servlet.ServletContext
import hylyht.adapter.ws.resource.{StaticContent, Content}

class ServletContentLoader(private val servletContext: ServletContext)
    extends ContentLoader
{

    def load(pathInfo: String): Option[Content] = {
        val in = servletContext.getResourceAsStream(pathInfo)
        if (in == null)
            None
        else
            Some(new StaticContent(in))
    }

}