package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Pelicula;

public class PeliculaDAO {
	
	db.Db db = new db.Db("CineStar");


	public Object getPeliculas(int id,boolean bLista) {
		db.Sentencia( String.format("call sp_getPeliculas(%s)", id));
		String[][] peliculas= db.getRegistros();
		
		
		if(peliculas == null ) return null;
		if(!bLista) return peliculas;
		
		List<Pelicula> lstPeliculas = new ArrayList<>();
		for(String[] pelicula :peliculas)
			lstPeliculas.add( new Pelicula(pelicula));
		return lstPeliculas;
	}
	
	public Object getPelicula(String id,boolean bEntidad) {
		db.Sentencia( String.format("call sp_getPelicula(%s)", id));
		String[] aRegistro= db.getRegistro();
		
		
		if(aRegistro == null ) return null;
		db.Sentencia(String.format("select getGenerosDetalle('%s')",aRegistro[4]));
		aRegistro[4]=db.getCampo();
		if(!bEntidad) return aRegistro;
		return new Pelicula(aRegistro);
	}
	
	
}
