package com.gildedrose.alteration;

public class OutdatedAlterationDecorator extends AlterationMultipleDecorator {
	public OutdatedAlterationDecorator(ItemAlteration alteration) {
		super(alteration, 2);
	}
}
