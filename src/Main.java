public class Main {
    public static void main(String[] args) {
        MiListaCircular miLista = new MiListaCircular();

        System.out.println("--- Insertar ---");
        miLista.insertHead("Ana");
        System.out.println(miLista.toString());
        miLista.insertTail(2);
        miLista.insertTail(true);
        miLista.insertTail("Juan");
        miLista.insertTail("Pedro");
        miLista.insertTail(false);
        System.out.println(miLista.toString());
        System.out.println("Tamaño: " + miLista.getSize());

        System.out.println("\n--- Head / Tail ---");
        System.out.println("Head: " + miLista.getHead());
        System.out.println("Tail: " + miLista.getTail());

        System.out.println("\n--- Search / Contains ---");
        Node nodoEncontrado = miLista.search(true);
        System.out.println("Search(true): " + (nodoEncontrado != null ? nodoEncontrado.dato : "null"));
        System.out.println("Contains('Juan'): " + miLista.contains("Juan"));
        System.out.println("Contains(999): " + miLista.contains(999));

        System.out.println("\n--- Get / Insert / Set usando un nodo ---");
        Node nodoJuan = miLista.search("Juan");
        System.out.println("Get(nodoJuan): " + miLista.get(nodoJuan));

        miLista.insert(nodoJuan, "Luis");
        System.out.println("Tras insert(nodoJuan, \"Luis\"): " + miLista.toString());

        miLista.insert("Pedro", "Marta");
        System.out.println("Tras insert(\"Pedro\", \"Marta\"): " + miLista.toString());

        miLista.set(nodoJuan, "JUAN_MODIFICADO");
        System.out.println("Tras set(nodoJuan, ...): " + miLista.toString());

        System.out.println("\n--- Demostrando la circularidad ---");
        System.out.print("Recorriendo 2 vueltas completas desde la cabeza: ");
        Node actual = miLista.search("Ana"); // la cabeza actual
        int vueltas = 0;
        int pasos = miLista.getSize() * 2; // damos el doble de pasos que el tamaño
        for (int i = 0; i < pasos; i++) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println("\n(Si esto no se colgó, la lista está bien cerrada en círculo)");

        System.out.println("\n--- Add ---");
        miLista.add("Al final");
        System.out.println("Tras add: " + miLista.toString());

        System.out.println("\n--- ToArray ---");
        Object[] arreglo = miLista.toArray();
        System.out.println("toArray(): " + java.util.Arrays.toString(arreglo));

        System.out.println("\n--- SubList ---");
        Node desde = miLista.search("Ana");
        Node hasta = nodoJuan;
        MiListaCircular sub = miLista.subList(desde, hasta);
        System.out.println("SubList(Ana -> Juan_modificado): " + (sub != null ? sub.toString() : "null"));

        System.out.println("\n--- Remove ---");
        miLista.remove(nodoJuan);
        System.out.println("Tras remove(nodoJuan): " + miLista.toString());
        System.out.println("Tamaño: " + miLista.getSize());

        System.out.println("\n--- SortList (lista aparte, mismo tipo) ---");
        MiListaCircular listaNumeros = new MiListaCircular();
        listaNumeros.insertTail(5);
        listaNumeros.insertTail(1);
        listaNumeros.insertTail(9);
        listaNumeros.insertTail(3);
        System.out.println("Antes de ordenar: " + listaNumeros.toString());
        MiListaCircular ordenada = listaNumeros.sortList();
        System.out.println("Ordenada: " + ordenada.toString());

        System.out.println("\n--- Clear ---");
        miLista.clear();
        System.out.println(miLista.toString());
        System.out.println("Tamaño: " + miLista.getSize());
        System.out.println("isEmpty: " + miLista.isEmpty());
    }
}