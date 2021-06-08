package com.dh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dh.dao.ActivityEmployeeRecordMapper;
import com.dh.dao.AnswerMapper;
import com.dh.dao.AnswerRecordMapper;
import com.dh.dao.DepartmentMapper;
import com.dh.dao.EmployeeLoginMapper;
import com.dh.dao.EmployeeMapper;
import com.dh.dao.EmployeeRankingMapper;
import com.dh.dao.EmployeeScoreRecordMapper;
import com.dh.model.ActivityEmployeeRecord;
import com.dh.model.Answer;
import com.dh.model.AnswerRecord;
import com.dh.model.Employee;
import com.dh.model.EmployeeLogin;
import com.dh.model.dto.EmployeeListDTO;
import com.dh.model.dto.MobileParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.DepartmentInfoVO;
import com.dh.model.vo.EmployeeFalseItemBankVO;
import com.dh.model.vo.EmployeeListVO;
import com.dh.model.vo.EmployeeRankingInfoVO;
import com.dh.model.vo.EmployeeRankingVO;
import com.dh.model.vo.EmployeeScoreRecordVO;
import com.dh.model.vo.EmployeeUserVO;
import com.dh.service.EmployeeService;
import com.dh.util.StringUtils;
import com.dh.util.SystemConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.security.spec.AlgorithmParameterSpec;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

