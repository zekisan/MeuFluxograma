package Figuras;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class Retangulo extends Figura{

    public Retangulo(int x, int y, String texto) {
        super(x, y, texto);
    }
    
    @Override
    public void desenha(Graphics g)
    {
        int x = this.getX();
        int y = this.getY();
        int larg = this.getLargura();
        int alt = this.getAltura();
        g.drawRect(x, y, larg, alt);
        
        FontMetrics fm = g.getFontMetrics();
        int xTexto = (xIni <= xFim) ? (((larg - fm.stringWidth(texto)) / 2) + xIni) : (((larg - fm.stringWidth(texto)) / 2) + xFim);
        int yTexto = (fm.getAscent() + (alt - (fm.getAscent() + fm.getDescent())) / 2) + yIni;
        g.drawString(texto, xTexto, yTexto);
        
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