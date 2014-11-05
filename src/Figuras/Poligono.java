package Figuras;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class Poligono extends Figura {

	public Poligono(int x, int y, String texto) {
		super(x, y, texto);
	}

	@Override
	public void desenha(Graphics g) {
		int larg = Math.abs(xIni - xFim);
		int alt = Math.abs(yIni - yFim);

		int xpoint[] = new int[4];
		xpoint[0] = (xIni <= xFim) ? xIni : xFim;
		xpoint[1] = (xIni <= xFim) ? (xIni + Math.abs(larg / 2)) : (xFim + Math.abs(larg / 2));
		xpoint[2] = (xIni <= xFim) ? (xIni + larg) : (xFim + larg);
		xpoint[3] = (xIni <= xFim) ? (xIni + Math.abs(larg / 2)) : (xFim + Math.abs(larg / 2));

		int ypoint[] = new int[4];
		ypoint[0] = yIni + Math.abs(alt / 2);
		ypoint[1] = yIni;
		ypoint[2] = yIni + Math.abs(alt / 2);
		ypoint[3] = yIni + alt;
		
		g.drawPolygon(xpoint, ypoint, 4);
		
		FontMetrics fm = g.getFontMetrics();
        int xTexto = (xIni <= xFim) ? (((larg - fm.stringWidth(texto)) / 2) + xIni) : (((larg - fm.stringWidth(texto)) / 2) + xFim);
        int yTexto = (fm.getAscent() + (alt - (fm.getAscent() + fm.getDescent())) / 2) + yIni;
        g.drawString(texto, xTexto, yTexto);
		
		if (this.estaSelecionado()) {
            g.drawOval(xpoint[0],ypoint[0],4,4);
            g.drawOval(xpoint[1],ypoint[1],4,4);
            g.drawOval(xpoint[2],ypoint[2],4,4);
            g.drawOval(xpoint[3],ypoint[3],4,4);        
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
