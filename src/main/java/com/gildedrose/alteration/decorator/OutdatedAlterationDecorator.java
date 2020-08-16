package com.gildedrose.alteration.decorator;

import com.gildedrose.alteration.ItemAlteration;
import com.gildedrose.alteration.decorator.MultipleAlterationDecorator;

public class OutdatedAlterationDecorator extends MultipleAlterationDecorator {
	public OutdatedAlterationDecorator(ItemAlteration alteration) {
		super(alteration, 2);
	}
}
