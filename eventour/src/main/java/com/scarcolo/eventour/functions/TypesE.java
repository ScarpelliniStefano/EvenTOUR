package com.scarcolo.eventour.functions;

public enum TypesE {
	POP ("1.1.1","Concerto - Musica commerciale - Pop"),
	DISCODANCE ("1.1.2","Concerto - Musica commerciale - Disco/Dance"),
	ALTRO_COMM ("1.1.3","Concerto - Musica commerciale - Altro"),
	HARD_ROCK ("1.2.1","Concerto - Rock - Hard rock"),
	METAL ("1.2.2","Concerto - Rock - Metal"),
	PUNK ("1.2.3","Concerto - Rock - Punk"),
	ALTRO_ROCK ("1.2.4","Concerto - Rock - Altro"),
	TRAP ("1.3.1","Concerto - Rap - Trap"),
	FREESTYLE ("1.3.2","Concerto - Rap - Freestyle"),
	BATTLE ("1.3.3","Concerto - Rap - Battle"),
	LIRICA ("1.4.1","Concerto - Classica - Lirica"),
	ORCHESTRALE ("1.4.2","Concerto - Classica - Orchestrale"),
	STRUMENTALE ("1.4.3","Concerto - Classica - Strumentale"),
	GOSPEL ("1.4.4","Concerto - Classica - Gospel"),
	MUSICAL ("2.1","Teatro - Musical"),
	COMMEDIA ("2.2","Teatro - Commedia"),
	OPERA_LIRICA ("2.3","Teatro - Opera lirica"),
	PROSA ("2.4","Teatro - Prosa"),
	MAGIA_CABARET ("2.5","Teatro - Magia/Cabaret"),
	TRAGEDIA ("2.6","Teatro - Tragedia"),
	DANZA("2.7","Teatro - Danza"),
	SFILATA ("3.1","Altri eventi - Sfilata"),
	PROIEZIONE_SPECIALE("3.2","Altri eventi - Proiezione speciale (non solo al cinema)"),
	EVENTO_SPORTIVO ("3.3","Altri eventi - Evento sportivo"),
	FIRMACOPIE_PRESENTAZIONE ("3.4","Altri eventi - Firmacopie/Presentazione"),
	CIRCO ("3.5","Altri eventi - Circo");

	private final String code; 
    private final String name; 
    TypesE(String code, String name) {
        this.code=code;
        this.name=name;
        BootstrapSingleton.lookup.put(code, this);
    }
    public String code() { return code; }
    public String description() { return name; }

    public static TypesE findCode(String codeFind) {
    	 return BootstrapSingleton.lookup.get(codeFind);
    }
}
