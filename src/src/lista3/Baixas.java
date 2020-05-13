package lista3;

public class Baixas {
	private double valor;
	private String tipo;
	private Data data;
	
	public Baixas(String tipo, double d, int dia, int mes, int ano) {
		this.tipo = tipo;
		this.valor = d;
		this.data = new Data(dia,mes,ano);
	}

	public void getSemana() {
		
	}
	public String getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public Data getData() {
		return data;
	}
	
}
