package controlador;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import modelo.Atleta;
import modelo.Competencia;
import modelo.Resultado;

public class GestionD {

	private List<Atleta> atletas;
	private String pathD = "src/archivos/D.txt";
	private int tamano = 75;

	public GestionD() throws Exception {
		cargarDatosArchivo();
	}

	private void cargarDatosArchivo() throws Exception {
		atletas = getAtletasArchivo();
	}

	private List<Atleta> getAtletasArchivo() {
		List<Atleta> atletas = new ArrayList<>();

		try {
			FileInputStream in = new FileInputStream(pathD);
			ObjectInputStream lect = new ObjectInputStream(in);
			Atleta a = (Atleta) lect.readObject();
			atletas.add(a);
		} catch (Exception e) {
		}
		return atletas;
	}

	public void newRegistro(String nombre, String apellido, String cedula, String codigo, String tinicial,
			String tfinal, String cnombre, String categoria, int registro) throws Exception {
		Atleta a = new Atleta();
		Competencia c = new Competencia();
		Resultado r = new Resultado();
		r.settInicial(tinicial);
		r.settFinal(tfinal);
		c.setNombre(cnombre);
		c.setCategoria(categoria);
		a.setNombre(nombre);
		a.setApellido(apellido);
		a.setCedula(cedula);
		a.setCodigo(codigo);
		a.setResultado(r);
		a.setCompetencia(c);

		try {
			if (registro > 0) {
				// editarAtletaArchivo(registro, a);
				// cargarDatosArchivo();
			} else {
				guardarAtletaArchivo(a);
				atletas.add(a);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Error al guardar datos");
		}
	}

	public void guardarAtletaArchivo(Atleta a) throws IOException {
		try {
			FileOutputStream in = new FileOutputStream(pathD);
			ObjectOutputStream esc = new ObjectOutputStream(in);
			esc.writeObject(a);
			esc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String tamanio(String texto, int t) {
		if (texto.length() > t)
			return texto.substring(t);
		for (int i = texto.length(); i < t; i++)
			texto += " ";
		return texto;
	}

	public List<Atleta> getAtletas() {
		return atletas;
	}

	public Atleta getAtletaArchivo(int registro) {
		return atletas.get(registro - 1);
	}

	private void editarAtletaArchivo(int registro, Atleta a) throws IOException {
		RandomAccessFile in = new RandomAccessFile(pathD, "rw");
		in.seek((registro - 1) * tamano);
		in.writeUTF(tamanio(a.getNombre(), 8));
		in.writeUTF(tamanio(a.getApellido(), 8));
		in.writeUTF(tamanio(a.getCedula(), 10));
		in.writeUTF(tamanio(a.getCodigo(), 5));
		in.writeUTF(tamanio(a.getResultado().gettInicial(), 5));
		in.writeUTF(tamanio(a.getResultado().gettFinal(), 5));
		in.writeUTF(tamanio(a.getCompetencia().getNombre(), 10));
		in.writeUTF(tamanio(a.getCompetencia().getCategoria() + "\n", 8));
		in.close();
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
}
