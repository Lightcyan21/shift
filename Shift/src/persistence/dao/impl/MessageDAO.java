package persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Message;

public class MessageDAO extends AbstractDAO<Message> implements DAO<Message>{
	
	private static long counter = 1L;

	@Override
	public Message create() {
		Message msg = new Message();
		msg.setId(++counter);
		return msg;
	}

	@Override
	public void delete(Message entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Message> findAll() {
		List<Message> list = new ArrayList<Message>();
		Message msg1 = new Message();
		msg1.setAuthor("DHBW Testuser");
		msg1.setCreateDate(new Date());
		msg1.setId(1L);
		msg1.setMessage("Hallo. Wie gehts dir?");
		msg1.setRecipient("Max Mustermann");
		
		Message msg2 = new Message();
		msg2.setAuthor("Max Mustermann");
		msg2.setCreateDate(new Date());
		msg2.setId(2L);
		msg2.setMessage("Gut, und selbst?");
		msg2.setRecipient("DHBW Testuser");
		
		list.add(msg1);
		list.add(msg2);
		
		return list;
	}

	@Override
	public Message getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Message entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload(Message entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(Message entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	
	
}
