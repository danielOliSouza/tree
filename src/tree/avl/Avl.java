package tree.avl;

public class Avl {
	private static No raiz;
	
	public void inserir(int value) {
		inserir(raiz, value);
	}
	
	private void inserir(No node, int value) {
		if(raiz == null) {
			raiz = new No(value);
		}
		else {
			//Todos os elementos na subárvore esquerda de um determinado nó n são menores que "n";
			if(node.getValor() > value) {
				if(node.getEsquerda() == null) {
					node.setEsquerda(new No(value));
					node.getEsquerda().setPai(node);
					verificarBalanceamento(node);
				}
				else {
					inserir(node.getEsquerda() ,value);
				}
			}
			//Todos os elementos na subárvore direita de um determinado nó n são maiores ou iguais a "n".
			else {
				if(node.getDireita() == null) {
					node.setDireita(new No(value));
					node.getDireita().setPai(node);
					verificarBalanceamento(node);
				}
				else {
					inserir(node.getDireita(), value);
				}
			}
		}
	}
	
	public void remover(int value) {
		remover(raiz, value);
	}
	private void remover(No node, int value) {
		if (node == null) {
			return;
		} 
		else {
			if (node.getValor() > value) {
				remover(node.getEsquerda(), value);

			} else if (node.getValor() < value) {
				remover(node.getDireita(), value);

			} else if (node.getValor() == value) {
				removerNoEncontrado(node);
			}
		}
	}
	
	private void removerNoEncontrado(No node) {
		No del, p;
		
		if(node.getEsquerda() == null && node.getDireita() == null) {
			if(node.getPai() == null) {
				raiz = null;
				node = null;
				return;
			}
			
			del = node;
		}
		else {
			del = sucessor(node);
			node.setValor(del.getValor());
		}
		
		if(del.getEsquerda() != null)
			p = del.getEsquerda();
		else
			p = del.getDireita();
		
		if(p != null)
			p.setPai(del.getPai());
		
		if(del.getPai() == null)
			raiz = p;
		else {
			if(del == del.getPai().getEsquerda())
				del.getPai().setEsquerda(p);
			else
				del.getPai().setDireita(p);
			
			verificarBalanceamento(del.getPai());
		}
		
		del = null;
	}

	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}
	
	private void verificarBalanceamento(No node) {
		node.setBalanceamento(altura(node.getDireita()) - altura(node.getEsquerda()));
		int balanceamento = node.getBalanceamento();
		
		//Rotação a Direita
		if (balanceamento == -2) {

			if (altura(node.getEsquerda().getEsquerda()) >= altura(node.getEsquerda().getDireita())) {
				node = rotacaoDireita(node);

			} else {
				node = duplaRotacaoEsquerdaDireita(node);
			}
		
		} else if (balanceamento == 2) { //Rotação a Esquerda

			if (altura(node.getDireita().getDireita()) >= altura(node.getDireita().getEsquerda())) {
				node = rotacaoEsquerda(node);

			} else {
				node = duplaRotacaoDireitaEsquerda(node);
			}
		}

		if (node.getPai() != null) {
			verificarBalanceamento(node.getPai());
		} else {
			raiz = node;
		}
	}
	
	private int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}
	
	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		inicial.setBalanceamento(altura(inicial.getDireita()) - altura(inicial.getEsquerda()));
		direita.setBalanceamento(altura(direita.getDireita()) - altura(direita.getEsquerda()));

		return direita;
	}

	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		inicial.setBalanceamento(altura(inicial.getDireita()) - altura(inicial.getEsquerda()));
		esquerda.setBalanceamento(altura(esquerda.getDireita()) - altura(esquerda.getEsquerda()));

		return esquerda;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
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
