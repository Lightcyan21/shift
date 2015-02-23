package model;

public class Apartment {
private int wohnID;
private double wohnflaeche;
private int zimmeranzahl;
private int mieteranzahl;
public Apartment(int wohnID, double wohnflaeche, int zimmeranzahl,
		int mieteranzahl) {
	super();
	this.wohnID = wohnID;
	this.wohnflaeche = wohnflaeche;
	this.zimmeranzahl = zimmeranzahl;
	this.mieteranzahl = mieteranzahl;
}
public Apartment(int wohnID, double wohnflaeche, int zimmeranzahl,
		int mieteranzahl,int mieterID) {
	super();
	this.wohnID = wohnID;
	this.wohnflaeche = wohnflaeche;
	this.zimmeranzahl = zimmeranzahl;
	this.mieteranzahl = mieteranzahl;
}

public void addMieterAnzahl(int mieterAnzahl){
	
}

}
