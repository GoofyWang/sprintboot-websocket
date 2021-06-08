package com.dh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.dh.model.MatchLog;
import com.dh.service.RoomService;
import com.dh.util.JSONUtils;
import com.dh.util.RedisUtil;
import com.dh.model.dto.ItemFightAnswerDTO;
import com.dh.model.Employee;
import com.dh.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import javax.annotation.Resource;

/**
 * websocket
 * 消息推送(个人和广播)
 */

@RestController
@RequestMapping(value = "/room/")
@Api(tags = {"2用户端接口","试题信息"}, description = " ")
public class RoomController {

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private RoomService roomService;

    @Autowired
    private EmployeeService employeeService;

    private Employee verifyUser(HttpServletRequest request){
        if(request.getHeader("token") == null){
            return new Employee();
        }

//        Employee emp = (Employee) redisUtil.get(request.getHeader("token"));
        Employee emp = employeeService.getEmployeeInfoByToken(request.getHeader("token"));
        if(  emp == null || emp.getEmployeeId() == null || emp.getEmployeeId() < 1){
            return new Employee();
        }
        return emp;
    }

    @RequestMapping(value = "status")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject getRoomStatus(@RequestBody Map<String, Integer> param,
                           HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.webGetRoomStatus(param.get("roomId"));
        return jo;
    }


    @RequestMapping(value = "create")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject create(@RequestBody Map<String, Integer> param,
                                     HttpServletRequest request){
        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.webCreateRoom(emp,param.get("type"));
        return jo;
    }

    @RequestMapping(value = "join")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject join(@RequestBody Map<String, Integer> param,
                             HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.webJoinRoom(emp,param.get("roomId"));
        return jo;
    }

    @RequestMapping(value = "leave")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject leave(@RequestBody Map<String, Integer> param,
                             HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.webLeaveRoom(emp.getEmployeeId(),param.get("roomId"));
        return jo;
    }


    @RequestMapping(value = "kick")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject kick(@RequestBody Map<String, Integer> param,
                            HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.webKick(emp.getEmployeeId(),param.get("roomId"), param.get("kickId"));
        return jo;
    }

    @RequestMapping(value = "match")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject match(@RequestBody Map<String, Integer> param,
                             HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.webMatch(emp.getEmployeeId(),param.get("roomId"));
        return jo;
    }

    @RequestMapping(value = "cancel_match")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject cancelMatch(@RequestBody Map<String, Integer> param,
                             HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.webCancelMatch(emp.getEmployeeId(),param.get("roomId"));
        return jo;
    }



    @RequestMapping(value = "bank/list")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject bankList(@RequestBody Map<String, Integer> param,
                                   HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.getBankList(emp.getEmployeeId(),param.get("roomId"),param.get("matchId"));
        return jo;
    }

    @RequestMapping(value = "bank/answer")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject bankAnswer(@RequestBody ItemFightAnswerDTO ifaDTO,
                               HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.bankAnswer(emp.getEmployeeId(),ifaDTO);
        return jo;

//        JSONObject jo= roomService.bankAnswer(id, ibaDTO);
//        return jo;
    }

    @RequestMapping(value = "match/complete")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject bankAnswer(@RequestBody Map<String, Integer> param,
                                 HttpServletRequest request){

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        MatchLog matchLog = new MatchLog();
        matchLog.setMatchId(param.get("matchId"));
        roomService.matchComplete(matchLog,false);

        JSONObject jo = JSONUtils.WebResult("");
        return jo;

//        JSONObject jo= roomService.bankAnswer(id, ibaDTO);
//        return jo;
    }


    @RequestMapping(value = "rank")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject rank(@RequestBody Map<String, Integer> param,
                                 HttpServletRequest request) {


        JSONObject jo= roomService.getRank();

        return jo;

    }

    @RequestMapping(value = "rank/self")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    public JSONObject rankSelf(@RequestBody Map<String, Integer> param,
                           HttpServletRequest request) {

        Employee emp = verifyUser(request);

        if(emp.getEmployeeId() == null){
            return JSONUtils.VerifyErrorResult();
        }

        JSONObject jo= roomService.getSelfRank(emp);

        return jo;

    }

}
