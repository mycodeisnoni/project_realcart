package com.ssafy.realcart.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.realcart.data.dao.inter.IGameDAO;
import com.ssafy.realcart.data.dao.inter.IPlayDAO;
import com.ssafy.realcart.data.dao.inter.IRecordDAO;
import com.ssafy.realcart.data.dao.inter.IUserDAO;
import com.ssafy.realcart.data.dto.PlayDto;
import com.ssafy.realcart.data.entity.Game;
import com.ssafy.realcart.data.entity.Play;
import com.ssafy.realcart.data.entity.Record;
import com.ssafy.realcart.data.entity.User;
import com.ssafy.realcart.service.inter.IGameService;
@Service
public class GameService implements IGameService{

	private final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
	private String[] currentUsers = new String[2];
	private String[] waitingUsers = new String[2];
	private long timeLimit = -1;
	private int recent = -1;
	
	private IUserDAO userDAO;
	private IGameDAO gameDAO;
	private IPlayDAO playDAO;
	private IRecordDAO recordDAO;
	private final Logger LOGGER = LoggerFactory.getLogger(GameService.class);
	
	@Autowired
    public GameService(IUserDAO userDAO, IGameDAO gameDAO, IPlayDAO playDAO, IRecordDAO recordDAO){
        this.userDAO = userDAO;
        this.gameDAO = gameDAO;
        this.playDAO = playDAO;
        this.recordDAO = recordDAO;
    }

	@Override
	public int participateGame(String nickname) {
		LOGGER.info("1");
		if(timeLimit != -1) { // 미리 예약된 유저가 있을 수 있다. (있다면 시간 내에 들어와야한다.)
			Long currentTime = System.currentTimeMillis();
			if(currentTime - timeLimit > 30000) { // 제한 시간이 지나버렸다면 더 이상 우선 순위자가 아님
				Arrays.fill(waitingUsers, null);
				int index = 0;
				int num = 1;
				for (int i = 0; i < currentUsers.length; i++) {
					if(currentUsers[i] != null) num--;
				}
				int size = queue.size();
				while(size-- > 0) {
					if(index > num) break;
					waitingUsers[index++] = queue.poll();
					timeLimit = System.currentTimeMillis(); // 시간제한이 다시 생김
				}
			}
		}
		//CurrentUsers에 있는 이용자라면 -100을 리턴
		for (String string : currentUsers) {
			if(nickname.equals(string)) return -100;
		}
		
		//존재하지 않는 닉네임의 이용자라면 -100을 리턴
		User user = userDAO.checkNickname(nickname);
		if(user == null) return -100;
		
		//Queue에 있는 이용자라면 자신의 앞에 몇 명 있는지 체크해서 보내 줌 
		int index = 0;
		for (String string : queue) {
			if(nickname.equals(string)) {
				for (int i = 0; i < waitingUsers.length; i++) {
					if(waitingUsers[i] != null) index++;
				}
				return index;
			}
			index++;
		}
		
		//CurrentUsers가 0명이라면
		if(currentUsers[0] == null && currentUsers[1] == null) {
			//WaitingUsers가 0명이라면
			if(waitingUsers[0] == null && waitingUsers[1] == null) {
				currentUsers[0] = nickname;
				return -1;
			}
			//WaitingUsers가 1명이라면
			else if(waitingUsers[0] != null && waitingUsers[1] == null) {
				if(waitingUsers[0].equals(nickname)) {
					waitingUsers[0] = null;
				}
				currentUsers[0] = nickname;
				return -1;
			}
			//WaitingUsers가 1명이라면
			else if(waitingUsers[0] == null && waitingUsers[1] != null) {
				if(waitingUsers[1].equals(nickname)) {
					waitingUsers[1] = null;
				}
				currentUsers[0] = nickname;
				return -1;
			}
			//WaitingUsers가 2명이라면
			else {
				if(waitingUsers[0].equals(nickname)) {
					waitingUsers[0] = null;
					currentUsers[0] = nickname;
					return -1;
				}
				else if(waitingUsers[1].equals(nickname)) {
					waitingUsers[1] = null;
					currentUsers[0] = nickname;
					return -1;
				}
				else {
					queue.add(nickname);
					return queue.size() + 1;
				}
			}
		}
		//CurrentUsers가 1명이라면
		if(currentUsers[0] != null && currentUsers[1] == null) {
			//WaitingUsers가 0명이라면
			if(waitingUsers[0] == null && waitingUsers[1] == null) {
				currentUsers[1] = nickname;
				createGame();
				return -1;
			}
			//WaitingUsers가 1명이라면
			else if(waitingUsers[0] != null && waitingUsers[1] == null) {
				if(waitingUsers[0].equals(nickname)) {
					waitingUsers[0] = null;
					currentUsers[1] = nickname;
					createGame();
					return -1;
				}
				else {
					queue.add(nickname);
					return queue.size();
				}
			}
			//WaitingUsers가 1명이라면
			else if(waitingUsers[0] == null && waitingUsers[1] != null) {
				if(waitingUsers[1].equals(nickname)) {
					waitingUsers[1] = null;
					currentUsers[1] = nickname;
					createGame();
					return -1;
				}
				else {
					queue.add(nickname);
					return queue.size();
				}
			}
			//WaitingUsers가 2명이라면
			else {
				if(waitingUsers[0].equals(nickname)) {
					waitingUsers[0] = null;
					currentUsers[1] = nickname;
					createGame();
					return -1;
				}
				else if(waitingUsers[1].equals(nickname)) {
					waitingUsers[1] = null;
					currentUsers[1] = nickname;
					createGame();
					return -1;
				}
				else {
					queue.add(nickname);
					return queue.size() + 1;
				}
			}
		}
		//CurrentUsers가 1명이라면
		if(currentUsers[0] == null && currentUsers[1] != null) {
			//WaitingUsers가 0명이라면
			if(waitingUsers[0] == null && waitingUsers[1] == null) {
				currentUsers[0] = nickname;
				createGame();
				return -1;
			}
			//WaitingUsers가 1명이라면
			else if(waitingUsers[0] != null && waitingUsers[1] == null) {
				if(waitingUsers[0].equals(nickname)) {
					waitingUsers[0] = null;
					currentUsers[0] = nickname;
					createGame();
					return -1;
				}
				else {
					queue.add(nickname);
					return queue.size();
				}
			}
			//WaitingUsers가 1명이라면
			else if(waitingUsers[0] == null && waitingUsers[1] != null) {
				if(waitingUsers[1].equals(nickname)) {
					waitingUsers[1] = null;
					currentUsers[0] = nickname;
					createGame();
					return -1;
				}
				else {
					queue.add(nickname);
					return queue.size();
				}
			}
			//WaitingUsers가 2명이라면
			else {
				if(waitingUsers[0].equals(nickname)) {
					waitingUsers[0] = null;
					currentUsers[0] = nickname;
					createGame();
					return -1;
				}
				else if(waitingUsers[1].equals(nickname)) {
					waitingUsers[1] = null;
					currentUsers[0] = nickname;
					createGame();
					return -1;
				}
				else {
					queue.add(nickname);
					return queue.size() + 1;
				}
			}
		}
		//CurrentUsers가 2명이라면
		else {
			queue.add(nickname);
			return queue.size() - 1;
			
		}

	}

