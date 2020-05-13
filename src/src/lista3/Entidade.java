package lista3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entidade {
	private String nome,telefone,email,pessoa,rua,numero,estado;
	private List<Baixas> caixa = new ArrayList<Baixas>();
	
	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getEstado() {
		return estado;
	}

	public List<Baixas> getCaixa() {
		return caixa;
	}

	public Entidade(String nome, String telefone, String email, String pessoa, String rua, String numero,
			String estado) {
		this.nome = nome;
		this.telefone = telefone;
		this.email =email;
		this.pessoa = pessoa;
		this.rua = rua;
		this.numero = numero;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public String getPessoa() {
		return pessoa;
	}

	public void addBaixa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o tipo de Baixa:\n1-Entrada\n2-Despesa");
		boolean k = false;
		while(!k) {
			switch(sc.nextLine()) {
			case "1":
				addEntrada();
				k=true;
				break;
			case "2":
				addDespesa();
				k=true;
				break;
			default:
				System.out.println("Entrada Inválida");
			}
		}
	}

	private void addEntrada() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o tipo de entrada:");
		if(pessoa.equals("jur"))
			System.out.println("1-Receita");
		else
			System.out.println("1-Salário");
		System.out.println("2-Investimentos");
		boolean k = false;
		String tipo="";
		while(!k) {
			switch(sc.nextLine()) {
			case "1":
				if(pessoa.equals("jur"))
					tipo = "Receita";
				else
					tipo = "Salário";
				k=true;
				break;
			case "2":
				tipo = "Investimentos";
				k=true;
				break;
			default:
				System.out.println("Entrada Inválida");
			}
		}
		System.out.println("Insira o valor: ");
		double valor = sc.nextDouble();
		System.out.println("Insira o dia: ");
		int dia = sc.nextInt();
		System.out.println("Insira o mês: ");
		int mes = sc.nextInt();
		System.out.println("Insira o ano: ");
		int ano = sc.nextInt();
		caixa.add(new Baixas(tipo, valor, dia, mes, ano));
	}

	private void addDespesa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o tipo de despesa:\n1-Energia Elétrica\n2-Gás\n3-Alimentação\n4-Combustível\n5-Outros");
		boolean k = false;
		String tipo="";
		while(!k) {
			switch(sc.nextLine()) {
			case "1":
				tipo = "Energia Elétrica";
				k=true;
				break;
			case "2":
				tipo = "Gás";
				k=true;
				break;
			case "3":
				tipo = "Alimentação";
				k=true;
				break;
			case "4":
				tipo = "Combustível";
				k=true;
				break;
			case "5":
				tipo = "Outros";
				k=true;
				break;
			default:
				System.out.println("Entrada Inválida");
			}
		}
		System.out.println("Insira o valor: ");
		double valor = sc.nextDouble();
		System.out.println("Insira o dia: ");
		int dia = sc.nextInt();
		System.out.println("Insira o mês: ");
		int mes = sc.nextInt();
		System.out.println("Insira o ano: ");
		int ano = sc.nextInt();
		caixa.add(new Baixas(tipo, -valor, dia, mes, ano));
	}
	
	public List<Baixas> getBaixas() {
		return caixa;
	}
}
