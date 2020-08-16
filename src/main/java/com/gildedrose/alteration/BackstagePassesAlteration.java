package com.gildedrose.alteration;

import com.gildedrose.Item;

public class BackstagePassesAlteration extends PositiveAlteration {
	private static final int BACKSTAGE_FIRST_LIMIT = 10;
	private static final int BACKSTAGE_SECOND_LIMIT = 5;
	private static final int BACKSTAGE_LAST_LIMIT = 0;

	@Override
	public void alterate(Item item) {
		for (int i = 0; i < backstageQualityFactor(item); i++) {
			super.alterate(item);
		}
	}

	private static int backstageQualityFactor(Item item) {
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