	@Override
	public void createGame() {
		LOGGER.info("2");
		Game game = gameDAO.createGame();
		LOGGER.info("3");
		recent = game.getId();
		LOGGER.info("4");
		Play play1 = new Play();
		LOGGER.info("5");
		User user1 = userDAO.checkNickname(currentUsers[0]);
		LOGGER.info("6");
		play1.setUser(user1);
		LOGGER.info("7");
		play1.setGame(game);
		LOGGER.info("8");
		playDAO.createPlay(play1);
		Play play2 = new Play();
		User user2 = userDAO.checkNickname(currentUsers[1]);
		play2.setUser(user2);
		play2.setGame(game);
		playDAO.createPlay(play2);
		
	}

	@Override
	public String checkQueue() {
		StringBuilder sb = new StringBuilder();
		for (String string : waitingUsers) {
			if(string != null) {
				sb.append(string).append(",");
			}
		}
		for (String string : queue) {
			sb.append(string).append(",");
		}
		return sb.toString();
	}

	@Override
	public boolean endGame(PlayDto playDto) {
		if(currentUsers[0] == null || currentUsers[1] == null) {
			return false;
		}
		Game game = gameDAO.getGame(recent);
		List<Play> list = playDAO.getPlay(game.getId());
		LOGGER.info(playDto.toString());
		for (Play play : list) {
			String nickName = play.getUser().getNickname();
			LOGGER.info(nickName);
			if(nickName.equals(playDto.getNickname1())) {
				LOGGER.info("1");
				play.setLapTime(playDto.getLaptime1());
				Record record = recordDAO.getRecord(play.getUser().getUserId());
				if(record == null) {
					record = new Record();
					record.setUser(play.getUser());
					record.setLapTime(play.getLapTime());
					recordDAO.saveRecord(record);
				}
				else if(record.getLapTime() > play.getLapTime()) {
					record.setLapTime(play.getLapTime());
					recordDAO.saveRecord(record);
				}
				byte isWin = playDto.getLaptime1() < playDto.getLaptime2() ? (byte) 1 : (byte) 0;
				play.setIsWin(isWin);
			}
			else {
				LOGGER.info("2");
				play.setLapTime(playDto.getLaptime2());
				Record record = recordDAO.getRecord(play.getUser().getUserId());
				if(record == null) {
					record = new Record();
					record.setUser(play.getUser());
					record.setLapTime(play.getLapTime());
					recordDAO.saveRecord(record);
				}
				else if(record.getLapTime() > play.getLapTime()) {
					record.setLapTime(play.getLapTime());
					recordDAO.saveRecord(record);
				}
				byte isWin = playDto.getLaptime1() < playDto.getLaptime2() ? (byte) 0 : (byte) 1;
				play.setIsWin(isWin);
			}
			playDAO.createPlay(play);
		}
		Arrays.fill(currentUsers, null);
		int size = queue.size();
		int index = 0;
		while(size-- > 0) {
			if(index > 1) break;
			waitingUsers[index++] = queue.poll();
			timeLimit = System.currentTimeMillis(); // 시간제한이 다시 생김
		}
		return true;
		
	}
	
	

}