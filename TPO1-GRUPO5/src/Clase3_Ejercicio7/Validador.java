package Clase3_Ejercicio7;

public class Validador {

    // creo un constructor vacio ya que no hay atributos por definir, esta clase solo contiene metodos
    public Validador() {
    }

    // creo el metodo para validar parentesis
    public boolean validarParentesis(String cadena) {
        /*
        El metodo saca cada '(' por cada ')' que se ingrese
        Si quedan '(' en la pila quiere decir que el resultado es false ya que hay parentesis sin cerrar
         */

        LinkedStack<Character> pila = new LinkedStack<>(); // creo una pila que recibe caracteres

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i); // chatAt es un metodo propio de la clase String, que devuelve el caracter que esta en la posicion dada por parametro para el string analizado

            if (c == '(') { // puesto entre comillas simples porque es un char
                pila.push(c); // en el caso de que se ingrese un '(' entonces se agrega a la pila
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false; // hay un ')' sin su '('
                }
                pila.pop(); // en el caso de que SI haya un '(', si el caracter actual es un ')' entonces elimina el ultimo '(' de la pila
            }
        }
        return pila.isEmpty(); // si la pila esta vacia quiere decir que no hay parentesis sin cerrar entonces devuelve true
    }
}
