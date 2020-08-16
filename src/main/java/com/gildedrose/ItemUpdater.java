package com.gildedrose;

import com.gildedrose.alteration.*;

import java.util.Arrays;
import java.util.List;

class ItemUpdater {
	private static final int SELL_IN_LIMIT = 0;
	private static final int SELL_IN_STEP = 1;

	private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";

	private static final List<String> ITEMS_GAIN_QUALITY_WITH_AGE = List.of("Aged Brie", BACKSTAGE_ITEM);
	private static final List<String> LEGENDARY_ITEMS = List.of("Sulfuras, Hand of Ragnaros");

	static void update(Item[] items) {
		Arrays.stream(items).filter(item -> !LEGENDARY_ITEMS.contains(item.name)).forEach(ItemUpdater::update);
	}

	private static void update(Item item) {
		affectSellIn(item);
		affectQuality(item);
	}

	private static void affectSellIn(Item item) {
		item.sellIn = item.sellIn - SELL_IN_STEP;
	}

	private static void affectQuality(Item item) {
		ItemAlteration alteration = determineAlteration(item);

		if ((item.sellIn < SELL_IN_LIMIT)) {
			alteration = new OutdatedAlterationDecorator(alteration);
		}

		alteration.alterate(item);
	}

	private static ItemAlteration determineAlteration(Item item) {
		return (ITEMS_GAIN_QUALITY_WITH_AGE.contains(item.name)) ? gainAlteration(item) : new NormalAlteration();
	}

	private static ItemAlteration gainAlteration(Item item) {
		return (BACKSTAGE_ITEM.equals(item.name)) ? new BackstageAlteration() : new GainAlteration();
	}
}
