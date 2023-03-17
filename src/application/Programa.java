package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		
		while (true) {
			try {
				IU.limparTela();
				IU.imprimeTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.print("Matriz Origem: ");
				PosicaoXadrez origem = IU.lerPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				IU.limparTela();
				IU.imprimeTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);				
				System.out.println();
				System.out.print("Qual Destino: ");
				PosicaoXadrez destino = IU.lerPosicaoXadrez(sc);
											
			PecaXadrez pecaCapturada = partidaXadrez.efetuarMovimentoXadrez(origem, destino);
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

		
	}
}