package homework;


import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.AbstractMap;

public class CustomerService {

    private final NavigableMap<Customer, String> container = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        return getImmutableEntry(container.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return getImmutableEntry(container.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        container.put(customer, data);
    }

    private Map.Entry<Customer, String> getImmutableEntry(Map.Entry<Customer, String> entry) {

        if (Objects.isNull(entry)) {
            return null;
        }

        return new AbstractMap.SimpleImmutableEntry<>(new Customer(entry.getKey()), entry.getValue());
    }
}
