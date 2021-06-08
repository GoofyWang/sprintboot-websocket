package com.dh.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRankingInfoVO {

	/**员工标识*/
	private Integer employeeId;

	/**总分*/
    private Integer erTotalScore = 0;

	/**总用时*/
    private Integer erTotalTime = 0; 

	/**排名*/
    private Integer rankNumber = 9999;
    
}
