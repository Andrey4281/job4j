package strorage;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ContorllQualityTestNewFunctionall {
    private ControllQuality controllQuality;
    private Storage newWarehouse;
    private Storage trash;
    private Storage warehouseWithMinusTemperature;
    private Storage recycler;

    @Before
    public void setControllQuality() {
        newWarehouse = new NewWarehouse(new Warehouse(new DAOForFoodImplCollect(new LinkedList<>())));
        trash = new ExtendedTrash(new Trash(new DAOForFoodImplCollect(new LinkedList<>())));
        warehouseWithMinusTemperature = new WarehouseWithMinusTemperature(new Warehouse(new DAOForFoodImplCollect(new LinkedList<>())));
        recycler = new Recycler(new Trash(new DAOForFoodImplCollect(new LinkedList<>())));

        DAOForStorage daoForStorage = new DAOForStorageImplList();
        daoForStorage.add(newWarehouse);
        daoForStorage.add(trash);
        daoForStorage.add(warehouseWithMinusTemperature);
        daoForStorage.add(recycler);

        controllQuality = new ControllQuality(daoForStorage);
    }

    @Test
    public void distributeFoodInStorage() {
        List<Food> products = new LinkedList<>();
        Calendar expaireDateForMeat = new GregorianCalendar();
        expaireDateForMeat.add(Calendar.DAY_OF_MONTH, 7);
        Calendar createDateForMeat = new GregorianCalendar();
        products.add(new Meat("breast", expaireDateForMeat, createDateForMeat, 300, false, "chick", false, false));


        Calendar expaireDateForMilkKindOne = new GregorianCalendar();
        expaireDateForMilkKindOne.add(Calendar.DAY_OF_MONTH, -1);
        Calendar createDateForMilkKindOne = new GregorianCalendar();
        createDateForMilkKindOne.add(Calendar.DAY_OF_MONTH, -7);
        products.add(new Milk("yogurt", expaireDateForMilkKindOne, createDateForMilkKindOne, 60, false, 2.5, false, false));

        Calendar expaireDateForMilkKindTwo = new GregorianCalendar();
        expaireDateForMilkKindTwo.add(Calendar.DAY_OF_MONTH, -1);
        Calendar createDateForMilkKindTwo = new GregorianCalendar();
        createDateForMilkKindTwo.add(Calendar.DAY_OF_MONTH, -7);
        products.add(new Milk("sour cream", expaireDateForMilkKindTwo, createDateForMilkKindTwo, 70, false, 5, true, false));

        Calendar expaireDateForVegetables = new GregorianCalendar();
        expaireDateForVegetables.add(Calendar.DAY_OF_MONTH, 6);
        Calendar createDateForVegetables = new GregorianCalendar();
        products.add(new Vegetables("carrot", expaireDateForVegetables, createDateForVegetables, 70, false,false));

        controllQuality.distributeFoodInStorage(products);
        controllQuality.resort();
        List<Food> listOfNewWareHouse = newWarehouse.getAllProducts();
        List<Food> listOfTrash = trash.getAllProducts();
        List<Food> listOfRecycler = recycler.getAllProducts();
        List<Food> listOfWarehouseWithMinusTemperature = warehouseWithMinusTemperature.getAllProducts();

        assertThat(listOfNewWareHouse.size(), is(1));
        assertThat(listOfTrash.size(), is(1));
        assertThat(listOfRecycler.size(), is(1));
        assertThat(listOfWarehouseWithMinusTemperature.size(), is(1));
        assertThat(listOfNewWareHouse.get(0).getName(), is("breast"));
        assertThat(listOfTrash.get(0).getName(), is("yogurt"));
        assertThat(listOfRecycler.get(0).getName(), is("sour cream"));
        assertThat(listOfWarehouseWithMinusTemperature.get(0).getName(), is("carrot"));
    }
}
