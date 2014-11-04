package Figuras;

import java.awt.Graphics;

public class Seta extends Figura{

	public Seta(int x, int y, String texto) {
		super(x, y, texto);
	}

	@Override
	public void desenha(Graphics g) {
		
	}

	@Override
	public boolean intersecta(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
