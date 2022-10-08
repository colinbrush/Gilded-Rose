package GeneralDoing;

public class GildedRose {
    //The assignment didn't tell us to refactor the code but I did a little bit mostly
    //in adding the features that they had in the gilded rose read me.
    //If more refactoring was intended I can do that just was unsure if that was what we were supposed to do
    public Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            int conjured = 1;
            if(items[i].name.split(" ")[0].equals("Conjured")){
                System.out.println("Conjured");
                conjured = 2;
            }
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")
                    && !items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                if (items[i].quality > 0) {
                        items[i].quality = items[i].quality - conjured;
                }
            } else {
                items[i].quality = items[i].quality + 1;
                if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (items[i].sellIn <= 10){
                        items[i].quality = items[i].quality + 1;
                    }
                    if (items[i].sellIn <= 5) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - conjured;
                            }
                        }
                    } else {
                        //The backstage passes are now worthless
                        items[i].quality = 0;
                    }
                } else {
                    items[i].quality = items[i].quality + 1;
                }
            }
            if(items[i].quality > 50){
                items[i].quality = 50;
            }
        }
    }
}