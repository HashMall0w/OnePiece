package it.marsico.treasure.pg;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public static List<String> inventario = new ArrayList<>();

    public void setInventario() {
        inventario.add("Cibo");
    }
}
