package functions;

import java.util.HashMap;
import java.util.Map;

public final class BootstrapSingleton {

    // Reverse-lookup map for getting a day from an abbreviation
    public static final Map<String, TypesE> lookup = new HashMap<String, TypesE>();
}