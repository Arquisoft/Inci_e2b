package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.dbupdate.DbTest;
import test.executer.ExecuterTest;
import test.modeltest.UserTest;
import test.parsertest.CSVTest;
import test.parsertest.LetterTest;
import test.parsertest.ParserTest;
import test.reportwritter.ReportWriterTest;
import test.reportwritter.WriteReportImplTest;

@RunWith(Suite.class)
@SuiteClasses({
	DbTest.class,
	ExecuterTest.class,
	UserTest.class,
	CSVTest.class,
	LetterTest.class,
	ParserTest.class,
	ReportWriterTest.class,
	WriteReportImplTest.class
})
public class TestAll {

}