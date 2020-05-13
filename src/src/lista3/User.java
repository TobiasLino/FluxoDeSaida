package lista3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
	private String user,pass;
	private Entidade ent;
	
	public User(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public User(String s1, String s2, String s3, String s4, String s5, String s6,String s7, String s8, String s9) {
		this.user=s1;
		this.pass=s2;
		this.ent = new Entidade(s3,s4,s5,s6,s7,s8,s9);
	}

	public String getUsuario() {
		return user;
	}

	public String getPass() {
		return pass;
	}
	
	public void summonEnt() {
		//nome,telefone,email,pessoa,rua,numero,estado
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite o telefone: ");
		String telefone = sc.nextLine();
		System.out.println("Digite o email: ");
		String email = sc.nextLine();
		boolean choiceMade = false;
		String pessoa="";
		while(!choiceMade) {
			System.out.println("Selecione:\n1-Pessoa Física\n2-Pessoa Jurídica");
			switch(sc.nextLine()) {
			case "1":
				pessoa = "fis";
				choiceMade=true;
				break;
			case "2":
				pessoa = "jur";
				choiceMade=true;
				break;
			default:
				System.out.println("Entrada inválida");
			}
		}
		System.out.println("Digite a rua: ");
		String rua = sc.nextLine();
		System.out.println("Digite o numero: ");
		String numero = sc.nextLine();
		System.out.println("Digite o estado ");
		String estado = sc.nextLine();
		ent = new Entidade(nome,telefone,email,pessoa,rua,numero,estado);
	}

	public Entidade getEnt() {
		return ent;
	}

	public void printMensal() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o mês: ");
		int mes = sc.nextInt();
		System.out.println("Escolha o ano: ");
		int ano = sc.nextInt();
		List<Baixas> temp = new ArrayList<Baixas>();
		for(Baixas b: ent.getBaixas()) {
			if(b.getData().getAno()==ano&&b.getData().getMes()==mes) {
				temp.add(b);
			}
		}
		double total=0;
		for(Baixas b: temp) {
			System.out.println("Tipo - "+b.getTipo()+"\nData - "+b.getData().getDia()+"/"+b.getData().getMes()+"/"+b.getData().getAno()+"\nValor - "+b.getValor());
			total+=b.getValor();
		}
		System.out.println("Saldo Total - "+total);
	}

	public void printTudo() {
		double total=0;
		for(Baixas b: ent.getBaixas()) {
			System.out.println("Tipo - "+b.getTipo()+"\nData - "+b.getData().getDia()+"/"+b.getData().getMes()+"/"+b.getData().getAno()+"\nValor - "+b.getValor());
			total+=b.getValor();			
		}
		System.out.println("Saldo Total - "+total);
	}

	public void printSemanal() {
		Scanner sc = new Scanner(System.in);
		int diaAp=0;
		System.out.println("Escolha o dia inicial da semana: ");
		diaAp += sc.nextInt();
		System.out.println("Escolha o mes: ");
		diaAp += sc.nextInt()*30;
		System.out.println("Escolha o ano: ");
		diaAp += sc.nextInt()*365;
		List<Baixas> temp = new ArrayList<Baixas>();
		for(Baixas b: ent.getBaixas()) {
			if(b.getData().getApDia()>=diaAp&&b.getData().getApDia()<=diaAp+7) {
				temp.add(b);
			}
		}
		double total=0;
		for(Baixas b: temp) {
			System.out.println("Tipo - "+b.getTipo()+"\nData - "+b.getData().getDia()+"/"+b.getData().getMes()+"/"+b.getData().getAno()+"\nValor - "+b.getValor());
			total+=b.getValor();
		}
		System.out.println("Saldo Total - "+total);
	}

	public void addBaixa(String string, String string2, String string3, String string4, String string5) {
		// TODO Auto-generated method stub
		
	}
}
