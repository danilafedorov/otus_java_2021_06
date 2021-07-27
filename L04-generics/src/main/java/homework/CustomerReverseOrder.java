package homework;

import java.util.*;

public class CustomerReverseOrder {

    private final Deque<Customer> customersSet = new LinkedList<>();

    public void add(Customer customer) {
        customersSet.add(customer);
    }

    public Customer take() {
        return customersSet.pollLast();
    }
}
