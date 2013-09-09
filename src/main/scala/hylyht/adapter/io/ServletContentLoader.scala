package hylyht.adapter.io

import hylyht.adapter.servlet.ContentLoader
import javax.servlet.ServletContext
import hylyht.adapter.servlet.resource.{StaticContent, Content}

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