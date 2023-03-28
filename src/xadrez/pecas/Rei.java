package xadrez.pecas;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.PecaXadrez;
import xadrez.Cor;
import xadrez.PartidaXadrez;

	public class Rei extends PecaXadrez {
		
		private PartidaXadrez partidaXadrez;
		
		public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
		
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean testeTorreRoque(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContador() == 0;
		}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);		
		return p == null || p.getCor() !=getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// above
		p.informeValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// below
		p.informeValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// left
		p.informeValores(posicao.getLinha(), posicao.getColuna() -1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// right
		p.informeValores(posicao.getLinha(), posicao.getColuna() +1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// nw
		p.informeValores(posicao.getLinha() -1, posicao.getColuna() -1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// ne
		p.informeValores(posicao.getLinha() -1, posicao.getColuna() +1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// sw
		p.informeValores(posicao.getLinha() +1, posicao.getColuna() -1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// se
		p.informeValores(posicao.getLinha() +1, posicao.getColuna() +1);
		if (getTabuleiro().existePosicao(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		// movimento especial roque
		if (getContador() == 0 && !partidaXadrez.getXeque()) {
			// Roque Pequeno
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() +3);
			if (testeTorreRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() +2);
				if (getTabuleiro().peca(p1)== null && getTabuleiro().peca(p2)== null) {
					matriz[posicao.getLinha()][posicao.getColuna() +2] = true;
				}
			}
			// Roque Grande
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
			if (testeTorreRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() -2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() -3);
				if (getTabuleiro().peca(p1)== null && getTabuleiro().peca(p2)== null && getTabuleiro().peca(p3)== null) {
					matriz[posicao.getLinha()][posicao.getColuna() -2] = true;
				}
			}
		}
		
		return matriz;
	}
}