package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {
	private static final String NAME = "Normal Sword";

	@Test
	public void givenItem_whenUpdate_thenSellInAndQualityLower() {
		givenItem_whenUpdate_thenExpected(new Item(NAME, 1, 1), new Item(NAME, 0, 0));
	}

	@Test
	public void givenItemWithZeroQuality_whenUpdate_thenQualityNotNegative() {
		givenItem_whenUpdate_thenExpected(new Item(NAME, 0, 0), new Item(NAME, -1, 0));
	}

	@Test
	public void givenItemWithSellNegative_whenUpdate_thenQualityDecreasedBy2() {
		givenItem_whenUpdate_thenExpected(new Item(NAME, -1, 2), new Item(NAME, -2, 0));
	}

	@Test
	public void givenItemThatGain1QualityWithAge_whenUpdate_thenQualityHigher() {
		String name = "Aged Brie";
		givenItem_whenUpdate_thenExpected(new Item(name, 1, 0),
				new Item(name, 0, 1));
	}

	@Test
	public void givenItemThatGainQualityWithAge_whenUpdate_thenQualityNotMore50() {
		String name = "Aged Brie";
		givenItem_whenUpdate_thenExpected(new Item(name, 1, 50),
				new Item(name, 0, 50));
	}

	@Test
	public void givenLegendaryItem_whenUpdate_thenSellInAndQualityNotChanged() {
		String name = "Sulfuras, Hand of Ragnaros";
		givenItem_whenUpdate_thenExpected(new Item(name, 1, 1),
				new Item(name, 1, 1));
	}

	@Test
	public void givenItemThatGainMoreQualityWithAgeAndSellInHigherThan10_whenUpdate_thenQualityIncreasedBy1() {
		givenItemThatGainMoreQualityWithAgeAndSellIn_whenUpdate_thenQualityIncreasedBy(20, 1);
	}

	@Test
	public void givenItemThatGainMoreQualityWithAgeAndSellIn10_whenUpdate_thenQualityIncreasedBy2() {
		givenItemThatGainMoreQualityWithAgeAndSellIn_whenUpdate_thenQualityIncreasedBy(10, 2);
	}

	@Test
	public void givenItemThatGainMoreQualityWithAgeAndSellIn6_whenUpdate_thenQualityIncreasedBy2() {
		givenItemThatGainMoreQualityWithAgeAndSellIn_whenUpdate_thenQualityIncreasedBy(6, 2);
	}

	@Test
	public void givenItemThatGainMoreQualityWithAgeAndSellIn5_whenUpdate_thenQualityIncreasedBy3() {
		givenItemThatGainMoreQualityWithAgeAndSellIn_whenUpdate_thenQualityIncreasedBy(5, 3);
	}

	@Test
	public void givenItemThatGainMoreQualityWithAgeAndSellIn1_whenUpdate_thenQualityIncreasedBy3() {
		givenItemThatGainMoreQualityWithAgeAndSellIn_whenUpdate_thenQualityIncreasedBy(1, 3);
	}

	@Test
	public void givenItemThatGainMoreQualityWithAgeAndSellIn0_whenUpdate_thenQualityIncreasedBy3() {
		givenItemThatGainMoreQualityWithAgeAndSellIn_whenUpdate_thenQualityIncreasedBy(0, 0);
	}

	private static void givenItemThatGainMoreQualityWithAgeAndSellIn_whenUpdate_thenQualityIncreasedBy(int sellIn,
																									   int increasedBy) {
		String name = "Backstage passes to a TAFKAL80ETC concert";
		givenItem_whenUpdate_thenExpected(new Item(name, sellIn, 0),
				new Item(name, sellIn - 1, increasedBy));
	}

	private static void givenItem_whenUpdate_thenExpected(Item given, Item expected) {
		Item updated = performUpdate(given);

		assertThat(updated.name).isEqualTo(expected.name);
		assertThat(updated.sellIn).isEqualTo(expected.sellIn);
		assertThat(updated.quality).isEqualTo(expected.quality);
	}

	private static Item performUpdate(Item given) {
		GildedRose gildedRose = new GildedRose(new Item[]{given});

		gildedRose.updateQuality();

		return gildedRose.items[0];
	}
}
