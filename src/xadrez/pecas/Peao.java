package xadrez.pecas;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);
		
		if(getCor() == Cor.BRANCA) {
			p.informeValores(posicao.getLinha() -1,posicao.getColuna());
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.informeValores(posicao.getLinha() -2,posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() -1,posicao.getColuna());
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temUmaPeca(p2) && getContador() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.informeValores(posicao.getLinha() -1,posicao.getColuna() -1);
			if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.informeValores(posicao.getLinha() -1,posicao.getColuna() +1);
			if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			p.informeValores(posicao.getLinha() +1,posicao.getColuna());
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.informeValores(posicao.getLinha() +2,posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() +1,posicao.getColuna());
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().temUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temUmaPeca(p2) && getContador() == 0) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.informeValores(posicao.getLinha() +1,posicao.getColuna() +1);
			if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
			p.informeValores(posicao.getLinha() +1,posicao.getColuna() -1);
			if (getTabuleiro().existePosicao(p) && temPecaOponente(p)) {
				matriz[p.getLinha()][p.getColuna()] = true;
			}
		}
		return matriz;
	}
}
