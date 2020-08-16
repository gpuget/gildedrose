package com.gildedrose;

import com.gildedrose.alteration.BackstagePassesAlteration;
import com.gildedrose.alteration.ItemAlteration;
import com.gildedrose.alteration.NegativeAlteration;
import com.gildedrose.alteration.PositiveAlteration;
import com.gildedrose.alteration.decorator.ConjuredAlterationDecorator;
import com.gildedrose.alteration.decorator.OutdatedAlterationDecorator;

import java.util.Arrays;
import java.util.List;

class ItemUpdater {
	private static final int SELL_IN_LIMIT = 0;
	private static final int SELL_IN_STEP = 1;

	private static final List<String> ITEMS_WITH_POSITIVE_ALTERATION = List.of("Aged Brie");
	private static final List<String> LEGENDARY_ITEMS = List.of("Sulfuras, Hand of Ragnaros");

	private static final String BACKSTAGE_PASSES_PREFIX = "Backstage passes";
	private static final String CONJURED_PREFIX = "Conjured";

	static void update(Item[] items) {
		Arrays.stream(items).filter(ItemUpdater::isNotLengendary).forEach(ItemUpdater::update);
	}

	private static boolean isNotLengendary(Item item) {
		return !LEGENDARY_ITEMS.contains(item.name);
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

		if (isOutdated(item)) {
			alteration = new OutdatedAlterationDecorator(alteration);
		}

		alteration.alterate(item);
	}

	private static ItemAlteration determineAlteration(Item item) {
		if (hasPositiveAlteration(item)) {
			return new PositiveAlteration();
		} else if (isBackstagePasses(item)) {
			return new BackstagePassesAlteration();
		} else {
			return negativeAlteration(item);
		}
	}

	private static boolean hasPositiveAlteration(Item item) {
		return ITEMS_WITH_POSITIVE_ALTERATION.contains(item.name);
	}

	private static boolean isBackstagePasses(Item item) {
		return item.name.startsWith(BACKSTAGE_PASSES_PREFIX);
	}

	private static ItemAlteration negativeAlteration(Item item) {
		ItemAlteration alteration = new NegativeAlteration();
		return isConjured(item) ? new ConjuredAlterationDecorator(alteration) : alteration;
	}

	private static boolean isConjured(Item item) {
		return item.name.startsWith(CONJURED_PREFIX);
	}

	private static boolean isOutdated(Item item) {
		return item.sellIn < SELL_IN_LIMIT;
	}
}
