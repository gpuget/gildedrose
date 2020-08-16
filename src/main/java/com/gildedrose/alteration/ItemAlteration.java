package com.gildedrose.alteration;

import com.gildedrose.Item;

public interface ItemAlteration {
	int QUALITY_DAMAGE = 1;
	int QUALITY_MIN = 0;
	int QUALITY_MAX = 50;

	void alterate(Item item);
}
