package com.dh.dao;

import com.dh.model.MatchLog;

import java.util.List;

public interface MatchLogMapper {

    int createMatch(MatchLog matchLog);

    MatchLog getMatch(Integer matchId);

    void increaseSubmitNum(Integer matchId);

    List<MatchLog> getMatchShouldCompleted(Integer Limit);

    void finishMatch(Integer matchId);

}