/**
 * 员工service
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private EmployeeLoginMapper empLoginMapper;
	
	@Autowired
	private AnswerRecordMapper answerRecordMapper;
	
	@Autowired
	private EmployeeScoreRecordMapper esrMapper;
	
	@Autowired
	private ActivityEmployeeRecordMapper aerMapper;
	
	@Autowired
	private AnswerMapper answerMapper;
	
	@Autowired
	private EmployeeRankingMapper empRankMapper;
	
	@Autowired
	private DepartmentMapper deptMapper;

	// 根据主键查询用户信息
	@Override
	public EmployeeUserVO getEmployeeInfoById(Integer employeeId) {
		EmployeeUserVO employeeInfo = employeeMapper.getEmployeeInfoById(employeeId);
		if(employeeInfo == null){
			return new EmployeeUserVO();
		}
		List<AnswerRecord> list = answerRecordMapper.getAnswerRecordByEmployeeId(employeeId);
		Integer totalScore = esrMapper.getEmployeeTotalScore(employeeInfo.getEmployeeId());
		if(totalScore == null) totalScore = 0;
		int trueCount = 0, falseCount = 0;
		if(list != null && list.size() > 0){
			for(AnswerRecord ar : list){
				if(SystemConstants.Y.equals(ar.getArStatus())){
					trueCount += 1;
				}else {
					falseCount += 1;
				}
			}
		}
		employeeInfo.setEmployeeAnswerTotalCount(trueCount + falseCount);
		employeeInfo.setEmployeeAnswerTrueCount(trueCount);
		employeeInfo.setEmployeeAnswerFalseCount(falseCount);
		employeeInfo.setEmployeeTotalScore(totalScore);
		employeeInfo.setEmployeeWinRate(getWinRate(employeeInfo.getEmployeeId()));
		return employeeInfo;
	}
	
	/**
	 * 获取胜率
	 * @param employeeId 员工id
	 * @return
	 */
	private String getWinRate(Integer employeeId){
		List<ActivityEmployeeRecord> aerList = aerMapper.getActivityEmployeeRecordList(employeeId);
		if(aerList == null || aerList.size() == 0){
			return "0.00%";
		}
		int winTotal = 0, win = 0;
		for(ActivityEmployeeRecord aer : aerList){
			winTotal += 1;
			if(SystemConstants.Y.equals(aer.getAerChallengeStatus())){
				win += 1;
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数  
		return df.format((float)win / winTotal) + "%";
	}

	// 根据openid获取用户信息
	@Override
	public Employee getEmployeeInfoByOpenid(String openid) {
		return employeeMapper.getEmployeeInfoByOpenid(openid);
	}

	// 根据token获取用户信息
	@Override
	public Employee getEmployeeInfoByToken(String token) {
		return employeeMapper.getEmployeeInfoByToken(token);
	}

	// 获取手机号
	@Override
	public String getUserMobile(MobileParamDTO mobileDTO, String empSessionKey) {
		if(StringUtils.isEmptyString(empSessionKey)) return null;
		byte[] sessionKey = Base64Utils.decodeFromString(empSessionKey);
		byte[] encryptedData = Base64Utils.decodeFromString(mobileDTO.getEncryptedData());
		byte[] iv = Base64Utils.decodeFromString(mobileDTO.getIv());
		String mobile = null;
		String resultString = null;
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		SecretKeySpec keySpec = new SecretKeySpec(sessionKey, "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);// 设置为解密模式
			resultString = new String(cipher.doFinal(encryptedData), "UTF-8");
			JSONObject object = JSONObject.parseObject(resultString);
			mobile = object.getString("phoneNumber");
		} catch (Exception e) {
			log.error("第一次解密失败：", e);
			try {
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
	            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
	            resultString = new String(cipher.doFinal(encryptedData), "UTF-8");
	            JSONObject object = JSONObject.parseObject(resultString);
				mobile = object.getString("phoneNumber");
			} catch (Exception e2) {
				log.error("解密失败：", e2);
			}
		}
		return mobile;
	}

	// 添加员工信息
	@Override
	public boolean insertEmployeeInfo(Employee employee, EmployeeLogin empLogin) {
		log.info("======添加员工信息======" + JSONObject.toJSONString(employee));
		try {
			int res = employeeMapper.insertSelective(employee);
			if(res > 0){
				empLogin.setEmployeeId(employee.getEmployeeId());
				empLogin.setCreateTime(empLogin.getUpdateTime());
				insertEmployeeLoginInfo(empLogin);
				log.info("======添加员工信息成功======");
				return true;
			}
		} catch (Exception e) {
			log.info("======添加员工信息失败:", e);
		}
		return false;
	}

	/**
	 * 添加员工登录信息
	 * @param empLogin
	 */
	private void insertEmployeeLoginInfo(EmployeeLogin empLogin) {
		log.info("======添加员工登录信息======" + JSONObject.toJSONString(empLogin));
		try {
			int res = empLoginMapper.insertSelective(empLogin);
			if(res > 0){
				log.info("======添加员工登录信息成功======");
			}
		} catch (Exception e) {
			log.info("======添加员工登录信息失败:", e);
		}
	}

	// 修改员工信息
	@Override
	public boolean updateEmployeeInfo(Employee employee, EmployeeLogin empLogin) {
		log.info("======修改员工信息======" + JSONObject.toJSONString(employee));
		try {
			int res = employeeMapper.updateByPrimaryKeySelective(employee);
			if(res > 0){
				if(empLogin != null) updateEmployeeLoginInfo(empLogin);
				log.info("======修改员工信息成功======");
				return true;
			}
		} catch (Exception e) {
			log.info("======修改员工信息失败:", e);
		}
		return false;
	}

	// 修改登录信息
	@Override
	public boolean updateEmployeeLoginInfo(EmployeeLogin empLogin) {
		log.info("======修改员工登录信息======" + JSONObject.toJSONString(empLogin));
		try {
			int res = empLoginMapper.updateByPrimaryKeySelective(empLogin);
			if(res > 0){
				log.info("======修改员工登录信息成功======");
				return true;
			}
		} catch (Exception e) {
			log.info("======修改员工登录信息失败:", e);
		}
		return false;
	}

	// 获取员工积分列表
	@Override
	public List<EmployeeScoreRecordVO> getEmployeeScoreRecordList(Integer employeeId, PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<EmployeeScoreRecordVO> list = esrMapper.getEmployeeScoreRecordList(employeeId);
		if(list == null || list.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		PageInfo<EmployeeScoreRecordVO> pageInfo = new PageInfo<>(list);
		pageDTO.setTotleCount(pageInfo.getTotal());
		return list;
	}

	// 分页获取员工错题信息
	@Override
	public List<EmployeeFalseItemBankVO> getEmployeeFalseItemBankList(PageInfoDTO pageDTO, Integer employeeId) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<EmployeeFalseItemBankVO> list = answerRecordMapper.getEmployeeFalseItemBankList(employeeId);
		if(list == null || list.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		PageInfo<EmployeeFalseItemBankVO> pageInfo = new PageInfo<>(list);
		pageDTO.setTotleCount(pageInfo.getTotal());
		List<Integer> ibIdList = list.stream().map(EmployeeFalseItemBankVO::getItemBankId).collect(Collectors.toList());
		List<Answer> answerList = answerMapper.getAnswerListByItembankIds(ibIdList);
		Map<Integer, List<Answer>> ibAnswerMap = answerList.stream().collect(Collectors.groupingBy(Answer::getItemBankId, Collectors.toList()));
		list.forEach(efib -> efib.setAnswerList(ibAnswerMap.get(efib.getItemBankId())));
		return list;
	}

	// 获取员工排名信息
	@Override
	public EmployeeRankingVO getEmployeeRankingInfo(Integer employeeId) {
		EmployeeRankingInfoVO empRankInfo = empRankMapper.getEmployeeCurrRankingByEmployeeId(employeeId);
		if(empRankInfo == null){
			empRankInfo = new EmployeeRankingInfoVO();
		}
		EmployeeRankingVO empRank = employeeMapper.getEmployeeRankingInfoById(employeeId);
		if(empRank == null){
			empRank = new EmployeeRankingVO();
			empRank.setEmployeeId(employeeId);
		}else{
			DepartmentInfoVO departmentInfo = deptMapper.getDepartmentInfo(empRank.getDepartmentParentId());
			if(departmentInfo != null){
				empRank.setDepartmentParentName(departmentInfo.getDepartmentName());
			}
		}
		empRank.setEmployeeTotalScore(empRankInfo.getErTotalScore());
		empRank.setEmployeeTotalUseTime(empRankInfo.getErTotalTime());
		empRank.setEmployeeRankingNumber(empRankInfo.getRankNumber());
		return empRank;
	}
	
	// 获取员工排名列表
	@Override
	public List<EmployeeRankingVO> getEmployeeRankingList(PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<EmployeeRankingInfoVO> empRankList = empRankMapper.getEmployeeRankingList();
		if(empRankList == null || empRankList.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		List<Integer> empIdList = new ArrayList<>();
		Map<Integer, EmployeeRankingInfoVO> empRankMap = empRankInfoListDispose(empRankList, empIdList);
		List<EmployeeRankingVO> list = employeeMapper.getEmployeeRankingListByIds(empIdList);
		list = packageEmployeeRankingVO(empIdList, list);
		List<Integer> deptIdList = list.stream().map(EmployeeRankingVO::getDepartmentParentId)
										.distinct().collect(Collectors.toList());
		Map<Integer, String> deptMap = new HashMap<>();
		if(deptIdList != null && deptIdList.size() > 0){
			List<DepartmentInfoVO> departmentList = deptMapper.getDepartmentListByIds(deptIdList);
			if(departmentList != null && departmentList.size() > 0){
				departmentList.forEach(d -> deptMap.put(d.getDepartmentId(), d.getDepartmentName()));
			}
		}
		PageInfo<EmployeeRankingInfoVO> pageInfo = new PageInfo<>(empRankList);
		pageDTO.setTotleCount(pageInfo.getTotal());
		list.forEach(er -> {
			EmployeeRankingInfoVO erInfo = empRankMap.get(er.getEmployeeId());
			er.setEmployeeTotalScore(erInfo.getErTotalScore());
			er.setEmployeeTotalUseTime(erInfo.getErTotalTime());
			er.setEmployeeRankingNumber(erInfo.getRankNumber());
			er.setDepartmentParentName(deptMap.get(er.getDepartmentParentId()));
		});
		return list;
	}

	/**
	 * 处理排名信息(临时处理)
	 * @param empIdList
	 * @param list
	 * @return
	 */
	private List<EmployeeRankingVO> packageEmployeeRankingVO(List<Integer> empIdList, List<EmployeeRankingVO> list) {
		List<EmployeeRankingVO> erList = new ArrayList<>();
		for(Integer empId : empIdList){
			for(EmployeeRankingVO er : list){
				if(empId.equals(er.getEmployeeId())){
					erList.add(er);
				}
			}
		}
		return erList;
	}

	/**
	 * 处理员工排名信息
	 * @param empRankList
	 * @param empIdList
	 * @return
	 */
	private Map<Integer, EmployeeRankingInfoVO> empRankInfoListDispose(List<EmployeeRankingInfoVO> empRankList,
								List<Integer> empIdList) {
		Map<Integer, EmployeeRankingInfoVO> map = new HashMap<>();
		for(EmployeeRankingInfoVO empRank : empRankList){
			empIdList.add(empRank.getEmployeeId());
			map.put(empRank.getEmployeeId(), empRank);
		}
		return map;
	}

	// 根据部门获取员工列表
	@Override
	public List<EmployeeListVO> getDepartmentEmployeeList(EmployeeListDTO paramDTO, PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage(), pageDTO.getPageSize());
		List<EmployeeListVO> list = employeeMapper.getDepartmentEmployeeList(paramDTO);
		if(list == null || list.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		PageInfo<EmployeeListVO> pageInfo = new PageInfo<>(list);
		pageDTO.setTotleCount(pageInfo.getTotal());
		return list;
	}

	// 修改员工隔离状态
	@Override
	public boolean quarantineStatusEdit(Employee employee) {
		log.info("======修改员工隔离状态 {} {} ", employee.getEmployeeId(), employee.getEmployeeQuarantineStatus());
		try {
			int res = employeeMapper.quarantineStatusEdit(employee);
			if(res > 0){
				log.info("======修改员工隔离状态 ======");
				return true;
			}
		} catch (Exception e) {
			log.info("======修改员工隔离状态失败:", e);
		}
		return false;
	}
	
	
	

}
