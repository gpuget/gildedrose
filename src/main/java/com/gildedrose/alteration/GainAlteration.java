package com.gildedrose.alteration;

import com.gildedrose.Item;

public class GainAlteration implements ItemAlteration {
	@Override
	public void alterate(Item item) {
		item.quality = Math.min(item.quality + QUALITY_DAMAGE, QUALITY_MAX);
	}
}
