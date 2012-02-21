package server;

import java.io.Serializable;

//ID
//0 = 
//1 =


public class DatiScambiati implements Serializable
{
	private static final long serialVersionUID = 5161090700500965664L;

	int id;
	public String nomeFile;
	public String contenutoFile;
	//Stats statistiche;
	
	
	public DatiScambiati(int id, String nomeFile, String contenutoFile) 
	{
		this.id            = id;
		this.nomeFile      = nomeFile;
		this.contenutoFile = contenutoFile;
		//this.statistiche   = null;
	}

	
	public DatiScambiati(int id)//, Stats stat) 
	{
		this.id            = id;
		this.nomeFile      = null;
		this.contenutoFile = null;
		//this.statistiche   = stat;
	}

}
