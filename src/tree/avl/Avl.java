package tree.avl;

public class Avl {
	private static No raiz;

	public void remover(int value) {
		raiz = deleteNode(raiz, value);
	}
	
	private No deleteNode(No node, int value) {
		if (node == null)
            return node;
		
        if (value < node.getValor())
            node.setEsquerda(deleteNode(node.getEsquerda(), value));
        else if (value > node.getValor())
            node.setDireita(deleteNode(node.getDireita(), value));
        else {
        	if ((node.getEsquerda() == null) || (node.getDireita() == null)) {
        		 No temp = null;
                 if (temp == node.getEsquerda())
                     temp = node.getDireita();
                 else
                     temp = node.getEsquerda();
                 
                 //Nó sem filhos
                 if (temp == null) {
                     temp = node;
                     node = null;
                 }
                 else   //Nó com somente 1 filho
                     node = temp;
        	}
        	else {
        		//Pega o menor valor a Direita
                No temp = minValueNode(node.getDireita());
 
                //Substitui o valor pelo o menor valor a direita
                node.setValor(temp.getValor());
 
                node.setDireita(deleteNode(node.getDireita(), temp.getValor()));
        	}
        }
        
        //Caso não tenha filhos
        if (node == null)
            return node;
        
        //Atualizar Altura
        node.setAltura(max(altura(node.getEsquerda()), altura(node.getDireita())) + 1);
        
        //Rebalancear a arvore
        int balance = getBalance(node);
        
        //Rotação Simples a Direita
        if (balance > 1 && getBalance(node.getEsquerda()) >= 0)
            return rightRotate(node);
        
        //Rotação Dupla Esquerda Direita 
        if (balance > 1 && getBalance(node.getEsquerda()) < 0){
            node.setEsquerda(leftRotate(node.getEsquerda()));
            return rightRotate(node);
        }
        
        // Rotação Simples a Esquerda
        if (balance < -1 && getBalance(node.getDireita()) <= 0)
            return leftRotate(node);
        
        // Rotação Dupla Direita Esquerda
        if (balance < -1 && getBalance(node.getDireita()) > 0)
        {
            node.setDireita(rightRotate(node.getDireita()));
            return leftRotate(node);
        }
 
        return node;
	}

	private No minValueNode(No node) {
		if(node.getEsquerda() != null)
			return minValueNode(node.getEsquerda());
		else
			return node;
	}

	public void inserir(int value) {
		raiz = inserir(raiz, value);
	}
	private No inserir(No node, int value) {
		if(node == null)
			return new No(value);

		if (value < node.getValor())
			node.setEsquerda(inserir(node.getEsquerda(), value));
		else 
			node.setDireita(inserir(node.getDireita(), value));


		node.setAltura(1 + max(altura(node.getEsquerda()), altura(node.getDireita())));
		
		int balance = getBalance(node);
		
		//Rotação Simples a Direita
        if (balance > 1 && value < node.getEsquerda().getValor())
            return rightRotate(node);
        
        //Rotação Simples a Esquerda
        if (balance < -1 && value > node.getDireita().getValor())
            return leftRotate(node);
        
        // Rotação Dupla Esquerda Direita 
        if (balance > 1 && value > node.getEsquerda().getValor()) {
            node.setEsquerda(leftRotate(node.getEsquerda()));
            return rightRotate(node);
        }
 
        // Rotação Dupla Direita Esquerda 
        if (balance < -1 && value < node.getDireita().getValor()) {
            node.setDireita(rightRotate(node.getDireita()));
            return leftRotate(node);
        }
        
        return node;
	}
	
    private No leftRotate(No node) {
    	No noDireita = node.getDireita();
        No noEsquerdaDaDireita = noDireita.getEsquerda();
 
        //Rotação
        noDireita.setEsquerda(node);
        node.setDireita(noEsquerdaDaDireita);
 
        node.setAltura(max(altura(node.getEsquerda()), altura(node.getDireita())) + 1);
        noDireita.setAltura(max(altura(noDireita.getEsquerda()), altura(noDireita.getDireita())) + 1);
 
        return noDireita;
	}

	private No rightRotate(No node) {
    	
        No noEsquerda = node.getEsquerda();
        No noDireitaEsquerda = noEsquerda.getDireita();
 
        //Rotação
        noEsquerda.setDireita(node);
        node.setEsquerda(noDireitaEsquerda);
 
        node.setAltura(max(altura(node.getEsquerda()), altura(node.getDireita())) + 1);
        noEsquerda.setAltura(max(altura(noEsquerda.getEsquerda()), altura(noEsquerda.getDireita())) + 1);
 
        return noEsquerda;
	}

	private int getBalance(No node) {
        if (node == null)
            return 0;
        return altura(node.getEsquerda()) - altura(node.getDireita());
	}

	int max(int a, int b){
        return (a > b) ? a : b;
    }
    int altura(No N) {
        if (N == null)
             return 0;
         return N.getAltura();
    }
    
    public void preOrder() {
    	preOrder(raiz);
    }
    private void preOrder(No node)
    {
        if (node != null)
        {
            System.out.print(node.getValor() + " ");
            preOrder(node.getEsquerda());
            preOrder(node.getDireita());
        }
    }
}
