package hylyht.adapter.servlet

import hylyht.adapter.servlet.resource.Resource

trait ResourceLoader {

	def load(path: String): Option[Resource]

}
