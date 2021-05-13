package br.capgemini.desafio.agencia.banco;

import java.util.ArrayList;
import java.util.List;

import br.capgemini.desafio.agencia.model.Anuncio;
import br.capgemini.desafio.agencia.util.Util;

public class BancoDeDados {

	private static List<Anuncio> anuncios = new ArrayList<Anuncio>();

	private BancoDeDados() {
	}

	static {
		Anuncio anuncio1 = new Anuncio();
		anuncio1.setId(1L);
		anuncio1.setNome("Anúncio A");
		anuncio1.setCliente("Cliente A");
		anuncio1.setDataInicio(Util.getConverteStringToDate("01/01/2019"));
		anuncio1.setDataTermino(Util.getConverteStringToDate("01/01/2020"));
		anuncio1.setInvestimentoPorDia(1000d);
		Anuncio anuncio2 = new Anuncio();
		anuncio2.setId(2L);
		anuncio2.setNome("Anúncio B");
		anuncio2.setCliente("Cliente B");
		anuncio2.setDataInicio(Util.getConverteStringToDate("01/01/2020"));
		anuncio2.setDataTermino(Util.getConverteStringToDate("01/01/2021"));
		anuncio2.setInvestimentoPorDia(500d);
		Anuncio anuncio3 = new Anuncio();
		anuncio3.setId(3L);
		anuncio3.setNome("Anúncio C");
		anuncio3.setCliente("Cliente C");
		anuncio3.setDataInicio(Util.getConverteStringToDate("12/05/2019"));
		anuncio3.setDataTermino(Util.getConverteStringToDate("14/06/2019"));
		anuncio3.setInvestimentoPorDia(2000d);
		Anuncio anuncio4 = new Anuncio();
		anuncio4.setId(4L);
		anuncio4.setNome("Anúncio D");
		anuncio4.setCliente("Cliente D");
		anuncio4.setDataInicio(Util.getConverteStringToDate("12/05/2018"));
		anuncio4.setDataTermino(Util.getConverteStringToDate("14/06/2019"));
		anuncio4.setInvestimentoPorDia(200.50d);

		anuncios.add(anuncio1);
		anuncios.add(anuncio2);
		anuncios.add(anuncio3);
		anuncios.add(anuncio4);
	}

	public static List<Anuncio> obterAnuncios() {
		return anuncios;
	}

	public static Anuncio obterAnuncio(Long id) {
		Anuncio anuncio = null;
		for (int i = 0; i < anuncios.size(); i++) {
			if (anuncios.get(i).getId().equals(id)) {
				anuncio = anuncios.get(i);
				}
			}
		return anuncio;

	}

	public static void adicionarAnuncio(Anuncio anuncio) {
		anuncios.add(anuncio);
	}

	public static void alterarAnuncio(Anuncio anuncio) {
		for (int i = 0; i < anuncios.size(); i++) {
			if (anuncios.get(i).getId().equals(anuncio.getId())) {
				anuncios.get(i).setNome(anuncio.getNome());
				anuncios.get(i).setCliente(anuncio.getCliente());
				anuncios.get(i).setDataInicio(anuncio.getDataInicio());
				anuncios.get(i).setDataTermino(anuncio.getDataTermino());
				anuncios.get(i).setInvestimentoPorDia(anuncio.getInvestimentoPorDia());
			}
		}
	}

	public static void deletarAnuncio(Long id) {
		for (Anuncio anuncio : anuncios) {
			if (anuncio.getId().equals(id)) {
				anuncios.remove(anuncio);
				break;
			}
		}
	}

}
