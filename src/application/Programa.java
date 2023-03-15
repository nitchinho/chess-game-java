package application;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		PartidaXadrez partidaXadrez = new PartidaXadrez();

		while (true) {
			IU.imprimeTabuleiro(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Matriz Inicial: ");
			PosicaoXadrez inicial = IU.lerPosicaoXadrez(sc);
			System.out.println();
			System.out.print("Qual Destino: ");
			PosicaoXadrez destino = IU.lerPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partidaXadrez.efetuarMovimentoXadrez(inicial, destino);

		}
	}
}