package xadrez;

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
	
	private void colocandoNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	private void SetupInicial() {
		colocandoNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));
		
		colocandoNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
		
	}
}
