package hylyht.adapter.filter

import com.sun.jersey.spi.container.{ContainerRequest, ContainerRequestFilter}
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response.Status
import org.slf4j.LoggerFactory

class AuthFilter extends ContainerRequestFilter {

    def logger = LoggerFactory.getLogger(classOf[AuthFilter])

    /**
      * Apply the filter : check input request, validate or not with user auth
     */
    @throws(classOf[WebApplicationException])
    override def filter(request: ContainerRequest): ContainerRequest = {

        logger.debug("AuthFilter.filter(), path="+ request.getPath())

        if (isRoot(request)
            || isIndex(request)
            || isStaticResource(request)
            || isLoginPage(request)
            || isRegistrationPage(request)
            || isCreateUserApi(request)) {
            return request
        }

//
//        val auth = request.getHeaderValue("authorization")
//
//        if(auth == null){
//            throw new WebApplicationException(Status.UNAUTHORIZED)
//        }
//
//        val usernameAndPassword = BasicAuth.decode(auth);
//
//        //If login or password fail
//        if(usernameAndPassword == null || usernameAndPassword.length != 2){
//            throw new WebApplicationException(Status.UNAUTHORIZED)
//        }

        //DO YOUR DATABASE CHECK HERE (replace that line behind)...
        //User authentificationResult =  AuthentificationThirdParty.authentification(lap[0], lap[1]);

        //Our system refuse login and password
        //        if(authentificationResult == null){
        //        throw new WebApplicationException(Status.UNAUTHORIZED);
        //        }

        //        TODO : HERE YOU SHOULD ADD PARAMETER TO REQUEST, TO REMEMBER USER ON YOUR REST SERVICE...
        //request.setSecurityContext(new HylyhtSecurityContext(userId, userRole))

        throw new WebApplicationException(Status.UNAUTHORIZED)
//        return request;
    }

    def isRoot(request: ContainerRequest) = request.getPath() == ""

    def isIndex(request: ContainerRequest) = request.getPath() == "index.html"

    def isStaticResource(request: ContainerRequest): Boolean = request.getPath().matches(".+\\.css|.+\\.js")

    def isLoginPage(request: ContainerRequest) = request.getPath() == "view/loginForm.html"

    def isRegistrationPage(request: ContainerRequest) = request.getPath() == "view/registrationForm.html"

    def isCreateUserApi(request: ContainerRequest) =
        request.getMethod() == "PUT" && request.getPath() == "user"
}