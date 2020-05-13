package lista3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaUsers {
	private List<User> banco = new ArrayList<User>();

	public User findUser(String nome) {
		for(User u:banco) {
			if(u.getUsuario().equals(nome))
				return u;
		}
		return null;
	}

	public boolean login(User u) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a Senha: ");
		String pass = sc.nextLine();
		if(u==null)
			return false;
		if(u.getPass().equals(pass))
			return true;
		return false;
	}

	public void addUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite um Usuário: ");
		String user = sc.nextLine();
		System.out.println("Digite uma Senha: ");
		String pass = sc.nextLine();
		User novo = new User(user, pass);
		novo.summonEnt();
		banco.add(novo);
	}

	public void addBaixa(boolean logado, User temp) {
		if(!logado) {
			System.out.println("Por favor realize o login!");
			return;
		}
		temp.getEnt().addBaixa();
		
	}

	public void imprime(boolean logado, User u) {
		if(!logado) {
			System.out.println("Por favor realize o login!");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o tipo de Relatório\n1-Mensal\n2-Semanal\n3-Vitalício");
		boolean k = false;
		while(!k) {
			switch(sc.nextLine()) {
			case "1":
				u.printMensal();
				k=true;
				break;
			case "2":
				u.printSemanal();
				k=true;
				break;
			case "3":
				u.printTudo();
				k=true;
				break;
			default:
				System.out.println("Entrada Inválida");
			}
		}
	}

	public void update() throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter reset = new BufferedWriter(new FileWriter("users.txt"));
		BufferedWriter resetti = new BufferedWriter(new FileWriter("baixas.txt"));
		reset.write("");
		resetti.write("");
		reset.close();
		resetti.close();
		BufferedWriter writerU = new BufferedWriter(new FileWriter("users.txt", true));
		BufferedWriter writerB = new BufferedWriter(new FileWriter("baixas.txt", true));
		for(User u:banco) {		//	nome,telefone,email,pessoa,rua,numero,estado;
			writerU.append(u.getUsuario()+";"+u.getPass()+";"+u.getEnt().getNome()+";"+u.getEnt().getTelefone()+";"+u.getEnt().getEmail()+";"+u.getEnt().getPessoa()+";"+u.getEnt().getRua()+";"+u.getEnt().getNumero()+";"+u.getEnt().getEstado()+"\n");
			for(Baixas b:u.getEnt().getBaixas()) { //valor tipo dia mes ano
				writerB.append(u.getUsuario()+";"+b.getValor()+";"+b.getTipo()+";"+b.getData().getDia()+";"+b.getData().getMes()+";"+b.getData().getAno()+"\n");
			}
		}
		writerB.close();
		writerU.close();
	}

	public void addUser(String[] s) {
		banco.add(new User(s[0], s[1], s[2], s[3],s[4],s[5],s[6],s[7], s[8]));
	}
}
