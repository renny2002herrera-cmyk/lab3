public class MiListaCircular implements ListInterface {
    Node cabeza = null;

    @Override
    public boolean isEmpty() {
        return cabeza == null;
    }

    @Override
    public int getSize() {
        if (cabeza == null) {
            return 0;
        }

        int contador = 0;
        Node actual = cabeza;

        do {
            contador++;
            actual = actual.siguiente;
        } while (actual != cabeza);

        return contador;
    }

    @Override
    public void clear() {
        cabeza = null;
    }

    @Override
    public Object getHead() {
        if (cabeza == null) {
            return null;
        }
        return cabeza.dato;
    }

    @Override
    public Object getTail() {
        if (cabeza == null) {
            return null;
        }

        Node actual = cabeza;
        while (actual.siguiente != cabeza) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    @Override
    public Object get(Node node) {
        if (node == null) {
            return null;
        }
        return node.dato;
    }

    @Override
    public Node search(Object object) {
        if (object == null || cabeza == null) {
            return null;
        }

        Node actual = cabeza;
        do {
            if (actual.dato.equals(object)) {
                return actual;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);

        return null;
    }

    @Override
    public boolean add(Object object) {
        if (object == null) return false;

        Node nuevo = new Node(object);

        if (cabeza == null) {
            cabeza = nuevo;
            nuevo.siguiente = nuevo;
            return true;
        }

        Node ultimo = cabeza;
        while (ultimo.siguiente != cabeza) {
            ultimo = ultimo.siguiente;
        }

        ultimo.siguiente = nuevo;
        nuevo.siguiente = cabeza;

        return true;
    }

    @Override
    public boolean insert(Node node, Object object) {
        if (node == null || object == null || cabeza == null) return false;

        Node actual = cabeza;
        boolean pertenece = false;
        do {
            if (actual == node) {
                pertenece = true;
                break;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);

        if (!pertenece) return false;

        Node nuevo = new Node(object);
        nuevo.siguiente = node.siguiente;
        node.siguiente = nuevo;

        return true;
    }

    @Override
    public boolean insert(Object objectRef, Object object) {
        Node nodoReferencia = search(objectRef);

        if (nodoReferencia == null) {
            return false;
        }
        return insert(nodoReferencia, object);
    }

    @Override
    public boolean insertHead(Object object) {
        if (object == null) return false;

        Node nuevo = new Node(object);

        if (cabeza == null) {
            cabeza = nuevo;
            nuevo.siguiente = nuevo;
            return true;
        }

        Node ultimo = cabeza;
        while (ultimo.siguiente != cabeza) {
            ultimo = ultimo.siguiente;
        }

        nuevo.siguiente = cabeza;
        ultimo.siguiente = nuevo;
        cabeza = nuevo;

        return true;
    }

    @Override
    public boolean insertTail(Object object) {
        return add(object);
    }

    @Override
    public boolean set(Node node, Object object) {
        if (node == null || object == null || cabeza == null) return false;

        Node actual = cabeza;
        do {
            if (actual == node) {
                actual.dato = object;
                return true;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);

        return false;
    }

    @Override
    public boolean remove(Node node) {
        if (node == null || cabeza == null) return false;

        if (cabeza == node && cabeza.siguiente == cabeza) {
            cabeza = null;
            return true;
        }

        Node anterior = cabeza;
        do {
            if (anterior.siguiente == node) {
                anterior.siguiente = node.siguiente; // saltamos el nodo
                if (node == cabeza) {
                    cabeza = node.siguiente; // si era la cabeza, movemos la cabeza
                }
                return true;
            }
            anterior = anterior.siguiente;
        } while (anterior != cabeza);

        return false;
    }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[getSize()];

        if (cabeza == null) {
            return arreglo;
        }

        Node actual = cabeza;
        int i = 0;
        do {
            arreglo[i] = actual.dato;
            i++;
            actual = actual.siguiente;
        } while (actual != cabeza);

        return arreglo;
    }

    @Override
    public Object[] toArray(Object[] object) {
        int size = getSize();

        if (object.length < size) {
            object = new Object[size];
        }

        if (cabeza != null) {
            Node actual = cabeza;
            int i = 0;
            do {
                object[i] = actual.dato;
                i++;
                actual = actual.siguiente;
            } while (actual != cabeza);
        }

        if (object.length > size) {
            object[size] = null;
        }

        return object;
    }

    @Override
    public MiListaCircular subList(Node from, Node to) {
        if (from == null || to == null || cabeza == null) return null;

        MiListaCircular sub = new MiListaCircular();
        Node actual = from;

        do {
            sub.insertTail(actual.dato);
            if (actual == to) {
                return sub;
            }
            actual = actual.siguiente;
        } while (actual != from);

        return null;
    }

    @Override
    public MiListaCircular sortList() {
        MiListaCircular ordenada = new MiListaCircular();
        Object[] arreglo = this.toArray();

        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1 - i; j++) {
                Comparable actual = (Comparable) arreglo[j];
                if (actual.compareTo(arreglo[j + 1]) > 0) {
                    Object temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }

        for (Object dato : arreglo) {
            ordenada.insertTail(dato);
        }

        return ordenada;
    }

    @Override
    public String toString() {
        if (cabeza == null) {
            return "[]";
        }

        String resultado = "[";
        Node actual = cabeza;

        do {
            resultado = resultado + actual.dato;
            actual = actual.siguiente;
            if (actual != cabeza) {
                resultado = resultado + ", ";
            }
        } while (actual != cabeza);

        resultado = resultado + "]";
        return resultado;
    }
}