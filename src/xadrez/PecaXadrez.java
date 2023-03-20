package xadrez;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {
	
	private Cor cor;
	private int contador;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContador() {
		return contador;		
	}
	
	public void aumentarContador() {
		contador++;
	}
	
	public void diminuirContador() {
		contador--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}
	
	protected boolean temPecaOponente(Posicao posicao) {
			PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
			return p != null && p.getCor() != cor;
	}
}