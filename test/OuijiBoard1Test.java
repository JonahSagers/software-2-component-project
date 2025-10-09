import java.util.Comparator;

import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;
import components.sortingmachine.SortingMachine3;

/**
 * Customized JUnit test fixture for {@code SortingMachine3}.
 */
public final class OuijiBoard1Test extends OuijiBoardTest {

    @Override
    protected SortingMachine<String> constructorTest(Comparator<String> order) {
        return new SortingMachine3<String>(order);
    }

    @Override
    protected SortingMachine<String> constructorRef(Comparator<String> order) {
        return new SortingMachine1L<String>(order);
    }

}
