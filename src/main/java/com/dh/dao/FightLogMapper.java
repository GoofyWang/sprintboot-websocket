package com.dh.dao;

import com.dh.model.FightLog;
import java.util.List;

public interface FightLogMapper {

    int createFightLog(FightLog fightLog);

    List<FightLog> getFightLogByMatchId(Integer matchId);

}