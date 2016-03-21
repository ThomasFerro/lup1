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

import java.io.Serializable;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

/**
 * Webservice en REST
 * 
 * @param	<ID>
 * 			le type de l'identifiant
 * @param	<T>
 * 			le type de l'objet associé
 */
public interface Restlet<ID extends Serializable, T extends Identifiable<ID>> {
	
	/**
	 * Crée une ressource
	 * 
	 * @param	entity
	 * 			l'entité à créer
	 * 
	 * @return	la réponse HTTP adéquate selon la réussite ou non de la création
	 * 
	 * @throws	SQLException
	 * 			erreur pouvant se produire lors de l'ajout en base de données
	 */
	Response create(T entity) throws SQLException;
	
	/**
	 * Récupère une ressource
	 * 
	 * @param	id
	 * 			l'identifiant de la ressource
	 * 
	 * @return	OK avec la ressource récupérée ou NOT_FOUND si la ressource n'existe pas
	 * 
	 * @throws	SQLException
	 * 			erreur pouvant se produire lors de la récupération d'un objet en base
	 */
	Response get(ID id) throws SQLException;
	
	/**
	 * Met à jour une ressource
	 * 
	 * @param	id
	 * 			l'identifiant de la ressource
	 * @param	entity
	 * 			la version modifiée de la ressource
	 * 
	 * @return	NO_CONTENT en cas de succès, NOT_FOUND sinon
	 * 
	 * @throws	SQLException
	 * 			erreur pouvant se produire lors de la mise à jour de l'objet en base
	 */
	Response update(ID id, T entity) throws SQLException;
	
	/**
	 * Supprime une ressource
	 * 
	 * @param	id
	 * 			l'identifiant de la ressource
	 * 
	 * @return	NO_CONTENT en cas de succès, NOT_FOUND sinon
	 * 
	 * @throws	SQLException
	 * 			erreur pouvant se produire lors de la suppression de l'objet en base
	 */
	Response delete(ID id) throws SQLException;

}
