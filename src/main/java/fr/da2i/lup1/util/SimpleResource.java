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
package fr.da2i.lup1.util;

import java.security.Principal;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import com.google.common.base.Strings;

import fr.da2i.lup1.entity.security.Credential;

public abstract class SimpleResource {
	
	@Context
	protected UriInfo uriInfo;
	
	@Context
	protected SecurityContext securityContext;
	
	protected Principal getPrincipal() {
		return securityContext.getUserPrincipal();
	}
	
	protected Credential getCredential() {
		return (Credential) getPrincipal();
	}
	
	protected Integer getMemberId() {
		return getCredential().getMember().getId();
	}
	
	protected String getAuthenticatedLogin() {
		return Strings.nullToEmpty(securityContext.getUserPrincipal().getName());
	}

}
