package lista3;

public class Data {
	private int dia,mes,ano,apDia;
	
	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.apDia = dia+mes*30+ano*365;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getApDia() {
		return apDia;
	}
}
