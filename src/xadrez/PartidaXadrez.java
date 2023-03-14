package xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		SetupInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}
	
	private void SetupInicial() {
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BRANCA), new Posicao(2, 1));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.PRETA), new Posicao(0, 4));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.BRANCA), new Posicao(7, 4));
	}
}
