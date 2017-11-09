package tree.binaria;

public class Binaria {
	private static No raiz;
	
	public void inserir(int value) {
		inserir(raiz, value);
	}
	
	private void inserir(No node, int value) {
		if(raiz == null) {
			raiz = new No(value);
			System.out.println("RAIZ");
		}
		else {
			//Todos os elementos na subárvore esquerda de um determinado nó n são menores que "n";
			if(node.getValor() > value) {
				if(node.getEsquerda() == null) {
					node.setEsquerda(new No(value));
					System.out.printf("Inserido %d a esquerda de %d", value, node.getValor().intValue());
				}
				else {
					inserir(node.getEsquerda() ,value);
				}
			}
			//Todos os elementos na subárvore direita de um determinado nó n são maiores ou iguais a "n".
			else {
				if(node.getDireita() == null) {
					node.setDireita(new No(value));
					System.out.printf("Inserido %d a direita de %d", value, node.getValor().intValue());
				}
				else {
					inserir(node.getDireita(), value);
				}
			}
		}
	}
	
	public void listValorArvorePreOrdem() {
		listValorArvorePreOrdem(raiz);
	}
	
	//Pré Ordem 
	private void listValorArvorePreOrdem(No node) {
		if(node == null)
			return;
		
		System.out.println(node.getValor());
		listValorArvorePreOrdem(node.getEsquerda());
		listValorArvorePreOrdem(node.getDireita());
	}

	public void listValorArvoreEmOrdem() {
		listValorArvoreEmOrdem(raiz);
	}
	
	//Em Ordem 
	private void listValorArvoreEmOrdem(No node) {
		if(node == null)
			return;
		
		listValorArvorePreOrdem(node.getEsquerda());
		System.out.println(node.getValor());
		listValorArvorePreOrdem(node.getDireita());
	}
	
	public void listValorArvorePosOrdem() {
		listValorArvorePosOrdem(raiz);
	}
	
	//Em Ordem 
	private void listValorArvorePosOrdem(No node) {
		if(node == null)
			return;
		
		listValorArvorePreOrdem(node.getEsquerda());
		listValorArvorePreOrdem(node.getDireita());
		System.out.println(node.getValor());
	}
	
	public static No getRaiz() {
		return raiz;
	}
}
