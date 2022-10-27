package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        //Hentet fra kompendiet med
        Objects.requireNonNull(verdi,"Ulovlig med null verdier");

        Node<T> p = rot; //p starter med roten
        Node<T> q = null;
        int cmp = 0;

        while (p!= null) {  //Forstetter til p er ute av treet
            q = p; //q er foreldre noden til p
            cmp = comp.compare(verdi,p.verdi); // Sammenligner p og verdi
            p = cmp < 0 ? p.venstre : p.høyre;
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte
        p = new Node<>(verdi, q); // Gir p en ny verdi siden den er null

        if (q == null) rot = p; // p blir rotnode
        else if (cmp < 0) q.venstre = p;  //venstre barn til q
        else q.høyre = p; //høyre barn til q

        antall++;  // En verdi mer i treet
        return true; // Vellykket innlegging
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        if (verdi == null) return 0;
        int teller = 0;
        int cmp = 0;
        //Først må jeg se om treet innhodler verdien
        if (inneholder(verdi)) {
            Node<T> p = rot;
            Node<T> q = null;

            //Deretter må jeg loope gjennom hver iterasjon av treet som innholder vedien

            while (p != null) {

                cmp = comp.compare(verdi, p.verdi);

                if (cmp < 0) {
                    p = p.venstre;
                } else {
                    if (p.verdi == verdi) {
                        teller++;
                    }
                    p = p.høyre;
                }
            }
        }
        return teller;

    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Loop basert på kompendiet
        while (true) {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else  {
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public static void main(String[] args) {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder()); for (int verdi : a) { tre.leggInn(verdi); }
        System.out.println(tre.antall()); System.out.println(tre.antall(5)); System.out.println(tre.antall(4)); System.out.println(tre.antall(7)); System.out.println(tre.antall(10));
// Utskrift: 10
// Utskrift: 0
// Utskrift: 3
// Utskrift: 2
// Utskrift: 1

    }


} // ObligSBinTre
