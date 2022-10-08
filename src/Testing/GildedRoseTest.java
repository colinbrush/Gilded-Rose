package Testing;
import GeneralDoing.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    //General Item test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void sellInOver(){
        Item[] items = new Item[] {new Item("foo", -1, 5),
                            new Item("foo", 20, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(4, app.items[1].quality);
    }

    @Test
    //Aged Brie Test
    void CheckAgedBrie(){
        Item[] items = new Item[] {new Item("Aged Brie", 1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    //Conjured Test
    void CheckConjured(){
        //This checks that my added Conjured feature works
        Item[] items = new Item[]{new Item("Duck", 4, 3),
                                new Item("Conjured Duck",4,3)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(1, app.items[1].quality);
    }

    @Test
    //Possible breaking test
    void CheckQualityNegative(){
        //With quality inputed as negative we want to make sure the system doesn't break
        Item[] items = new Item[] {new Item("foo", 1, -1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    //makes sure ticket updates are correct
    void checkTicketUpdates(){
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 2, 5),
                new Item("Backstage passes to a TAFKAL80ETC concert", 8, 5),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 5)
                };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
        assertEquals(7, app.items[1].quality);
        assertEquals(6, app.items[2].quality);
    }

    @Test
    //Boundary Test
    void checkTicketBoundary(){
        //This checks the boundary case of the ticket going over the time it is valuable
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 2, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    //Checking quality boundary
    void qualityMax(){
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 2, 49),
                                    new Item("Aged Brie", 2, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }

    @Test
    //Testing if someone inputs a quality over 50
    void qualityOverShoot(){
        Item[] items = new Item[] {new Item("foo", 2, 60)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    //Sulfurus Test values should stay the same
    void sulfurusStatic(){
        Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn);
        assertEquals(20, app.items[0].quality);
    }


}
