package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {
	private static final int DEFAULT_ITEM_VALUE = 10;

	@Test
	public void givenNormalItem_whenUpdate_thenQualityDecreasedBy1() {
		TestBuilder.givenNormalItem().whenUpdate().thenQualityDecreasedBy(1);
	}

	@Test
	public void givenNormalItemAndZeroSellIn_whenUpdate_thenQualityDecreasedBy2() {
		TestBuilder.givenNormalItem().andZeroSellIn().whenUpdate().thenQualityDecreasedBy(2);
	}

	@Test
	public void givenNormalItemAndNegativeSellIn_whenUpdate_thenQualityDecreasedBy2() {
		TestBuilder.givenNormalItem().andNegativeSellIn().whenUpdate().thenQualityDecreasedBy(2);
	}

	@Test
	public void givenNormalItemAndZeroQuality_whenUpdate_thenQualityNotNegative() {
		TestBuilder.givenNormalItem().andQuality(0).whenUpdate().thenQualityNotNegative();
	}

	@Test
	public void givenPositiveAlterationItem_whenUpdate_thenQualityIncreasedBy1() {
		TestBuilder.givenPositiveAlterationItem().whenUpdate().thenQualityIncreasedBy(1);
	}

	@Test
	public void givenPositiveAlterationItemAndNegativeSellIn_whenUpdate_thenQualityIncreasedBy2() {
		TestBuilder.givenPositiveAlterationItem().andNegativeSellIn().whenUpdate().thenQualityIncreasedBy(2);
	}

	@Test
	public void givenPositiveAlterationItemAndQuality50_whenUpdate_thenQualityNotGreaterThan50() {
		TestBuilder.givenPositiveAlterationItem().andQuality(50).whenUpdate().thenQualityNotGreaterThan50();
	}

	@Test
	public void givenLegendaryItem_whenUpdate_thenItemNotChanged() {
		TestBuilder.givenLegendaryItem().whenUpdate().thenItemNotChanged();
	}

	@Test
	public void givenBackstagePassesItemAndSellInMoreThan10_whenUpdate_thenQualityIncreasedBy1() {
		TestBuilder.givenBackstagePassesItem().andSellIn(11).whenUpdate().thenQualityIncreasedBy(1);
	}

	@Test
	public void givenBackstagePassesItemAndSellIn10_whenUpdate_thenQualityIncreasedBy2() {
		TestBuilder.givenBackstagePassesItem().andSellIn(10).whenUpdate().thenQualityIncreasedBy(2);
	}

	@Test
	public void givenBackstagePassesItemAndSellInBetween5And10_whenUpdate_thenQualityIncreasedBy2() {
		TestBuilder.givenBackstagePassesItem().andSellIn(6).whenUpdate().thenQualityIncreasedBy(2);
	}

	@Test
	public void givenBackstagePassesItemAndSellIn5_whenUpdate_thenQualityIncreasedBy3() {
		TestBuilder.givenBackstagePassesItem().andSellIn(5).whenUpdate().thenQualityIncreasedBy(3);
	}

	@Test
	public void givenBackstagePassesItemAndSellInBetween0And5_whenUpdate_thenQualityIncreasedBy3() {
		TestBuilder.givenBackstagePassesItem().andSellIn(1).whenUpdate().thenQualityIncreasedBy(3);
	}

	@Test
	public void givenBackstagePassesItemAndSellIn0_whenUpdate_thenQualityZero() {
		TestBuilder.givenBackstagePassesItem().andZeroSellIn().whenUpdate().thenQualityZero();
	}

	@Test
	public void givenConjuredItem_whenUpdate_thenQualityDecreasedBy2() {
		TestBuilder.givenConjuredItem().whenUpdate().thenQualityDecreasedBy(2);
	}

	@Test
	public void givenConjuredItemAndNegativeSellIn_whenUpdate_thenQualityDecreasedBy2() {
		TestBuilder.givenConjuredItem().andNegativeSellIn().whenUpdate().thenQualityDecreasedBy(4);
	}

	private static class TestBuilder {
		private final String name;
		private int sellIn = DEFAULT_ITEM_VALUE;
		private int quality = DEFAULT_ITEM_VALUE;

		private Item result;

		private TestBuilder(String itemName) {
			this.name = itemName;
		}

		private static TestBuilder givenName(String value) {
			return new TestBuilder(value);
		}

		private static TestBuilder givenNormalItem() {
			return givenName("Normal item");
		}

		private static TestBuilder givenPositiveAlterationItem() {
			return givenName("Aged Brie");
		}

		private static TestBuilder givenLegendaryItem() {
			return givenName("Sulfuras, Hand of Ragnaros");
		}

		private static TestBuilder givenBackstagePassesItem() {
			return givenName("Backstage passes to a TAFKAL80ETC concert");
		}

		private static TestBuilder givenConjuredItem() {
			return givenName("Conjured Mana Cake");
		}

		private TestBuilder andSellIn(int value) {
			this.sellIn = value;
			return this;
		}

		private TestBuilder andNegativeSellIn() {
			return andSellIn(-1);
		}

		private TestBuilder andZeroSellIn() {
			return andSellIn(0);
		}

		private TestBuilder andQuality(int value) {
			this.quality = value;
			return this;
		}

		private TestBuilder whenUpdate() {
			Item given = new Item(this.name, this.sellIn, this.quality);
			GildedRose gildedRose = new GildedRose(new Item[]{given});

			gildedRose.updateQuality();

			this.result = gildedRose.items[0];
			return this;
		}

		private void thenNameNotChangedAndSellInDecreasedBy1() {
			assertThat(result.name).isEqualTo(this.name);
			assertThat(result.sellIn).isEqualTo(this.sellIn - 1);
		}

		private void thenQualityEqualsTo(int expected) {
			thenNameNotChangedAndSellInDecreasedBy1();
			assertThat(result.quality).isEqualTo(expected);
		}

		private void thenQualityZero() {
			thenNameNotChangedAndSellInDecreasedBy1();
			assertThat(result.quality).isZero();
		}

		private void thenQualityChanged(int alterationValue) {
			thenQualityEqualsTo(this.quality + alterationValue);
		}

		private void thenQualityIncreasedBy(int value) {
			thenQualityChanged(value);
		}

		private void thenQualityDecreasedBy(int value) {
			thenQualityChanged(-value);
		}

		private void thenQualityNotNegative() {
			thenNameNotChangedAndSellInDecreasedBy1();
			assertThat(result.quality).isNotNegative();
		}

		private void thenQualityNotGreaterThan50() {
			thenNameNotChangedAndSellInDecreasedBy1();
			assertThat(result.quality).isLessThanOrEqualTo(50);
		}

		private void thenItemNotChanged() {
			assertThat(result.name).isEqualTo(this.name);
			assertThat(result.sellIn).isEqualTo(this.sellIn);
			assertThat(result.quality).isEqualTo(this.quality);
		}
	}
}
