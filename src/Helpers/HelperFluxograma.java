package Helpers;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Projeto.Fluxograma;
import UserInterface.Janela;
import UserInterface.PainelFluxograma;

public class HelperFluxograma {

	public static void alimentaListaFluxogramas(Janela janela, ArrayList<Fluxograma> fluxogramas) {
		for (Fluxograma f : fluxogramas) {
			janela.lP.addElement(f.getNomeFluxograma());
		}
		janela.listaFluxogramas.setModel(janela.lP);
	}

	public static void defineTitulo(Janela janela, String titulo) {
		janela.setTitle(janela.getTitle().substring(0, 13) + " - "
				+ titulo);
	}

	public static void adicionaFluxograma(Janela janela, PainelFluxograma painelFluxograma) {
		janela.projetoAtual.adicionaFluxograma(new Fluxograma(JOptionPane
				.showInputDialog(janela, "Digite o nome do fluxograma:")));
		painelFluxograma.setFluxogramaAtual(janela.projetoAtual.getUltimoFluxograma());
		janela.lP.addElement(janela.projetoAtual.getUltimoFluxograma().getNomeFluxograma());
		janela.listaFluxogramas.setModel(janela.lP);
		janela.listaFluxogramas
				.setSelectedIndex(janela.projetoAtual.getFluxogramas().size() - 1);
	}

	public static String getNomeProcesso(Janela janela) {
		return JOptionPane.showInputDialog(janela,
				"Digite o nome do processo:");
	}
	
	public static void habilitaFuncoesFluxograma(JMenuItem itemExportarImagem){
		itemExportarImagem.setEnabled(true);
		//itemVerificaConsistencia.setEnabled(true);
	}
	
	public void desabilitaFuncoesFluxograma(JMenuItem itemExportarImagem){
		itemExportarImagem.setEnabled(false);
		//itemVerificaConsistencia.setEnabled(false);
	}
	
}
