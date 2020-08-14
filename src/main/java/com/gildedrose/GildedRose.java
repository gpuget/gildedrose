package com.gildedrose;

import java.util.List;

class GildedRose {
	private static final int QUALITY_MIN = 0;
	private static final int QUALITY_MAX = 50;
	private static final int QUALITY_STEP = 1;
	private static final int BACKSTAGE_FIRST_LIMIT = 11;
	private static final int BACKSTAGE_SECOND_LIMIT = 6;

	private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";
	private static final List<String> ITEMS_GAIN_QUALITY_WITH_AGE = List.of("Aged Brie", BACKSTAGE_ITEM);
	private static final List<String> LEGENDARY_ITEMS = List.of("Sulfuras, Hand of Ragnaros");

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			if (!LEGENDARY_ITEMS.contains(item.name)) {
				affectQuality(item);
				affectSellIn(item);
			}
		}
	}

	private void affectQuality(Item item) {
		if (ITEMS_GAIN_QUALITY_WITH_AGE.contains(item.name)) {
			increaseQuality(item);
		} else {
			decreaseQuality(item);
		}
	}

	private void decreaseQuality(Item item) {
		item.quality = Math.max(item.quality - QUALITY_STEP, QUALITY_MIN);
	}

	private void increaseQuality(Item item) {
		int modifier = 1;
		if (BACKSTAGE_ITEM.equals(item.name)) {
			modifier = backstageQualityModifier(item);
		}

		item.quality = Math.min(item.quality + (modifier * QUALITY_STEP), QUALITY_MAX);
	}

	private int backstageQualityModifier(Item item) {
		if (item.sellIn < BACKSTAGE_SECOND_LIMIT) {
			return 3;
		} else if (item.sellIn < BACKSTAGE_FIRST_LIMIT) {
			return 2;
		} else {
			return 1;
		}
	}

	private void affectSellIn(Item item) {
		item.sellIn = item.sellIn - 1;
		if (item.sellIn < 0) {
			if (!item.name.equals("Aged Brie")) {
				if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
					if (item.quality > 0) {
						if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
							item.quality = item.quality - 1;
						}
					}
				} else {
					item.quality = item.quality - item.quality;
				}
			} else {
				if (item.quality < 50) {
					item.quality = item.quality + 1;
				}
			}
		}
	}
}