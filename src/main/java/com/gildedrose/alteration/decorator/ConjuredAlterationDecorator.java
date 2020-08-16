package com.gildedrose.alteration.decorator;

import com.gildedrose.alteration.ItemAlteration;

public class ConjuredAlterationDecorator extends MultipleAlterationDecorator {
	public ConjuredAlterationDecorator(ItemAlteration alteration) {
		super(alteration, 2);
	}
}
