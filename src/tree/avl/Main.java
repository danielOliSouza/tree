package tree.avl;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Avl tree = new Avl();
		
		int command = 0;
		Scanner in = new Scanner(System.in);
		
		System.out.println("---- Bem Vindo ----");
		System.out.println("Tecle 0 para encerrar");
		System.out.println("Tecle 1 para Inserir");
		System.out.println("Tecle 2 para listar em Pré Ordem");
		System.out.println("Tecle 5 para Remover");
		
		do {
			System.out.print("$: ");
			command = in.nextInt();

			switch (command) {
				case 0: break;
				case 1:	inserirArvoreAvl(in, tree); System.out.println("\n"); break;
				case 2: System.out.println("\nPré Ordem"); tree.preOrder();; ; break;
				case 5: removerArvoreAvl(in, tree); break;
				default: System.out.println("Comando não especificado \n"); break;
			}

		}while(command != 0);
	}

	private static void removerArvoreAvl(Scanner in, Avl tree) {
		System.out.print("\nValor: ");

		tree.remover(in.nextInt());
		
		System.out.print("\nRemover outro valor? (s/n): ");
		if(in.next().toUpperCase().equals("S"))
			removerArvoreAvl(in, tree);
	}

	private static void inserirArvoreAvl(Scanner in, Avl tree) {
		System.out.print("\nValor: ");

		tree.inserir(in.nextInt());
		
		System.out.print("\nInserir outro valor? (s/n): ");
		if(in.next().toUpperCase().equals("S"))
			inserirArvoreAvl(in, tree);
	}
}
