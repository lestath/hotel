
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.lestath.xmlpro.model.Employee;
import com.lestath.xmlpro.model.Guest;
import com.lestath.xmlpro.model.Room;
import com.lestath.xmlpro.model.Transaction;
import com.lestath.xmpro.repository.EmployeeRepository;
import com.lestath.xmpro.repository.GuestRepository;
import com.lestath.xmpro.repository.RoomRepository;
import com.lestath.xmpro.repository.TransactionRepository;
import com.lestath.xmpro.repository.csv.CsvEmployeeRepository;
import com.lestath.xmpro.repository.csv.CsvGuestRepository;
import com.lestath.xmpro.repository.csv.CsvRoomRepository;
import com.lestath.xmpro.repository.csv.CsvTransactionRepository;



public class Main {
	private static EmployeeRepository er = new CsvEmployeeRepository();
	private static GuestRepository gr = new CsvGuestRepository();
	private static RoomRepository rr = new CsvRoomRepository();
	private static TransactionRepository tr = new CsvTransactionRepository();
	
	public static void main(String[] args) {
/*		add();
		//delete();
		addGuest();
		addRoom();
		addTransaction(
					1L,
					gr.findById(1L),
					rr.findById(1L),
					er.findById(3L)
				); */
		System.out.println(tr.findById(1L).toString());
		Transaction t= tr.findById(1L);
		Calendar cal = Calendar.getInstance();
		t.setFrom(new Date(cal.getTimeInMillis()));
		cal.add(Calendar.DATE,5);
		t.setTo(new Date(cal.getTimeInMillis()));
		tr.update(t);
		System.out.println(tr.findById(1L).toString());
		
		
	}
	
	private static void addTransaction(Long id,Guest g, Room r, Employee e){
		Transaction t= new Transaction();
		t.setId(id);
		t.setGuest(g);
		t.setEmployee(e);
		t.setRoom(r);
		t.setFullCost(r.getCost());
		tr.save(t);
	}
	
	
	private static void addRoom() {
		Room emp = new Room();
		emp.setId(1L);
		emp.setCost(new BigDecimal("200.00"));
		emp.setFloor(1);
		emp.setRoomNr(10);
		rr.save(emp);	
		
	}

	private static void addGuest(){
		Guest emp = new Guest();
		emp.setId(1L);
		emp.setName("Nowy");
		emp.setSurname("Gość");
		gr.save(emp);	
	}
	
	private static void add(){
		Employee emp = new Employee();
		emp.setId(2L);
		emp.setName("Konrad");
		emp.setSurname("Kopacz");
		emp.setSalaryStandard(new BigDecimal("3000.00"));
		er.save(emp);	
		
		emp = new Employee();
		emp.setId(3L);
		emp.setName("Konrad");
		emp.setSurname("Kopacz");
		emp.setSalaryStandard(new BigDecimal("3000.00"));
		er.save(emp);	
		
		emp = new Employee();
		emp.setId(4L);
		emp.setName("Konrad");
		emp.setSurname("Kopacz");
		emp.setSalaryStandard(new BigDecimal("3000.00"));
		er.save(emp);	
	}
	
	private static void delete(){
		Employee emp = new Employee();
		emp.setId(4L);
		er.delete(emp);	
	}
	
	private static void updateTransaction(){
		Employee emp = er.findById(2L);
		Transaction t = tr.findById(1L);
		t.setEmployee(emp);
		
		System.out.println(emp.toString());
		System.out.println(t.toString());
		tr.update(t);
	}

}
