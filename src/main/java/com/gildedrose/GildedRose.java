package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

class GildedRose {
	private static final int QUALITY_MIN = 0;
	private static final int QUALITY_MAX = 50;
	private static final int QUALITY_STEP = 1;

	private static final int SELL_IN_LIMIT = 0;
	private static final int SELL_IN_STEP = 1;

	private static final int BACKSTAGE_FIRST_LIMIT = 10;
	private static final int BACKSTAGE_SECOND_LIMIT = 5;
	private static final int BACKSTAGE_LAST_LIMIT = SELL_IN_LIMIT;
	private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";

	private static final List<String> ITEMS_GAIN_QUALITY_WITH_AGE = List.of("Aged Brie", BACKSTAGE_ITEM);
	private static final List<String> LEGENDARY_ITEMS = List.of("Sulfuras, Hand of Ragnaros");

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		Arrays.stream(this.items).filter(item -> !LEGENDARY_ITEMS.contains(item.name)).forEach(this::updateQuality);
	}

	private void updateQuality(Item item) {
		affectSellIn(item);
		affectQuality(item);
	}

	private void affectSellIn(Item item) {
		item.sellIn = item.sellIn - SELL_IN_STEP;
	}

	private void affectQuality(Item item) {
		int outdatedDegradationFactor = outdatedDegradationFactor(item);
		if (ITEMS_GAIN_QUALITY_WITH_AGE.contains(item.name)) {
			int qualityFactor = qualityFactor(item);
			increaseQuality(item, outdatedDegradationFactor * qualityFactor);
		} else {
			decreaseQuality(item, outdatedDegradationFactor);
		}
	}

	private int outdatedDegradationFactor(Item item) {
		return (item.sellIn < SELL_IN_LIMIT) ? 2 : 1;
	}

	private void increaseQuality(Item item, int factor) {
		item.quality = Math.min(item.quality + (factor * QUALITY_STEP), QUALITY_MAX);
	}

	private void decreaseQuality(Item item, int factor) {
		item.quality = Math.max(item.quality - (factor * QUALITY_STEP), QUALITY_MIN);
	}

	private int qualityFactor(Item item) {
		return (BACKSTAGE_ITEM.equals(item.name)) ? backstageQualityFactor(item) : 1;
	}

	private int backstageQualityFactor(Item item) {
		if (item.sellIn < BACKSTAGE_LAST_LIMIT) {
			return 0;
		} else if (item.sellIn < BACKSTAGE_SECOND_LIMIT) {
			return 3;
		} else if (item.sellIn < BACKSTAGE_FIRST_LIMIT) {
			return 2;
		} else {
			return 1;
		}
	}
}