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
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (la 7 Production)
 */
package fr.da2i.lup1.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permet d'exécuter des scripts SQL.
 */
public final class Scripts {
	
	/**
	 * Les options disponibles pour l'exécution d'un script.
	 */
	public enum ScriptOption {
		AUTO_COMMIT, STOP_ON_ERROR, FULL_LINE_DELIMITER;
	}
	
	/**
	 * Exécute chaque script trouvé.
	 */
	private static class ScriptFileExecutor extends SimpleFileVisitor<Path> {

		private Connection con;
		private String delimiter;
		private ScriptOption[] options;
		
		public ScriptFileExecutor(Connection con, String delimiter, ScriptOption... options) {
			this.con = con;
			this.delimiter = delimiter;
			this.options = options;
		}
		
		@Override
		public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
			return FileVisitResult.CONTINUE;
		}
		
		@Override
		public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
			try {
				log.info("Exécution du script " + file);
				Scripts.runScript(con, Files.newBufferedReader(file, Charset.forName("UTF-8")), delimiter, options);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return FileVisitResult.CONTINUE;
		}

	}
	
	private static Logger log = Logger.getLogger("SQL Scripts");
	
	public static final String DEFAULT_DELIMITER = ";";
	public static final String LINE_SEPARATOR = "\n";
	
	/**
	 * Empêche l'instanciation de la classe.
	 */
	private Scripts() {}
	
	/**
	 * Exécute un script sql
	 * 
	 * @param	con
	 * 			la connexion à la base de données
	 * @param	path
	 * 			le chemin d'accès du fichier sql (ou du dossier des fichiers sql)
	 * @param	delimiter
	 * 			le délimiteur entre chaque instructions du script
	 * @param	options
	 * 			les options lors de l'exécution du script
	 * 
	 * @throws	IOException
	 * 			erreur pouvant survenir à l'ouverture du fichier
	 * @throws	SQLException
	 * 			erreur pouvant survenir si une des instructions n'est pas une requête sql
	 */
	public static void runScript(Connection con, Path path, String delimiter, ScriptOption... options) throws IOException, SQLException {
		if (Files.exists(path)) {
			Files.walkFileTree(path, new ScriptFileExecutor(con, delimiter, options));
		}
		else {
			log.log(Level.SEVERE, path + " not found.");
		}
	}
	
	/**
	 * Exécute un script sql
	 * 
	 * @param	con
	 * 			la connexion à la base de données
	 * @param	path
	 * 			le chemin d'accès du fichier sql (ou du dossier de fichiers sql)
	 * @param	options
	 * 			les options lors de l'exécution du script
	 * 
	 * @throws	IOException
	 * 			erreur pouvant survenir à l'ouverture du fichier
	 * @throws	SQLException
	 * 			erreur pouvant survenir si une des instructions n'est pas une requête sql
	 */
	public static void runScript(Connection con, Path path, ScriptOption... options) throws IOException, SQLException {
		runScript(con, path, DEFAULT_DELIMITER, options);
	}
	
	/**
	 * Exécute un script sql
	 * 
	 * @param	con
	 * 			la connexion à la base de données
	 * @param	reader
	 * 			le flux de données équivalent au fichier sql
	 * @param	delimiter
	 * 			le délimiteur entre chaque instructions du script
	 * @param	options
	 * 			les options lors de l'exécution du script
	 * 
	 * @throws	IOException
	 * 			erreur pouvant survenir à l'ouverture du fichier
	 * @throws	SQLException
	 * 			erreur pouvant survenir si une des instructions n'est pas une requête sql
	 */
	public static void runScript(Connection con, Reader reader, String delimiter, ScriptOption... options) throws IOException, SQLException {
		runScript(con, reader, delimiter, Arrays.asList(options));
	}
	
	/**
	 * Exécute un script sql
	 * 
	 * @param	con
	 * 			la connexion à la base de données
	 * @param	reader
	 * 			le flux de données équivalent au fichier sql
	 * @param	options
	 * 			les options lors de l'exécution du script
	 * 
	 * @throws	IOException
	 * 			erreur pouvant survenir à l'ouverture du fichier
	 * @throws	SQLException
	 * 			erreur pouvant survenir si une des instructions n'est pas une requête sql
	 */
	public static void runScript(Connection con, Reader reader, ScriptOption... options) throws IOException, SQLException {
		runScript(con, reader, DEFAULT_DELIMITER, options);
	}
	
	/**
	 * Exécute un script sql
	 * 
	 * @param	con
	 * 			la connexion à la base de données
	 * @param	reader
	 * 			le flux de données équivalent au fichier sql
	 * @param	delimiter
	 * 			le délimiteur entre chaque instructions du script
	 * @param	options
	 * 			les options lors de l'exécution du script
	 * 
	 * @throws	IOException
	 * 			erreur pouvant survenir à l'ouverture du fichier
	 * @throws	SQLException
	 * 			erreur pouvant survenir si une des instructions n'est pas une requête sql
	 */
	private static void runScript(Connection con, Reader reader, String delimiter, List<ScriptOption> options) throws IOException, SQLException {
		StringBuffer buffer = new StringBuffer();
		
		String line;
		String trimmedLine;
		Statement statement;
		
		String command = null;
		boolean fullLineDelimiter = options.contains(ScriptOption.FULL_LINE_DELIMITER);
		boolean stopOnError = options.contains(ScriptOption.STOP_ON_ERROR);
		boolean autoCommit = options.contains(ScriptOption.AUTO_COMMIT);
		
		try {
			if (autoCommit != con.getAutoCommit()) {
				con.setAutoCommit(autoCommit);
			}
		} catch (Throwable t) {
			throw new RuntimeException("Could not set AutoCommit to " + autoCommit, t);
		}
		
		try (LineNumberReader lineReader = new LineNumberReader(reader)) {
			while ((line = lineReader.readLine()) != null) {
				trimmedLine = line.trim();
				
				if (trimmedLine.length() == 0 || trimmedLine.startsWith("//") || trimmedLine.startsWith("--")) {
					continue;
				}
				
				if (!fullLineDelimiter && trimmedLine.endsWith(delimiter) || fullLineDelimiter && trimmedLine.equals(delimiter)) {
					buffer.append(line.substring(0, line.lastIndexOf(delimiter))).append(LINE_SEPARATOR);
					statement = con.createStatement();
					command = buffer.toString();
					
					log.info(command);
					
					if (stopOnError) {
						statement.execute(command);
					}
					else {
						try {
							statement.execute(command);
						}
						catch (SQLException e) {
							e.fillInStackTrace();
							log.log(Level.SEVERE, "SQL error in " + command, e);
						}
					}
					
					if (autoCommit && !con.getAutoCommit()) {
						con.commit();
					}
					
					try {
						statement.close();
					}
					catch (SQLException e) {
						e.fillInStackTrace();
					}
					
					buffer.setLength(0);
					Thread.yield();
				}
				else {
					buffer.append(line).append(LINE_SEPARATOR);
				}
				
				if (!autoCommit) {
					con.commit();
				}
			}
		}
		catch (IOException | SQLException e) {
			e.fillInStackTrace();
			log.log(Level.SEVERE, "SQL error in " + command, e);
			throw e;
		}
		finally {
			con.rollback();
		}
	}

}
