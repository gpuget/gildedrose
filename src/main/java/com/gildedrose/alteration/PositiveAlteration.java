package com.gildedrose.alteration;

import com.gildedrose.Item;

public class PositiveAlteration implements ItemAlteration {
	@Override
	public void alterate(Item item) {
		alterate(item, QUALITY_DAMAGE);
	}

	protected void alterate(Item item, int alterationValue) {
		item.quality = Math.min(item.quality + alterationValue, QUALITY_MAX);
	}
}
