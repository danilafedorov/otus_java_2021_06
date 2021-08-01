package homework;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {

    private final Deque<Customer> customersSet = new ArrayDeque<>();

    public void add(Customer customer) {
        customersSet.push(customer);
    }

    public Customer take() {
        return customersSet.pop();
    }
}
