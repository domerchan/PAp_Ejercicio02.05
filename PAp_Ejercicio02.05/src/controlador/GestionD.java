package controlador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import modelo.Atleta;
import modelo.Competencia;
import modelo.Resultado;

public class GestionD {

	private List<Competencia> competencias;
	private List<Atleta> atletas;
	private List<Atleta> atletasAJ;
	private List<Atleta> atletasAS;
	private List<Atleta> atletasBJ;
	private List<Atleta> atletasBS;
	private String pathD = "src/archivos/D.txt";
	private RandomAccessFile file;

	public GestionD() throws IOException {
		competencias = new ArrayList<Competencia>();
		atletas = new ArrayList<Atleta>();
		atletasAJ = new ArrayList<Atleta>();
		atletasBJ = new ArrayList<Atleta>();
		atletasAS = new ArrayList<Atleta>();
		atletasBS = new ArrayList<Atleta>();

		addCompetencia();
	}

	public void newRegistro(String nombre, String apellido, String cedula, String codigo, String tinicial,
			String tfinal, String cnombre, String categoria) throws IOException {
		try {
			Atleta atleta = new Atleta();
			Resultado resultado = new Resultado();
			Competencia competencia = searchCompetencia(cnombre, categoria);

			resultado.settInicial(tinicial);
			resultado.settFinal(tfinal);

			atleta.setNombre(nombre);
			atleta.setApellido(apellido);
			atleta.setCedula(cedula);
			atleta.setCodigo(codigo);
			atleta.setResultado(resultado);

			if (competencias.get(0).getNombre().equals(competencia))
				if (competencias.get(0).getCategoria().equals("Juvenil")) {
					atletasAJ.add(atleta);
					atletas.add(atleta);
				} else {
					atletasAS.add(atleta);
					atletas.add(atleta);
				}
			else if (competencias.get(1).getNombre().equals(competencia))
				if (competencias.get(1).getCategoria().equals("Juvenil")) {
					atletasBJ.add(atleta);
					atletas.add(atleta);
				} else {
					atletasBS.add(atleta);
					atletas.add(atleta);
				}

			file = new RandomAccessFile(pathD, "rw");
			file.writeUTF(nombre);
			file.writeUTF(apellido);
			file.writeUTF(cedula);
			file.writeUTF(codigo);
			file.writeUTF(tinicial);
			file.writeUTF(tfinal);
			file.writeUTF(cnombre);
			file.writeUTF(categoria);

		} catch (IOException evento) {
			evento.printStackTrace();
		} finally {
			file.close();
		}
	}

	public RandomAccessFile getFile() {
		return file;
	}

	public void addCompetencia() {
			Competencia competencia = new Competencia();
			competencia.setNombre("Atletismo");
			competencia.setCategoria("Senior");
			competencia.setAtletas(atletasAS);
			competencias.add(competencia);

			competencia = new Competencia();
			competencia.setNombre("Atletismo");
			competencia.setCategoria("Juvenil");
			competencia.setAtletas(atletasAJ);
			competencias.add(competencia);

			competencia = new Competencia();
			competencia.setNombre("Baloncesto");
			competencia.setCategoria("Juvenil");
			competencia.setAtletas(atletasBJ);
			competencias.add(competencia);

			competencia = new Competencia();
			competencia.setNombre("Baloncesto");
			competencia.setCategoria("Senior");
			competencia.setAtletas(atletasBS);
			competencias.add(competencia);
	}

	public List<Atleta> listAtletas() {
		return atletas;
	}

	public String[] getCompetencias() {
		String[] lista = new String[competencias.size()];
		System.out.println(competencias.size());
		for (int i = 0; i < competencias.size(); i++)
			lista[i] = competencias.get(i).getNombre();
		return lista;
	}

	public boolean validarCedula(String cedula) throws Exception {
		try {
			int a = Integer.parseInt(cedula);
		} catch (NumberFormatException e) {
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if (cedula.length() != 10)
			throw new Exception("Debe ser de 10 dígitos");
		return true;
	}

	public boolean validarCodigo(String codigo) throws Exception {
		if (codigo.length() != 5)
			throw new Exception("Código inválido");
		return true;
	}

	public boolean validarTiempo(String tiempo) throws Exception {
		if (tiempo.length() != 5)
			throw new Exception("Tiempo inválido");
		return true;
	}

	public String complete(String dato) {
		for (int i = dato.length() + 1; i < 11; i++)
			dato = dato + " ";
		return dato;
	}

	public Competencia searchCompetencia(String nombre, String categoria) {
		System.out.println(competencias.size());
		for (int i = 0; i < competencias.size(); i++) {
			Competencia c = competencias.get(i);
			if (c.getNombre().equals(nombre) && c.getCategoria().equals(categoria))
				return c;
		}
		return null;
	}

	/*
	 * public boolean duplicadosAtletas(String nombre, String codigo) { for (int
	 * i = 0; i < atletas.size(); i++) { if
	 * (atletas.get(i).getNombre().equals(nombre) &&
	 * atletas.get(i).getCodigo().equals(codigo)) { return true; } } return
	 * false; }
	 */
}
