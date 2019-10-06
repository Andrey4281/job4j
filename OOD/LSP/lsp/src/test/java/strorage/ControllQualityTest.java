package strorage;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControllQualityTest {
    private ControllQuality controllQuality;
    private Storage shop;
    private Storage warehhouse;
    private Storage trash;


    @Before
    public void setControllQuality() {
        shop = new Shop(new DAOForFoodImplCollect(new LinkedList<>()));
        warehhouse = new Warehouse(new DAOForFoodImplCollect(new LinkedList<>()));
        trash = new Trash(new DAOForFoodImplCollect(new LinkedList<>()));

        DAOForStorage daoForStorage = new DAOForStorageImplList();
        daoForStorage.add(shop);
        daoForStorage.add(warehhouse);
        daoForStorage.add(trash);

        controllQuality = new ControllQuality(daoForStorage);
    }

    @Test
    public void distributeFoodInStorage() {
        List<Food> products = new LinkedList<>();
        Calendar expaireDateForMeat = new GregorianCalendar();
        expaireDateForMeat.add(Calendar.DAY_OF_MONTH, 7);
        Calendar createDateForMeat = new GregorianCalendar();
        products.add(new Meat("breast", expaireDateForMeat, createDateForMeat, 300, false, "chick"));

        Calendar expaireDateForMilkKindOne = new GregorianCalendar();
        expaireDateForMilkKindOne.add(Calendar.DAY_OF_MONTH, -1);
        Calendar createDateForMilkKindOne = new GregorianCalendar();
        createDateForMilkKindOne.add(Calendar.DAY_OF_MONTH, -7);
        products.add(new Milk("yogurt", expaireDateForMilkKindOne, createDateForMilkKindOne, 60, false, 2.5));

        Calendar expaireDateForMilkKindTwo = new GregorianCalendar();
        expaireDateForMilkKindTwo.add(Calendar.DAY_OF_MONTH, 1);
        Calendar createDateForMilkKindTwo = new GregorianCalendar();
        createDateForMilkKindTwo.add(Calendar.DAY_OF_MONTH, -6);
        products.add(new Milk("sour cream", expaireDateForMilkKindTwo, createDateForMilkKindTwo, 70, false, 5));

        controllQuality.distributeFoodInStorage(products);
        List<Food> listOfWareHouse = warehhouse.getAllProducts();
        List<Food> listOfTrash = trash.getAllProducts();
        List<Food> listOfShop = shop.getAllProducts();

        assertThat(listOfShop.size(), is(1));
        assertThat(listOfTrash.size(), is(1));
        assertThat(listOfWareHouse.size(), is(1));
        assertThat(listOfShop.get(0).getName(), is("sour cream"));
        assertThat(listOfShop.get(0).getDisscount(), is(true));
        assertThat(listOfTrash.get(0).getName(), is("yogurt"));
        assertThat(listOfTrash.get(0).getDisscount(), is(false));
        assertThat(listOfWareHouse.get(0).getName(), is("breast"));
        assertThat(listOfWareHouse.get(0).getDisscount(), is(false));
    }
}