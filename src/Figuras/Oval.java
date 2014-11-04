package Figuras;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class Oval extends Figura{

	public Oval(int x, int y, String texto) {
        super(x, y, texto);
    }
	
	@Override
    public void desenha(Graphics g)
    {
        int x = (xIni <= xFim) ?xIni :xFim;
        int y = (yIni <= yFim) ?yIni :yFim;
        int larg = Math.abs (xIni-xFim);
        int alt = Math.abs (yIni-yFim);
        g.drawOval(x, y, larg, alt);
        
        FontMetrics fm = g.getFontMetrics();
        int xTexto = (larg - fm.stringWidth(texto)) / 2;
        int yTexto = (fm.getAscent() + (alt - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(texto, xIni + xTexto, yIni + yTexto);
        
        if (this.estaSelecionado()) {
            g.drawOval(x-2,y-2,4,4);
            g.drawOval(x-2,y+alt-2,4,4);
            g.drawOval(x+larg-2,y-2,4,4);
            g.drawOval(x+larg-2,y+alt-2,4,4);        
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
