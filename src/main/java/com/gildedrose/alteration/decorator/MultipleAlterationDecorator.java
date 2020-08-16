package com.gildedrose.alteration.decorator;

import com.gildedrose.Item;
import com.gildedrose.alteration.ItemAlteration;

public class MultipleAlterationDecorator implements ItemAlteration {
	private final ItemAlteration alteration;
	private final int multiplier;

	public MultipleAlterationDecorator(ItemAlteration alteration, int multiplier) {
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
