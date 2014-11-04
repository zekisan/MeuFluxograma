package Figuras;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Figura implements Serializable{
	
	protected int xIni, yIni, xFim, yFim;
	protected String texto;
	protected boolean selecionado;
	protected Linha linhaAnt,linhaProx;
	
	public Figura(int x, int y, String texto) {
        this.xIni = x;
        this.xFim = x;
        this.yIni = y;
        this.yFim = y;
        this.texto = texto;
    }

    public int getxIni() {
        return xIni;
    }

    public void setxIni(int xIni) {
        this.xIni = xIni;
    }

    public int getyIni() {
        return yIni;
    }

    public void setyIni(int yIni) {
        this.yIni = yIni;
    }

    public int getxFim() {
        return xFim;
    }

    public void setxFim(int xFim) {
        this.xFim = xFim;
    }

    public int getyFim() {
        return yFim;
    }

    public void setyFim(int yFim) {
        this.yFim = yFim;
    }
    
    public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setPontoInicial(int x, int y) {
        this.xIni = x;
        this.yIni = y;
    }

    public void setPontoFinal(int x, int y) {
        this.xFim = x;
        this.yFim = y;
    }
    
    protected int getX() {
        return ((xIni <= xFim) ? xIni : xFim);
    }

    protected int getY() {
        return ((yIni <= yFim) ? yIni : yFim);
    }

    protected int getLargura() {
        return (Math.abs(xIni - xFim));
    }

    protected int getAltura() {
        return (Math.abs(yIni - yFim));
    }
    
    public Linha getLinhaAnt() {
		return linhaAnt;
	}

	public void setLinhaAnt(Linha linhaAnt) {
		this.linhaAnt = linhaAnt;
	}

	public Linha getLinhaProx() {
		return linhaProx;
	}

	public void setLinhaProx(Linha linhaProx) {
		this.linhaProx = linhaProx;
	}

	public boolean estaSelecionado() {
        return (selecionado);
    }

    public void selecionar() {
        selecionado = true;
    }

    public void deselecionar() {
        selecionado = false;
    }

    public void moveTo(int posX, int posY)
    {      
        int largura = this.getLargura();
        int altura = this.getAltura();
        this.xIni = posX - largura/2;
        this.xFim = posX + largura/2;
        this.yIni = posY - altura/2;
        this.yFim = posY + altura/2;
    }  
    
	public abstract void desenha(Graphics g);
	public abstract boolean intersecta(int x, int y);
}