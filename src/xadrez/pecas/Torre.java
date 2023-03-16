package xadrez.pecas;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		// above
		p.informeValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}

		// left
		p.informeValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// right
		p.informeValores(posicao.getLinha(), posicao.getColuna() +1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()]= true;
			p.setColuna(p.getColuna() +1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// below
		p.informeValores(posicao.getLinha() +1, posicao.getColuna());
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()]= true;
			p.setLinha(p.getLinha() +1);
		}
		if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		return matriz;
	}

}
