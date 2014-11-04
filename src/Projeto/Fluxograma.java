package Projeto;

import java.io.Serializable;
import java.util.ArrayList;

import Figuras.Figura;

public class Fluxograma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Figura> figuras;
	private String nomeFluxograma;
	
	public Fluxograma(){}
	
	public Fluxograma(String nome){
		figuras = new ArrayList<Figura>();
		this.nomeFluxograma = nome;
	}
	
	public Fluxograma(ArrayList<Figura> figuras){
		this.figuras = figuras;
	}

	public ArrayList<Figura> getFiguras() {
		return figuras;
	}

	public void setFiguras(ArrayList<Figura> figuras) {
		this.figuras = figuras;
	}
	
	public void adicionaFigura(Figura figura){
		this.figuras.add(figura);
	}

	public String getNomeFluxograma() {
		return nomeFluxograma;
	}

	public void setNomeFluxograma(String nomeFluxograma) {
		this.nomeFluxograma = nomeFluxograma;
	}
	
	public Figura getUltimaFigura(){
		return this.figuras.get(this.figuras.size() - 1);
	}
}