package tree.avl;

public class Main {

	public static void main(String[] args) {
		Avl tree = new Avl();
		
        tree.inserir(9);
        tree.inserir(5);
        tree.inserir(10);
        tree.inserir(0);
        tree.inserir(6);
        tree.inserir(11);
        tree.inserir(-1);
        tree.inserir(1);
        tree.inserir(2);
        
        System.out.println("Valores Inseridos");
        
        tree.preOrder();
        
        tree.remover(10);
        
        System.out.println("\nDelete 10");
        tree.preOrder();
	}

}
