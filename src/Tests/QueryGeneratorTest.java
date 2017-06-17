package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Database.QueryGenerator;

public class QueryGeneratorTest {
	private QueryGenerator generator;

	@Before
	public void setUp() throws Exception {
		generator = new QueryGenerator();
	}

	@Test
	public void test1() {
		List<String> columnNames = Arrays.asList("ID","username", "surname", "password");
		assertEquals("UPDATE table SET username = ?,surname = ?,password = ? WHERE ID = 1",
				generator.getUpdateByIdQuery(columnNames, "table", 1));

	}
}
