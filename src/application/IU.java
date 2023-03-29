package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class IU {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
			String s = sc.nextLine().toLowerCase();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro lendo a posicao. Valores validos sao de *a1* ate *h8*.");
		}
	}

	public static void imprimePartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturada) {
		imprimeTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		imprimePecaCapturada(capturada);
		System.out.println("------------------");
		System.out.println("Turno : " + partidaXadrez.getTurno());
		if (!partidaXadrez.getXequeMate()) {
			System.out.println("Aguardando Jogador : " + partidaXadrez.getJogadorAtual());
			if (partidaXadrez.getXeque()) {
				System.out.println("Voce esta em cheque! Tah se lascando, hein...");
			}
		}
		else {
			System.out.println("CHEQUE-MATE !!!");
			System.out.println("VENCEDOR: " + partidaXadrez.getJogadorAtual());
		}
	}

	public static void imprimeTabuleiro(PecaXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimePeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}

	public static void imprimeTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimePeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}

	private static void imprimePeca(PecaXadrez peca, boolean planodefundo) {
		if (planodefundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.BRANCA) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_CYAN + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	private static void imprimePecaCapturada(List<PecaXadrez> capturada) {
		List<PecaXadrez> branca = capturada.stream().filter(x -> x.getCor() == Cor.BRANCA).collect(Collectors.toList());
		List<PecaXadrez> preta = capturada.stream().filter(x -> x.getCor() == Cor.PRETA).collect(Collectors.toList());
		System.out.println();
		System.out.println("PECAS CAPTURADAS: ");
		System.out.print("BRANCAS: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("PRETAS: ");
		System.out.print(ANSI_CYAN);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.println(ANSI_RESET);
	}

}