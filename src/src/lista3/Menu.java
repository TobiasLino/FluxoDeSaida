package lista3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) throws IOException {
		List<String> temp2 = new ArrayList<String>();
		ListaUsers banco = new ListaUsers();
		boolean logado = false;
		User temp = null;
		
		try {
		Files.lines(new File("users.txt").toPath()).forEach(temp2::add);
		for(String s:temp2) {
			banco.addUser(s.split(";"));
		}
		}catch(Exception e) {}
		temp2.clear();
		try {
		Files.lines(new File("baixas.txt").toPath()).forEach(temp2::add);
		for(String s:temp2) {
			String[] st = s.split(";");
			banco.findUser(st[0]).addBaixa(st[1],st[2],st[3],st[4],st[5]);
		}}catch(Exception e) {}
		temp2.clear();
		
		Scanner sc = new Scanner(System.in);
		boolean k = true;
		System.out.println("Fintech Ltda. Inc. tm");
		System.out.println("Menu:");
		System.out.println("1-Logar");
		System.out.println("2-Cadastrar Usuário");
		System.out.println("3-Adicionar Baixa");
		System.out.println("4-Imprimir Relatório");
		System.out.println("5-Salvar");
		System.out.println("6-Sair");
		
		
		while(k) {
			switch(sc.nextLine()) {
			case "1":
				System.out.println("Digite o Usuário: ");
				String user = sc.nextLine();
				try {
				temp = banco.findUser(user);
				}catch(Exception e) {}
				logado = banco.login(temp);
				if(logado == false)
					System.out.println("Usuario e/ou senha incorretos!");
				else {System.out.println("Logado com Sucesso!\nPrazer "+temp.getEnt().getNome());}
				break;
			case "2":
				banco.addUser();
				break;
			case "3":
				banco.addBaixa(logado, temp);
				break;
			case "4":
				banco.imprime(logado, temp);				
				break;
			case "5":
				banco.update();
				break;
			case "6":
				k = false;
				break;
			case "":
				System.out.println("Fintech Ltda. Inc. tm");
				System.out.println("Menu:");
				System.out.println("1-Logar");
				System.out.println("2-Cadastrar Usuário");
				System.out.println("3-Adicionar Baixa");
				System.out.println("4-Imprimir Relatório");
				System.out.println("5-Salvar");
				System.out.println("6-Sair");
				break;
			default:
				System.out.println("Entrada não reconhecida!");
				break;
			}
		}
	}
}
