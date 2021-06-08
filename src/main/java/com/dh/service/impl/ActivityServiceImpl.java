package com.dh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dh.dao.ActivityEmployeeRecordMapper;
import com.dh.dao.ActivityItemBankMapper;
import com.dh.dao.ActivityMapper;
import com.dh.dao.ActivityRuleMapper;
import com.dh.dao.AnswerRecordMapper;
import com.dh.dao.DepartmentRankingMapper;
import com.dh.dao.EmployeeRankingMapper;
import com.dh.dao.EmployeeScoreRecordMapper;
import com.dh.model.Activity;
import com.dh.model.EmployeeRanking;
import com.dh.model.ActivityEmployeeRecord;
import com.dh.model.ActivityItemBank;
import com.dh.model.ActivityRule;
import com.dh.model.Answer;
import com.dh.model.EmployeeScoreRecord;
import com.dh.model.AnswerRecord;
import com.dh.model.DepartmentRanking;
import com.dh.model.dto.ActivityDelDTO;
import com.dh.model.dto.ActivityParamDTO;
import com.dh.model.dto.IBAnswerDTO;
import com.dh.model.dto.ItemBankAnswerDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.ActivityItemBankVO;
import com.dh.model.vo.ActivityVO;
import com.dh.model.vo.ItemBankAnswerResultVO;
import com.dh.service.ActivityService;
import com.dh.util.DateUtil;
import com.dh.util.SystemConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * 活动service
 */
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private ActivityItemBankMapper aibMapper;
	
	@Autowired
	private AnswerRecordMapper answerRecordMapper;
	
	@Autowired
	private ActivityEmployeeRecordMapper aerMapper;
	
	@Autowired
	private EmployeeScoreRecordMapper esrMapper;
	
	@Autowired
	private EmployeeRankingMapper empRankingMapper;
	
	@Autowired
	private DepartmentRankingMapper deptRankingMapper;
	
	@Autowired
	private ActivityRuleMapper arMapper;

	// 用户端获取活动列表
	@Override
	public List<ActivityVO> getActivityUserList(String nowTime) {
		List<ActivityVO> list = activityMapper.getActivityUserList(nowTime);
		if(list == null) list = new ArrayList<>();
		return list;
	}

	// 根据活动获取试题信息列表
	@Override
	public List<ActivityItemBankVO> getActivityItemBankList(Integer activityId) {
		List<ActivityItemBankVO> list = aibMapper.getActivityItemBankList(activityId);
		if(list == null) list = new ArrayList<>();
		return list;
	}

	// 提交答题信息
	@Override
	public ItemBankAnswerResultVO submitItemBankInfo(ItemBankAnswerDTO ibaDTO, Integer employeeId, Integer departmentId) {
		log.info("=====用户 {} 部门 {} 答题信息：{}", employeeId, departmentId, JSONObject.toJSONString(ibaDTO));
		try {
			ItemBankAnswerResultVO ibar = new ItemBankAnswerResultVO();
			ibar.setAnswerUseTime(ibaDTO.getAnswerUseTime());
			String nowTime = DateUtil.getDateTime();
			Map<Integer, Integer> ibScoreMap = new HashMap<>();
			Integer totalScore = activityScoreDispose(ibaDTO, ibScoreMap);
			Integer score = editAnswerRecord(employeeId, ibaDTO.getIbAnswerList(), nowTime, 
														ibaDTO.getActivityId(), ibScoreMap);
			String challengeStatus = insertActivityEmployeeRecordInfo(employeeId, score, totalScore, 
										ibaDTO.getAnswerUseTime(), nowTime, ibaDTO.getActivityId());
			ibar.setWinner(SystemConstants.N);
			ibar.setAddScore(0);
			if(SystemConstants.Y.equals(challengeStatus)){// 挑战成功
				insertEmployeeScoreRecordInfo(employeeId, score, nowTime, ibaDTO.getActivityId());
				editEmployeeRankingInfo(employeeId, score, ibaDTO.getAnswerUseTime(), nowTime);
				editDepartmentRankingInfo(departmentId, score, ibaDTO.getAnswerUseTime(), nowTime);
				String rankStatus = checkEmployeeRankingStatus(employeeId);
				ibar.setAddScore(score);
				ibar.setWinner(rankStatus);
			}
			ibar.setChallengeStatus(challengeStatus);
			Integer currScore = esrMapper.getEmployeeTotalScore(employeeId);
			if(currScore == null) currScore = 0;
			ibar.setCurrScore(currScore);
			return ibar;
		} catch (Exception e) {
			log.error("=======答题异常：", e);
		}
		return null;
	}

	/**
	 * 添加员工分数信息
	 * @param employeeId
	 * @param score
	 * @param nowTime
	 * @param activityId
	 */
	private void insertEmployeeScoreRecordInfo(Integer employeeId, Integer score, 
											   String nowTime, Integer activityId){
		Activity activity = activityMapper.selectByPrimaryKey(activityId);
		EmployeeScoreRecord esr = new EmployeeScoreRecord();
		esr.setEmployeeId(employeeId);
		esr.setCreateTime(nowTime);
		esr.setEsrScore(score);
		esr.setEsrTypeName(activity.getActivityName());
		esr.setStatus(SystemConstants.Y);
		int res = esrMapper.insert(esr);
		if(res > 0){
			log.info("=====添加员工分数记录成功====");
		}
	}
	
	/**
	 * 添加活动员工记录信息
	 * @param employeeId  员工标识
	 * @param score       分数
	 * @param useTime     用时
	 * @param nowTime     当前时间
	 * @param activityId  活动标识
	 * @return
	 */
	private String insertActivityEmployeeRecordInfo(Integer employeeId, Integer score, int totalScore, 
			 						Integer useTime, String nowTime, Integer activityId){
		ActivityEmployeeRecord aer = new ActivityEmployeeRecord();
		aer.setActivityId(activityId);
		aer.setAerAnswerTime(useTime);
		aer.setAerScore(score);
		aer.setEmployeeId(employeeId);
		aer.setCreateTime(nowTime);
		aer.setAerAnswerDate(nowTime.substring(0, 10));
		String challengeStatus = SystemConstants.N;
		List<ActivityRule> arList = arMapper.getActivityRuleList(activityId);
		if(arList == null || arList.size() == 0){
			totalScore = totalScore * 60 / 100;
			if(score >= totalScore){
				challengeStatus = SystemConstants.Y; 
			}
		}else{
			for(ActivityRule ar : arList){
				if("1".equals(ar.getActivityRuleType())){// 得分比
					totalScore = totalScore * Integer.valueOf(ar.getActivityRuleContent()) / 100;
					if(score >= totalScore){
						challengeStatus = SystemConstants.Y; 
					}else{
						challengeStatus = SystemConstants.N;
					}
				}else if("2".equals(ar.getActivityRuleType())){// 时间
					if(useTime >= Integer.valueOf(ar.getActivityRuleContent())){
						challengeStatus = SystemConstants.Y;
					}else{
						challengeStatus = SystemConstants.N;
					}
				}
			}
		}
		aer.setAerChallengeStatus(challengeStatus);
		int res = aerMapper.insert(aer);
		if(res > 0){
			log.info("=====添加活动员工记录成功=====");
		}
		return challengeStatus;
	}
	
	/**
	 * 活动试题分数处理
	 * @param ibaDTO  活动标识
	 * @param ibScoreMap  试题分数Map
	 * @return  总分
	 */
	private Integer activityScoreDispose(ItemBankAnswerDTO ibaDTO, Map<Integer, Integer> ibScoreMap){
		List<ActivityItemBank> list = aibMapper.getActivityItemBankListByActivityId(ibaDTO.getActivityId());
		if(list == null || list.size() == 0){
			return 0; 
		}
		int totalScore = 0;
		List<Integer> collect = ibaDTO.getIbAnswerList().stream()
									  .map(IBAnswerDTO::getItemBankId).collect(Collectors.toList());
		for(Integer ibId : collect){
			for(ActivityItemBank aib : list){
				if(ibId.equals(aib.getItemBankId())){
					totalScore += aib.getAibScore();
					ibScoreMap.put(aib.getItemBankId(), aib.getAibScore());
					break;
				}
			}
		}
		return totalScore;
	}
	
	/**
	 * 编辑排行榜信息
	 * @param employeeId 员工标识
	 * @param score      分数
	 * @param useTime    用时
	 * @param nowTime    当前时间
	 */
	private void editEmployeeRankingInfo(Integer employeeId, Integer score, 
										 Integer useTime, String nowTime){
		EmployeeRanking er = empRankingMapper.getEmployeeRankingInfoByEmployeeId(employeeId);
		if(er == null){
			er = new EmployeeRanking();
			er.setEmployeeId(employeeId);
			er.setErTotalScore(score);
			er.setErTotalTime(useTime);
			er.setCreateTime(nowTime);
			er.setUpdateTime(nowTime);
			er.setStatus(SystemConstants.Y);
			int res = empRankingMapper.insert(er);
			if(res > 0){
				log.info("======添加员工排行榜信息成功=====");
			}
		}else{
			er.setErTotalScore(er.getErTotalScore() + score);
			er.setErTotalTime(er.getErTotalTime() + useTime);
			er.setUpdateTime(nowTime);
			int res = empRankingMapper.updateByPrimaryKey(er);
			if(res > 0){
				log.info("======修改员工排行榜信息成功=====");
			}
		}
	}
	
	/**
	 * 编辑部门排行榜信息
	 * @param departmentId 部门标识
	 * @param score      分数
	 * @param useTime    用时
	 * @param nowTime    当前时间
	 */
	private void editDepartmentRankingInfo(Integer departmentId, Integer score, 
										Integer useTime, String nowTime){
		DepartmentRanking dr = deptRankingMapper.getDepartmentRankingInfoByDepartmentId(departmentId);
		if(dr == null){
			dr = new DepartmentRanking();
			dr.setDepartmentId(departmentId);
			dr.setDrTotalScore(score);
			dr.setDrTotalTime(useTime);
			dr.setCreateTime(nowTime);
			dr.setUpdateTime(nowTime);
			dr.setStatus(SystemConstants.Y);
			int res = deptRankingMapper.insert(dr);
			if(res > 0){
				log.info("======添加部门排行榜信息成功=====");
			}
		}else{
			dr.setDrTotalScore(dr.getDrTotalScore() + score);
			dr.setDrTotalTime(dr.getDrTotalTime() + useTime);
			dr.setUpdateTime(nowTime);
			int res = deptRankingMapper.updateByPrimaryKey(dr);
			if(res > 0){
				log.info("======修改部门排行榜信息成功=====");
			}
		}
	}
	
	/**
	 * 编辑答题记录
	 * @param employeeId   员工标识
	 * @param ibAnswerList 答题信息list
	 * @param activityId   活动标识
	 * @param ibScoreMap   试题分数map
	 * @return  得分
	 */
	private Integer editAnswerRecord(Integer employeeId, List<IBAnswerDTO> ibAnswerList, String nowTime,
									 Integer activityId, Map<Integer, Integer> ibScoreMap){
		List<ActivityItemBankVO> list = aibMapper.getActivityItemBankRealAnswerList(activityId);
		if(list == null || list.size() == 0){
			return 0;
		}
		int score = 0;
		List<AnswerRecord> arList = new ArrayList<>();
		AnswerRecord ar = null;
		for(IBAnswerDTO ibAnswer : ibAnswerList){
			for(ActivityItemBankVO aib : list){
				if(ibAnswer.getItemBankId().equals(aib.getItemBankId())){// 同一题
					ar = new AnswerRecord();
					ar.setArEmployeeAnwer(JSONObject.toJSONString(ibAnswer.getAnswerIdList()));
					ar.setArNumber(1);
					List<Integer> realAlist = aib.getAnswerList().stream()
												.filter(a -> SystemConstants.Y.equals(a.getAnswerStatus()))
												.map(Answer::getAnswerId).collect(Collectors.toList());
					ar.setArRealAnswer(JSONObject.toJSONString(realAlist));
					ar.setCreateTime(nowTime);
					ar.setEmployeeId(employeeId);
					ar.setItemBankId(aib.getItemBankId());
					ar.setUpdateTime(nowTime);
					ar.setStatus(SystemConstants.Y);
					boolean flag = checkAnswer(ibAnswer.getAnswerIdList(), aib.getAnswerList());
					ar.setArStatus(SystemConstants.N);
					if(flag){
						ar.setArStatus(SystemConstants.Y);
						score += ibScoreMap.get(aib.getItemBankId());
					}
					arList.add(ar);
				}
			}
		}
		// 添加答题记录
		insertAnswerRecord(employeeId, arList);
		return score;
	}

	/**
	 * 添加答题记录
	 * @param employeeId  答题标识
	 * @param arList      答题记录列表
	 */
	private void insertAnswerRecord(Integer employeeId, List<AnswerRecord> arList) {
		List<Integer> ibIdList = arList.stream().map(AnswerRecord::getItemBankId).collect(Collectors.toList());
		List<AnswerRecord> list = answerRecordMapper.getAnswerRecordList(employeeId, ibIdList);
		List<Integer> arIdDelList = new ArrayList<>();
		for(AnswerRecord ar : arList){
			for(AnswerRecord ar1 : list){
				if(ar.getItemBankId().equals(ar1.getItemBankId())){// 存在相同数据
					ar.setArNumber(ar.getArNumber() + ar1.getArNumber());
					arIdDelList.add(ar1.getArId());
					ar.setCreateTime(ar1.getCreateTime());
				}
			}
		}
		if(arIdDelList.size() > 0)
		answerRecordMapper.deleteAnswerRecordByIds(arIdDelList);
		int res = answerRecordMapper.insertAnswerRecord(arList);
		if(res > 0){
			log.info("====添加答题记录成功====");
		}
	}

	/**
	 * 校验答案是否正确
	 * @param answerIdList 员工答案标识
	 * @param answerList   正确答案标识
	 * @return
	 */
	private boolean checkAnswer(List<Integer> answerIdList, List<Answer> answerList) {
		boolean flag = false;
		if(answerIdList.size() == answerList.size()){
			for(Integer aid : answerIdList){
				boolean f = false;
				for(Answer answer : answerList){
					if(aid.equals(answer.getAnswerId())){
						f = true;
						break;
					}
				}
				if(!f){
					break;
				}else{
					flag = true;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 检测员工是不是擂主
	 * @param employeeId  员工标识
	 * @return
	 */
	public String checkEmployeeRankingStatus(Integer employeeId){
		List<Map<String, Integer>> list = esrMapper.getEmployeeScoreProfileList();
		if(list == null || list.size() == 0){
			log.info("=====暂无员工积分记录=====");
			return SystemConstants.N;
		}
		for(Map<String, Integer> map : list){
			if(employeeId.equals(map.get("employeeId"))){
				return SystemConstants.Y;
			}
		}
		return SystemConstants.N;
	}

	// 获取当天员工答题次数
	@Override
	public Integer getEmployeeAnswerCount(Integer employeeId) {
		Integer count = aerMapper.getEmployeeAnswerCount(employeeId, DateUtil.getDate());
		return count == null ? 0 : count;
	}

	// 获取活动详情
	@Override
	public ActivityVO getActivityInfo(Integer activityId) {
		return activityMapper.getActivityInfo(activityId);
	}

	// 获取活动列表
	@Override
	public List<ActivityVO> getActivityList(ActivityParamDTO activityParm, PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<ActivityVO> list = activityMapper.getActivityList(activityParm);
		if(list == null || list.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		PageInfo<ActivityVO> pageInfo = new PageInfo<>(list);
		pageDTO.setTotleCount(pageInfo.getTotal());
		return list;
	}

	// 添加活动信息
	@Override
	public boolean insertActivityInfo(Activity activity) {
		log.info("=========添加活动信息：" + JSONObject.toJSONString(activity));
		try {
			int res = activityMapper.insertSelective(activity);
			if(res > 0){
				log.info("=========添加活动信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====添加活动信息异常：", e);
		}
		return false;
	}

	// 修改活动信息
	@Override
	public boolean updateActivityInfo(Activity activity) {
		log.info("=========添加活动信息：" + JSONObject.toJSONString(activity));
		try {
			int res = activityMapper.updateByPrimaryKeySelective(activity);
			if(res > 0){
				log.info("=========添加活动信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====添加活动信息异常：", e);
		}
		return false;
	}

	// 删除活动信息
	@Override
	public boolean deleteActivityInfo(ActivityDelDTO activityDel) {
		log.info("=========删除活动信息：" + JSONObject.toJSONString(activityDel));
		try {
			int res = activityMapper.deleteActivityInfo(activityDel);
			if(res > 0){
				log.info("=========删除活动信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====删除活动信息异常：", e);
		}
		return false;
	}
	
}
