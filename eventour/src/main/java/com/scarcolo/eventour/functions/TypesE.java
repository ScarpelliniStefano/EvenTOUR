package com.scarcolo.eventour.functions;

// TODO: Auto-generated Javadoc
/**
 * The Enum TypesE.
 */
public enum TypesE {
	
	/** The pop. */
	POP ("1.1.1","Concerto - Musica commerciale - Pop"),
	
	/** The discodance. */
	DISCODANCE ("1.1.2","Concerto - Musica commerciale - Disco/Dance"),
	
	/** The altro comm. */
	ALTRO_COMM ("1.1.3","Concerto - Musica commerciale - Altro"),
	
	/** The hard rock. */
	HARD_ROCK ("1.2.1","Concerto - Rock - Hard rock"),
	
	/** The metal. */
	METAL ("1.2.2","Concerto - Rock - Metal"),
	
	/** The punk. */
	PUNK ("1.2.3","Concerto - Rock - Punk"),
	
	/** The altro rock. */
	ALTRO_ROCK ("1.2.4","Concerto - Rock - Altro"),
	
	/** The trap. */
	TRAP ("1.3.1","Concerto - Rap - Trap"),
	
	/** The freestyle. */
	FREESTYLE ("1.3.2","Concerto - Rap - Freestyle"),
	
	/** The battle. */
	BATTLE ("1.3.3","Concerto - Rap - Battle"),
	
	/** The lirica. */
	LIRICA ("1.4.1","Concerto - Classica - Lirica"),
	
	/** The orchestrale. */
	ORCHESTRALE ("1.4.2","Concerto - Classica - Orchestrale"),
	
	/** The strumentale. */
	STRUMENTALE ("1.4.3","Concerto - Classica - Strumentale"),
	
	/** The gospel. */
	GOSPEL ("1.4.4","Concerto - Classica - Gospel"),
	
	/** The musical. */
	MUSICAL ("2.1","Teatro - Musical"),
	
	/** The commedia. */
	COMMEDIA ("2.2","Teatro - Commedia"),
	
	/** The opera lirica. */
	OPERA_LIRICA ("2.3","Teatro - Opera lirica"),
	
	/** The prosa. */
	PROSA ("2.4","Teatro - Prosa"),
	
	/** The magia cabaret. */
	MAGIA_CABARET ("2.5","Teatro - Magia/Cabaret"),
	
	/** The tragedia. */
	TRAGEDIA ("2.6","Teatro - Tragedia"),
	
	/** The danza. */
	DANZA("2.7","Teatro - Danza"),
	
	/** The sfilata. */
	SFILATA ("3.1","Altri eventi - Sfilata"),
	
	/** The proiezione speciale. */
	PROIEZIONE_SPECIALE("3.2","Altri eventi - Proiezione speciale (non solo al cinema)"),
	
	/** The evento sportivo. */
	EVENTO_SPORTIVO ("3.3","Altri eventi - Evento sportivo"),
	
	/** The firmacopie presentazione. */
	FIRMACOPIE_PRESENTAZIONE ("3.4","Altri eventi - Firmacopie/Presentazione"),
	
	/** The circo. */
	CIRCO ("3.5","Altri eventi - Circo");

	/** The code. */
	private final String code; 
    
    /** The name. */
    private final String name; 
    
    /**
     * Instantiates a new types E.
     *
     * @param code the code
     * @param name the name
     */
    TypesE(String code, String name) {
        this.code=code;
        this.name=name;
        BootstrapSingleton.lookup.put(code, this);
    }
    
    /**
     * Code.
     *
     * @return the string
     */
    public String code() { return code; }
    
    /**
     * Description.
     *
     * @return the string
     */
    public String description() { return name; }

    /**
     * Find code.
     *
     * @param codeFind the code find
     * @return the types E
     */
    public static TypesE findCode(String codeFind) {
    	 return BootstrapSingleton.lookup.get(codeFind);
    }
}
