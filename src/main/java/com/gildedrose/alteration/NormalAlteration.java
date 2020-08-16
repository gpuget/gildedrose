package com.gildedrose.alteration;

import com.gildedrose.Item;

public class NormalAlteration implements ItemAlteration {
	@Override
	public void alterate(Item item) {
		item.quality = Math.max(item.quality - QUALITY_DAMAGE, QUALITY_MIN);
	}
}
