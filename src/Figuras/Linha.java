package Figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Linha extends Figura {

	private Figura figuraAnt, figuraProx;

	public Linha(int x, int y, String texto) {
		super(x, y, texto);
	}

	public Figura getFiguraAnt() {
		return figuraAnt;
	}

	public void setFiguraAnt(Figura figuraAnt) {
		this.figuraAnt = figuraAnt;
	}

	public Figura getFiguraProx() {
		return figuraProx;
	}

	public void setFiguraProx(Figura figuraProx) {
		this.figuraProx = figuraProx;
	}

	@Override
	public void desenha(Graphics g) {
		g.drawLine(xIni, yIni, xFim, yFim);
		
		if (this.estaSelecionado()) {
            g.drawOval(xIni,yIni,4,4);
            g.drawOval(xFim,yFim,4,4);       
        }
	}

	@Override
	public boolean intersecta(int x, int y) {
		if(x<this.getX()) return false;
        if(x>(this.getX()+this.getLargura())) return false;
        if(y<this.getY()) return false;
        if(y>(this.getY()+this.getAltura())) return false;
        return true;
	}

}
