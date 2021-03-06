/**
 * This file is part of lup1.
 *
 * lup1 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * lup1 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with lup1.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.da2i.lup1;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import fr.da2i.lup1.filter.PromotionFilter;
import fr.da2i.lup1.filter.SemesterFilter;
import fr.da2i.lup1.io.GZIPWriterInterceptor;
import fr.da2i.lup1.security.AuthenticationFilter;
import fr.da2i.lup1.security.AuthenticationServiceFeature;
import fr.da2i.lup1.security.JwtManagerFeature;
import fr.da2i.lup1.util.CORSReponseFilter;

/**
 * {@link javax.ws.rs.core.Application} charge toutes les ressources de l'application.
 */
@ApplicationPath("api")
public class App extends ResourceConfig {
	
	public App() {
		register(LoggingFilter.class);
		register(RolesAllowedDynamicFeature.class);
		register(CORSReponseFilter.class);
		register(JwtManagerFeature.class);
		register(AuthenticationServiceFeature.class);
		register(AuthenticationFilter.class);
		register(PromotionFilter.class);
		register(SemesterFilter.class);
		register(MultiPartFeature.class);
		register(GZIPWriterInterceptor.class);
//		register(GZIPReaderInterceptor.class);
		packages("fr.da2i.lup1.resource");
	}
}
