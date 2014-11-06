package Helpers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Projeto.Projeto;
import UserInterface.Janela;
import UserInterface.PainelFluxograma;

public class ControladorArquivo {
	
	private final int SALVAR = 0;
	private final static int SALVARCOMO = 1;

	public static void exportaImagem(PainelFluxograma painelFluxograma,
			Janela janela) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fileChooser.showSaveDialog(janela);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			file.renameTo(new File(file.getName() + ".jpg"));
			BufferedImage im = new BufferedImage(painelFluxograma.getWidth(),
					painelFluxograma.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = im.createGraphics();
			painelFluxograma.paint(g2);
			g2.dispose();
			try {
				ImageIO.write(im, "jpg", file);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(janela,
						"Erro ao exportar imagem!");
			}
		}
	}

	public static void carregaDados(File file, Janela janela) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			janela.projetoAtual = (Projeto) ois.readObject();
			janela.alimentaListaFluxogramas(janela.projetoAtual.getFluxogramas());
			janela.defineTitulo(janela.projetoAtual.getNomeProjeto());
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void salvarDados(File file, int operacao, Projeto projetoAtual)
			throws FileNotFoundException, IOException {
		try {
			if (operacao == SALVARCOMO) {
				projetoAtual.setArquivoProjeto(file);
			}
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.reset();
			oos.writeObject(projetoAtual);
			oos.close();
			fos.close();
			projetoAtual.setSalvo(true);
		} catch (Exception e) {

		}
	}
}