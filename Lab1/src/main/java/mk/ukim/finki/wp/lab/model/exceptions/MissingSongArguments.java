package mk.ukim.finki.wp.lab.model.exceptions;

public class MissingSongArguments extends RuntimeException {
    public MissingSongArguments() {
        super("Missing song arguments.");
    }
}


