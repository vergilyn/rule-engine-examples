package com.vergilyn.examples.ruleengine.easyrules.eg1002;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

/**
 * VTODO 2021-05-20 >>>> 没想到实际场景。
 * @author vergilyn
 * @since 2021-05-20
 */
class IfDynamicConditionTests {

	@Test
	void traditional(){

	}

	@Data
	@NoArgsConstructor
	public static class ParamDto {
		private VipTypeEnum vip;
		private Integer level;

		public ParamDto(VipTypeEnum vip, Integer level) {
			this.vip = vip;
			this.level = level;
		}
	}

	public static enum VipTypeEnum{
		NORMAL_VIP(1, "普通会员"),
		SILVER_VIP(3, "白银会员"),
		GOLD_VIP(5, "黄金会员"),
		PLATINUM_VIP(10, "钻石会员");

		public final Integer capacity;
		public final String desc;

		VipTypeEnum(Integer capacity, String desc) {
			this.capacity = capacity;
			this.desc = desc;
		}
	}
}
