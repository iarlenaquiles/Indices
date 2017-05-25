package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.BrbrasilDAO;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		// System.out.println(ConnectionFactory.getConnection());

		BrbrasilDAO dao;
		Runtime run = Runtime.getRuntime();

		run.exec(
				"sudo su \r\n bombaufc123 \r\n echo 3 > /proc/sys/vm/drop_caches \r\n sysctl -w vm.drop_caches=3 \r\n service postgresql restart");
		System.out.println("----------MENU----------");
		System.out.println("0 - Com Índice");
		System.out.println("1 - Sem Índice");

		Scanner sc = new Scanner(System.in);

		int digitado = sc.nextInt();

		System.out.println("Rodando a consulta ...");

		switch (digitado) {
		case 0:
			dao = new BrbrasilDAO(true);
			System.out.println("O tempo de execucao de consulta com indice: " + dao.executar() + " millisegundos");
			System.out.println(dao.getPlano());
			break;

		case 1:
			dao = new BrbrasilDAO(false);
			System.out.println("O tempo de execucao de consulta sem indice: " + dao.executar() + " millisegundos");
			System.out.println(dao.getPlano());
			break;

		default:
			System.out.println("Opcao invalida, encerrando a execucao");
			System.exit(1);
		}
	}

}
