package xadrez.pecas;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// above-left
		p.informeValores(posicao.getLinha() - 1, posicao.getColuna() -1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
			p.setColuna(p.getColuna() -1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// above-right
		p.informeValores(posicao.getLinha() -1, posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// below-right
		p.informeValores(posicao.getLinha() +1, posicao.getColuna() +1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()]= true;
			p.setColuna(p.getColuna() +1);
			p.setLinha(p.getLinha() +1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// below-left
		p.informeValores(posicao.getLinha() +1, posicao.getColuna() -1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()]= true;
			p.setLinha(p.getLinha() +1);
			p.setColuna(p.getColuna() -1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		return matriz;
	}	
}