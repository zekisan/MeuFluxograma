package Projeto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Projeto implements Serializable{

	private ArrayList<Fluxograma> fluxogramas;
	private String nomeProjeto;
	private File arquivoProjeto;
	private boolean salvo = false;
	
	public Projeto(String nome){
		fluxogramas = null;
		this.nomeProjeto = nome;
	}
	
	public Projeto(ArrayList<Fluxograma> fluxogramas){
		this.fluxogramas = fluxogramas;
	}

	public ArrayList<Fluxograma> getFluxogramas() {
		return fluxogramas;
	}

	public void setFluxogramas(ArrayList<Fluxograma> fluxogramas) {
		this.fluxogramas = fluxogramas;
	}
	
	public void adicionaFluxograma(Fluxograma fluxograma){
		if(this.fluxogramas == null){
			this.fluxogramas = new ArrayList<Fluxograma>();
		}
		this.fluxogramas.add(fluxograma);
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	
	public boolean getSalvo() {
		return salvo;
	}

	public void setSalvo(boolean salvo) {
		this.salvo = salvo;
	}
	
	public File getArquivoProjeto() {
		return arquivoProjeto;
	}

	public void setArquivoProjeto(File arquivoProjeto) {
		this.arquivoProjeto = arquivoProjeto;
	}

	public Fluxograma getUltimoFluxograma(){
		return fluxogramas.get(fluxogramas.size() - 1);
	}
	
	public Fluxograma getFluxogramaAtual(int indice){
		return fluxogramas.get(indice);
	}
}
