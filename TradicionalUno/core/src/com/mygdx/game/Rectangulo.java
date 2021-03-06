package com.mygdx.game;

public class Rectangulo {

	public Posicion posicion;
	public int ancho, alto;
	public int arriba, derecha;
	private int izquierda;
	private int abajo;

	public Rectangulo(Posicion posicion, int ancho, int alto) {
		super();
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		calculaLados();
		// this.derecha = posicion.x + ancho;
		// this.arriba = posicion.y + alto;
	}

	public boolean contiene(Rectangulo rectangulo) {
		calculaLados();
		if(izquierda<rectangulo.izquierda)
			return false;
		if(derecha>rectangulo.derecha)
			return false;
		if(arriba>rectangulo.arriba)
			return false;
		if(abajo<rectangulo.abajo)
			return false;
		return true;
	}

	public boolean solapa(Rectangulo rectangulo) {
		calculaLados();
		rectangulo.calculaLados();
		boolean porIzquieda= izquierda>rectangulo.izquierda&& izquierda<rectangulo.derecha;
		boolean porDerecha= derecha<rectangulo.derecha&&derecha>rectangulo.izquierda;
		boolean porArriba=arriba>rectangulo.abajo && arriba<rectangulo.arriba;
		boolean porAbajo= abajo<rectangulo.arriba&&abajo>rectangulo.abajo;
		return (porArriba||porAbajo)&&(porIzquieda||porDerecha);
	}

	public void calculaLados() {
		izquierda=posicion.x;
		abajo=posicion.y;
		arriba = posicion.y + alto;
		derecha = posicion.x + ancho;
	}
}
