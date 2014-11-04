package Projeto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Figuras.Figura;
import Figuras.Linha;
import Figuras.Oval;

public class Fluxograma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Figura> figuras;
	private String nomeFluxograma;
	private boolean temInicio = false;
	
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
	
	public Figura getUltimaNaoLinha(){
		for (int i = figuras.size() - 1; i >= 0; i--) {
			if(!figuras.get(i).getClass().getName().equals("Linha")){
				return figuras.get(i);
			}
		}
		return null;
	}
	
	public boolean temInicio() {
		return temInicio;
	}

	public void setTemInicio(boolean temInicio) {
		this.temInicio = temInicio;
	}
	
	public boolean adicionaOvalComRegra(Oval oval){
		if(temInicio){
			return false;
		}else{
			adicionaFigura(oval);
			temInicio = true;
			return true;
		}
	}
	
	public String verificaSePodeAdicionar(Figura figura){
		if(figura.getClass().equals(getUltimaFigura().getClass())){
			return "Processo repetido.";
		}
		else if(!(getUltimaFigura().getClass().equals(Linha.class))){
			return "Um novo processo pode ser criado somente após uma linha de fluxo.";
		}
		else{
			adicionaFigura(figura);
		}
		return null;
	}
}