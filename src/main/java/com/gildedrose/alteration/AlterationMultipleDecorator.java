package com.gildedrose.alteration;

import com.gildedrose.Item;

public class AlterationMultipleDecorator implements ItemAlteration {
	private final ItemAlteration alteration;
	private final int multiplier;

	public AlterationMultipleDecorator(ItemAlteration alteration, int multiplier) {
		this.alteration = alteration;
		this.multiplier = multiplier;
	}

	@Override
	public void alterate(Item item) {
		for (int i = 0; i < this.multiplier; i++) {
			this.alteration.alterate(item);
		}
	}
}
