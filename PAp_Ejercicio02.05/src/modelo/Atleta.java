package modelo;

public class Atleta extends Persona implements java.io.Serializable {

	private String codigo;
	private Resultado resultado;
	private Competencia competencia;
	
	public Atleta() {
		
	}

	public Atleta(String codigo, Resultado resultado, Competencia competencia) {
		super();
		this.codigo = codigo;
		this.resultado = resultado;
		this.competencia = competencia;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	@Override
	public String toString() {
		return "Atleta [codigo=" + codigo + ", resultado=" + resultado + ", competencia=" + competencia + "]";
	}

}
