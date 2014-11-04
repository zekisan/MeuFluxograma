package UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import Figuras.Figura;
import Figuras.Linha;
import Projeto.Fluxograma;
import Projeto.Projeto;

@SuppressWarnings("serial")
public class PainelFluxograma extends JPanel {

	private ArrayList<Figura> figuras;
	private Fluxograma fluxogramaAtual;

	public PainelFluxograma() {
		fluxogramaAtual = null;
	}

	public ArrayList<Figura> getFiguras() {
		return fluxogramaAtual.getFiguras();
	}

	public void setFiguras(ArrayList<Figura> figuras) {
		this.figuras = figuras;
	}

	public Fluxograma getFluxogramaAtual() {
		return fluxogramaAtual;
	}

	public void setFluxogramaAtual(Fluxograma fluxogramaAtual) {
		if (fluxogramaAtual == null) {
			fluxogramaAtual = new Fluxograma();
		}
		this.fluxogramaAtual = fluxogramaAtual;
		this.repaint();
	}

	public void limpaFiguras() {
		if (fluxogramaAtual != null) {
			fluxogramaAtual.getFiguras().clear();
		}
		this.repaint();
	}

	private void limparTela(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
	}

	public Figura getUltimaFigura() {
		return fluxogramaAtual.getFiguras().get(
				fluxogramaAtual.getFiguras().size() - 1);
	}

	public void removerFiguras() {
		figuras.clear();
	}

	public void verificaSelecao(int x, int y) {
		deselecionarFiguras();
		for (Figura f : fluxogramaAtual.getFiguras()) {
			if (f.intersecta(x, y)) {
				deselecionarFiguras();
				f.selecionar();
			}
		}

	}

	public Figura getSelecionado() {
		for (Figura f : fluxogramaAtual.getFiguras()) {
			if (f.estaSelecionado()) {
				return f;
			}
		}
		return null;
	}

	public void deselecionarFiguras() {
		for (Figura f : fluxogramaAtual.getFiguras()) {
			f.deselecionar();
		}
		repaint();
	}

	public void removeFigura() {
		if (this.getSelecionado() != null) {
			fluxogramaAtual.getFiguras()
					.remove(fluxogramaAtual.getFiguras().indexOf(
							this.getSelecionado()) - 1);
			fluxogramaAtual.getFiguras().remove(this.getSelecionado());
			this.repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		limparTela(g);

		if (fluxogramaAtual != null) {
			// Desenha o nome do fluxograma atual
			g.drawString("Fluxograma: " + fluxogramaAtual.getNomeFluxograma(),
					8, 20);
			if (fluxogramaAtual.getFiguras() != null) {
				for (Figura f : fluxogramaAtual.getFiguras()) {
					f.desenha(g);
				}
			}
		}
	}
}
