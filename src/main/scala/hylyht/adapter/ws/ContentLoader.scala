package hylyht.adapter.ws

import hylyht.adapter.ws.resource.Content

trait ContentLoader {

	def load(path: String): Option[Content]

}
