package pt.ips.pa.model.patterns.factory;

import java.io.Serializable;

public final class GestorFactory implements Serializable{
    
    private static final GestorFactory instance = new GestorFactory();

    public static GestorFactory getInstance() {
        return instance;
    }

    public AbstractFactory getFactory(String tema){
        AbstractFactory factory = null;
        switch(tema){
            case "Halloween": factory = new HalloweenFactory(); break;
            case "Smileys": factory = new SmileysFactory(); break;
            case "War": factory = new WarFactory(); break;
            case "Gravity Falls": factory = new GravityFallsFactory(); break;
            case "Steven Universe": factory = new StevenUniverseFactory(); break;
            case "Pokémon": factory = new PokemonFactory(); break;
        }
        return factory;
    }
}
