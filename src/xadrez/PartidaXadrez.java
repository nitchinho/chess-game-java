package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogotabuleiro.Peca;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Dama;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequemate;
		
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		SetupInicial();
	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequemate;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}

	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}

	public PecaXadrez efetuarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMovimento(origem, destino);
		
		if (testeXeque(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Voce nao pode se colocar em Cheque, burro...");
		}
		
		xeque = (testeXeque(oponente(jogadorAtual))) ? true : false;
			
		if (testeXequeMate(oponente(jogadorAtual))) {
			xequemate = true;
		}
		else {
				trocandoTurno();
		}
		return (PecaXadrez) pecaCapturada;
	}

	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(origem);
		p.aumentarContador();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		// Movimento Especial Roque Pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() +2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() +1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, destinoT);
			torre.aumentarContador();
		}
		
		// Movimento Especial Roque Grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() -2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() -1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.colocarPeca(torre, destinoT);
			torre.aumentarContador();
		}
		return pecaCapturada;
	}
	
	private void desfazerMovimento (Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez)tabuleiro.removerPeca(destino);
		p.diminuirContador();
		tabuleiro.colocarPeca(p, origem);
				
		if (pecaCapturada !=null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
		// Movimento Especial Roque Pequeno
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() +2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() +1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.diminuirContador();
		}
		
		// Movimento Especial Roque Grande
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() -2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() -1);
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.colocarPeca(torre, origemT);
			torre.diminuirContador();
		}
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Nao ha peca na posicao origem");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida nao eh sua... Ladrao safado");
		}
		
		if (!tabuleiro.peca(posicao).temMovimentoPossivel()) {
			throw new XadrezException("Nao tem movimentos possiveis");
		}
	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentosPossiveis(destino)) {
			throw new XadrezException("A peca escolhida de origem nao pode se mover para o destino escolhido");
		}
	}

	private void trocandoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe nenhum Rei " + cor +" no tabuleiro. Tah bugado!");
	}
	
	
	private boolean testeXeque (Cor cor) {
		Posicao posicaodoRei = rei(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasdoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasdoOponente) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaodoRei.getLinha()][posicaodoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeXequeMate (Cor cor) {
		if (!testeXeque(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = fazerMovimento(origem, destino);
						boolean testeXeque = testeXeque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeXeque) {
							return false;
						} 
					}
				}
			}
		}
		return true;
	}

	private void colocandoNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}

	private void SetupInicial() {
		colocandoNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('d', 1, new Dama(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
		colocandoNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA));
		colocandoNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA));

		colocandoNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('d', 8, new Dama(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA, this));
		colocandoNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA));
		colocandoNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA));
	}
}