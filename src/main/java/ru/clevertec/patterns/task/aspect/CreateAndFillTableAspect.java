package ru.clevertec.patterns.task.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import ru.clevertec.patterns.task.database.postgresql.service.BootstrapDataBasePostgreSQL;
import ru.clevertec.patterns.task.database.postgresql.service.PostgreSQLCreateTables;

@Aspect
public class CreateAndFillTableAspect {

	@Pointcut("@annotation(ru.clevertec.patterns.task.aspect.annotation.CreateAndFillTable)")
	public void createTableMethod() {
	}

	@Before(value = "createTableMethod()")
	public void createAndFillTableProfiling(JoinPoint jp) throws Exception {
		PostgreSQLCreateTables postgreSQLCreateTables = new PostgreSQLCreateTables();
		postgreSQLCreateTables.createTablesInDataBase();
		BootstrapDataBasePostgreSQL bootstrapDataBasePostgreSQL = new BootstrapDataBasePostgreSQL();
		bootstrapDataBasePostgreSQL.fillDataBase();
	}

}
