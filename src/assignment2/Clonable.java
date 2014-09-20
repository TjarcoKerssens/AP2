package assignment2;

public interface Clonable extends Cloneable {

    /* In order to be able to use clone() everywhere is is overridden with
       a public version.
    */

    public Object clone();

}
