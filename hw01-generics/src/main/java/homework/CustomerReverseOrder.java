package homework;

import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {

    private final Deque<Customer> customersSet = new LinkedList<>();

    public void add(Customer customer) {
        customersSet.add(customer);
    }

    public Customer take() {
        return customersSet.pollLast();
    }
}
