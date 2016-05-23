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
package fr.da2i.lup1.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Cette classe offre des fonctionnalités pour manipuler les objets json.
 */
public final class JsonFileBuilder {
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private JsonFileBuilder() {}
	
	/**
	 * Transforme un fichier JSON sauvegardé sur le disque en objet JAVA
	 * 
	 * @param	jsonPath
	 * 			le chemin d'accès du fichier json à lire
	 * @param	type
	 * 			la classe de l'objet JAVA a retrouver
	 * 
	 * @return	l'objet JAVA retrouvé via le fichier json
	 */
	public static <E> E readJSON(Path jsonPath, Class<E> type) {
		ObjectMapper mapper = new ObjectMapper();
		E elt = null;
		if (Files.exists(jsonPath)) {
			try {
				elt = mapper.readValue(jsonPath.toFile(), type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return elt;
	}
	
	/**
	 * Transforme un objet JAVA en fichier JSON sauvegardé sur le disque
	 * 
	 * @param	jsonPath
	 * 			le chemin d'accès du fichier json à sauvegarder
	 * @param	o
	 * 			l'objet à transformer
	 * 
	 * @return	vrai si l'écriture du fichier s'est bien déroulée, faux sinon
	 */
	public static boolean writeJSON(Path jsonPath, Object o) {
		ObjectMapper mapper = new ObjectMapper();
		Path parent = jsonPath.getParent();
		boolean success = false;
		if (!Files.exists(parent)) {
			IO.mkdirs(parent);
		}
		try {
			mapper.writeValue(jsonPath.toFile(), o);
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}

}
