package hylyht.adapter.servlet

import hylyht.adapter.servlet.response.{InternalServerError, NotFound, Ok, HttpResponse}
import javax.servlet.http.HttpServletRequest
import java.io.IOException

class LoadStaticFile(private val loader: ResourceLoader) extends Action {

    def execute(req: HttpServletRequest): HttpResponse = {
        try {
            loader.load(req.getPathInfo())
                .map { new Ok(_) }
                .getOrElse{ new NotFound }
        }
        catch {
            case e: IOException => {
                return new InternalServerError
            }
        }
    }
}