package tree.binaria;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int command = 0;
		Scanner in = new Scanner(System.in);
		Binaria binaria = new Binaria();
		
		System.out.println("---- Bem Vindo ----");
		System.out.println("Tecle 0 para encerrar");
		System.out.println("Tecle 1 para Inserir");
		System.out.println("Tecle 2 para listar em Pré Ordem");
		System.out.println("Tecle 3 para listar em Em  Ordem");
		System.out.println("Tecle 4 para listar em Pós Ordem");
		System.out.println("Tecle 5 para Remover");
		
		do {
			System.out.print("$: ");
			command = in.nextInt();

			switch (command) {
				case 0: break;
				case 1:	inserirArvoreBinaria(in, binaria); System.out.println("\n"); break;
				case 2: System.out.println("\nPré Ordem"); binaria.listValorArvorePreOrdem(); ; break;
				case 3: System.out.println("\nEm  Ordem"); binaria.listValorArvoreEmOrdem(); break;
				case 4: System.out.println("\nPós Ordem"); binaria.listValorArvorePosOrdem(); break;
				case 5: removerArvoreBinaria(in, binaria); break;
				default: System.out.println("Comando não especificado \n"); break;
			}

		}while(command != 0);
	}


	private static void removerArvoreBinaria(Scanner in, Binaria binaria) {
		System.out.print("\nValor: ");

		binaria.remover(in.nextInt());
		
		System.out.print("\nRemover outro valor? (s/n): ");
		if(in.next().toUpperCase().equals("S"))
			removerArvoreBinaria(in, binaria);
	}


	private static void inserirArvoreBinaria(Scanner in, Binaria binaria) {
		System.out.print("\nValor: ");

		binaria.inserir(in.nextInt());
		
		System.out.print("\nInserir outro valor? (s/n): ");
		if(in.next().toUpperCase().equals("S"))
			inserirArvoreBinaria(in, binaria);
	}

}
