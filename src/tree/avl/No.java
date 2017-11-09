package tree.avl;

public class No {
	private Integer valor = null;
	private No esquerda = null;
	private No direita = null;
	private int balanceamento;
	private No pai = null;
	
	public No(int valor) {
		this.valor = valor;
	}
	
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public No getEsquerda() {
		return esquerda;
	}
	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}
	public No getDireita() {
		return direita;
	}
	public void setDireita(No direita) {
		this.direita = direita;
	}
	public int getBalanceamento() {
		return balanceamento;
	}
	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}
	public void setPai(No pai) {
		this.pai = pai;
	}
	public No getPai() {
		return pai;
	}
	
}
