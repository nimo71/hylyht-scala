package hylyht.adapter.servlet

import hylyht.adapter.servlet.resource.Content

trait ContentLoader {

	def load(path: String): Option[Content]

}
