package com.gildedrose.alteration;

import com.gildedrose.Item;

public class BackstagePassesAlteration extends PositiveAlteration {
	private static final int BACKSTAGE_FIRST_LIMIT = 10;
	private static final int BACKSTAGE_SECOND_LIMIT = 5;
	private static final int BACKSTAGE_LAST_LIMIT = 0;

	@Override
	public void alterate(Item item) {
		alterate(item, backstageAlterationValue(item));
	}

	private static int backstageAlterationValue(Item item) {
		if (item.sellIn < BACKSTAGE_LAST_LIMIT) {
			return -item.quality;
		} else if (item.sellIn < BACKSTAGE_SECOND_LIMIT) {
			return 3 * QUALITY_DAMAGE;
		} else if (item.sellIn < BACKSTAGE_FIRST_LIMIT) {
			return 2 * QUALITY_DAMAGE;
		} else {
			return QUALITY_DAMAGE;
		}
	}
}
