package members;

@FunctionalInterface
public interface Calculator <T extends Number> {
    double calculateFees(T clubID);
}
