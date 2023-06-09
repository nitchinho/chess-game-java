package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();
		
		while (!partidaXadrez.getXequeMate()) {
			try {
				IU.limparTela();
				IU.imprimePartida(partidaXadrez, capturada);
				System.out.println();
				System.out.print("Posicao de Origem: ");
				PosicaoXadrez origem = IU.lerPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				IU.limparTela();
				IU.imprimeTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);				
				System.out.println();
				System.out.print("Posicao de Destino: ");
				PosicaoXadrez destino = IU.lerPosicaoXadrez(sc);
											
			PecaXadrez pecaCapturada = partidaXadrez.efetuarMovimentoXadrez(origem, destino);
			
			if (pecaCapturada != null) {
				capturada.add(pecaCapturada);
			}
			
			if (partidaXadrez.getPromocao() != null) {
				System.out.print("Informe a peca promovida (B/C/T/D): ");
				String type = sc.nextLine().toUpperCase();
				while(!type.equals("B") && !type.equals("C") && !type.equals("D") && !type.equals("T")) {
					System.out.print("Peca invalida! Informe a peca promovida (B/C/T/D): ");
					type = sc.nextLine().toUpperCase();
				}
				partidaXadrez.trocandoPecaPromovida(type);
			}
		}
		catch (XadrezException e) {
			System.out.println(e.getMessage());
			sc.nextLine();
		}
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			sc.nextLine();
			}
		}
		IU.limparTela();
		IU.imprimePartida(partidaXadrez, capturada);
	}
}