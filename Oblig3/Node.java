//lager klasse Node som tar vare paa neste node og data som lagres i denne noden
public class Node<T> {
    //oppretter instansvariablene
    Node<T> neste;
    T data;

    //oppretter konstruktoren
    Node(T data){
        this.data = data;
    }

}
