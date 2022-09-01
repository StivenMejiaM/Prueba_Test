package application;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class LoginControllerTest {

	@Test
	void test() throws SQLException {
		LoginController c2 = new LoginController();
		
		int validation = c2.ingresar("stiven", "mejia");
		
		assertEquals(2, validation, 0.0001);
		
		
	}

}
